package com.api.boxwatch.damagedpackages.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.api.boxwatch.damagedpackages.errorhandling.EmptyDataException;
import com.api.boxwatch.damagedpackages.errorhandling.InvalidDateException;
import com.api.boxwatch.damagedpackages.mapping.DamagedPackages;
import com.api.boxwatch.damagedpackages.mapping.DamagedPackagesRowMapper;

@RestController
public class DamagedPackagesWebController {

	@Autowired
	private final JdbcTemplate jdbcTemplate;
	private static ResponseEntity<List<DamagedPackages>> responseEntity;
	private static String sql;
	private static List<DamagedPackages> response;

	private static final int ERR_INT = -1;
	private static final String ERR_STR = "Error";

	public DamagedPackagesWebController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/damagedPackages")
	public ResponseEntity<List<DamagedPackages>> getData() {
		sql = "SELECT * FROM DamagedPackages;";
		response = jdbcTemplate.query(sql, new DamagedPackagesRowMapper());
		
		if (response.isEmpty()) throw new EmptyDataException("No data to display");
		
		responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		
		return responseEntity;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/damagedPackages/dates/between")
	public ResponseEntity<List<DamagedPackages>> getBetweenDates(@PathVariable @RequestParam String start,
			@PathVariable @RequestParam String end) {

		return queryAndErrorCheck(start, end);
	}

	private ResponseEntity<List<DamagedPackages>> queryAndErrorCheck(String start, String end) {
		start = start.replace("-", "");
		end = end.replace("-", "");
		try {
			checkDates(start, end);
			sql = String.format("SELECT * FROM DamagedPackages WHERE date BETWEEN %s AND %s", start, end);
			response = jdbcTemplate.query(sql, new DamagedPackagesRowMapper());
			if (response.isEmpty())
				throw new EmptyDataException();

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (InvalidDateException e) {
			response = new ArrayList<>();
			response.add(new DamagedPackages(ERR_STR, ERR_INT, ERR_INT));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (EmptyDataException e) {
			response.add(new DamagedPackages(ERR_STR, ERR_INT, ERR_INT));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			response.add(new DamagedPackages(ERR_STR, ERR_INT, ERR_INT));
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	private void checkDates(String start, String end) {

        if (!GenericValidator.isDate(start, "yyyyMMdd", true) && !GenericValidator.isDate(end, "yyyyMMdd", true)) {
			throw new InvalidDateException("Your date format was incorrect");
		} else if (Integer.parseInt(start) > Integer.parseInt(end)) {
			throw new InvalidDateException();
		}
	}

}