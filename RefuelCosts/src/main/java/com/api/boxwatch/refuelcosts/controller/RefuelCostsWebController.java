package com.api.boxwatch.refuelcosts.controller;

import java.util.List;
import java.time.LocalDate;

import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.api.boxwatch.refuelcosts.errorhandling.EmptyDataException;
import com.api.boxwatch.refuelcosts.errorhandling.InvalidDateException;
import com.api.boxwatch.refuelcosts.mapping.*;

@RestController
public class RefuelCostsWebController {

	@Autowired
	private final JdbcTemplate jdbcTemplate;
	private static ResponseEntity<List<RefuelCosts>> responseEntity;
	private static String sql;
	private static List<RefuelCosts> response;

	public RefuelCostsWebController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/refuelcosts")
	public ResponseEntity<List<RefuelCosts>> getRefuelCosts() {
		sql = "SELECT * FROM RefuelCosts;";
		response = jdbcTemplate.query(sql, new RefuelCostsRowMapper());

		if (response.isEmpty())
			throw new EmptyDataException("No data to display");

		responseEntity = new ResponseEntity<List<RefuelCosts>>(response, HttpStatus.OK);

		return responseEntity;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/refuelcosts/since-date")
	public ResponseEntity<List<RefuelCosts>> getRefuelCostsSince(@PathVariable @RequestParam String date) {
		String currDate = stripDateChars(LocalDate.now().toString());
		date = stripDateChars(date);

		checkDates(date);

		sql = String.format("SELECT * FROM RefuelCosts WHERE date BETWEEN %s AND %s", date, currDate);
		response = jdbcTemplate.query(sql, new RefuelCostsRowMapper());

		if (response.isEmpty())
			throw new EmptyDataException("No data to display");

		responseEntity = new ResponseEntity<List<RefuelCosts>>(response, HttpStatus.OK);

		return responseEntity;
	}

	private String stripDateChars(String date) {
		date = date.replace("-", "");
		date = date.replace("/", "");

		return date;
	}

	private void checkDates(String date) {
		final String DATE_FORMAT = "yyyyMMdd";

		if (GenericValidator.isDate(date, DATE_FORMAT, true)) {
			throw new InvalidDateException();
		}
	}

}