package com.api.boxwatch.callervolume.controller;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.boxwatch.callervolume.errorhandling.EmptyDataException;
import com.api.boxwatch.callervolume.errorhandling.InvalidDateException;
import com.api.boxwatch.callervolume.errorhandling.InvalidQuarterException;
import com.api.boxwatch.callervolume.mapping.CallerVolume;
import com.api.boxwatch.callervolume.mapping.CallerVolumeRowMapper;
import com.api.boxwatch.callervolume.mapping.CallerVolumeView;
import com.api.boxwatch.callervolume.mapping.CallerVolumeViewRowMapper;
import com.api.boxwatch.callervolume.util.StateComparator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class CallerVolumeWebController {

	@Autowired
	private final JdbcTemplate jdbcTemplate;
	private static ResponseEntity<List<CallerVolume>> responseEntity;
	private static String sql;
	private static List<CallerVolume> response;
	private static List<CallerVolumeView> responseAlt;

	private static final int ERR_INT = -1;
	private static final String ERR_STR = "Error";

	public CallerVolumeWebController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/callerVolume")
	public ResponseEntity<List<CallerVolume>> getCallerVolume() {
		sql = "SELECT * FROM CallerVolume;";
		response = jdbcTemplate.query(sql, new CallerVolumeRowMapper());
		if (response.isEmpty())
			throw new EmptyDataException();

		responseEntity = new ResponseEntity<List<CallerVolume>>(response, HttpStatus.OK);

		return responseEntity;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/callerVolume/dates/between")
	public ResponseEntity<List<CallerVolume>> getBetweenDates(@PathVariable @RequestParam String start,
			@PathVariable @RequestParam String end) {
		start = stripDateChars(start);
		end = stripDateChars(end);
		try {
			checkDates(start, end);
			sql = String.format("SELECT * FROM CallerVolume WHERE CallsDate BETWEEN %s AND %s", start, end);
			response = jdbcTemplate.query(sql, new CallerVolumeRowMapper());
			if (response.isEmpty())
				throw new EmptyDataException();

			responseEntity = new ResponseEntity<List<CallerVolume>>(response, HttpStatus.OK);
		} catch (InvalidDateException e) {
			response.clear();
			response.add(new CallerVolume(ERR_INT, ERR_INT, ERR_STR, ERR_STR));
			responseEntity = new ResponseEntity<List<CallerVolume>>(response, HttpStatus.BAD_REQUEST);
		} catch (EmptyDataException e) {
			response.add(new CallerVolume(ERR_INT, ERR_INT, ERR_STR, ERR_STR));
			responseEntity = new ResponseEntity<List<CallerVolume>>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			response.add(new CallerVolume(ERR_INT, ERR_INT, ERR_STR, ERR_STR));
			responseEntity = new ResponseEntity<List<CallerVolume>>(response, HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/callerVolume/dates/quarter")
	public String getQuarter(@PathVariable @RequestParam String quarter) {
		if (Integer.parseInt(quarter) > 4 || Integer.parseInt(quarter) < 1) {
			try {
				throw new InvalidQuarterException("The value: " + quarter + " is not a valid quarter argument");
			} catch (InvalidQuarterException exception) {
				return exception.getMessage();
			}
		}
		sql = createQuery(quarter);

		try {
			responseAlt = jdbcTemplate.query(sql, new CallerVolumeViewRowMapper());
			if (responseAlt.isEmpty())
				throw new EmptyDataException();
		} catch (EmptyDataException e) {
			responseAlt.add(new CallerVolumeView(ERR_INT, ERR_STR));
		}

		Collections.sort(responseAlt, new StateComparator());

		aggregateSums(responseAlt);

		@SuppressWarnings("unused")
		ResponseEntity<List<CallerVolumeView>> responseEntity = new ResponseEntity<List<CallerVolumeView>>(responseAlt,
				HttpStatus.OK);

		return toJson(responseAlt);
	}

	private String toJson(List<CallerVolumeView> cv) {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();

		return gson.toJson(cv);
	}

	private void aggregateSums(List<CallerVolumeView> response) {
		ListIterator<CallerVolumeView> iter = response.listIterator();

		while (iter.hasNext()) {
			int prevIdx = iter.previousIndex();

			if (prevIdx < 0) {
				iter.next();
				continue;
			}

			CallerVolumeView prev = response.get(prevIdx);
			CallerVolumeView curr = iter.next();

			if (sameState(prev, curr)) {
				response.set(prevIdx, addCallVolumes(prev, curr));
				iter.remove();
			}

		}
	}

	private String createQuery(String quarter) {
		String sqlret = "CALL queryquarter(";
		String daterange = "";

		if (quarter.equals("1")) {
			daterange = "20230102, 20230331);";
		} else if (quarter.equals("2")) {
			daterange = "20230401, 20230630);";
		} else if (quarter.equals("3")) {
			daterange = "20230701, 20230930);";
		} else if (quarter.equals("4")) {
			daterange = "20231001, 20231230);";
		}

		sqlret += daterange;

		return sqlret;
	}

	private String stripDateChars(String date) {
		date = date.replace("-", "");
		date = date.replace("/", "");

		return date;
	}

	private CallerVolumeView addCallVolumes(CallerVolumeView sumTo, CallerVolumeView sumFrom) {
		sumTo.setNumberOfCalls(sumTo.getNumberOfCalls() + sumFrom.getNumberOfCalls());
		return sumTo;
	}

	private boolean sameState(CallerVolumeView c1, CallerVolumeView c2) {
		return (new StateComparator().compare(c1, c2) == 0);
	}

	private void checkDates(String start, String end) throws InvalidDateException {
		if (!GenericValidator.isDate(start, "yyyyMMdd", true) && !GenericValidator.isDate(end, "yyyyMMdd", true)) {
			throw new InvalidDateException("Your date format was incorrect");
		} else if (Integer.parseInt(start) > Integer.parseInt(end)){
			throw new InvalidDateException("Your date was wrong");
		}
	}
}
