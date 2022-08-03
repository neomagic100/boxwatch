package com.api.boxwatch.returns.controller;

import java.util.List;

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
import com.api.boxwatch.returns.errorhandling.EmptyDataException;
import com.api.boxwatch.returns.errorhandling.InvalidDateException;
import com.api.boxwatch.returns.mapping.*;

@RestController
public class ReturnsWebController {

	@Autowired
	private final JdbcTemplate jdbcTemplate;
	private static ResponseEntity<List<Returns>> responseEntity;
	private static String sql;
	private static List<Returns> response;

	public ReturnsWebController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/returns")
	public ResponseEntity<List<Returns>> getData() {
		sql = "SELECT * FROM Returns;";
		response = jdbcTemplate.query(sql, new ReturnsRowMapper());

		if (response.isEmpty())
			throw new EmptyDataException();

		responseEntity = new ResponseEntity<List<Returns>>(response, HttpStatus.OK);

		return responseEntity;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/returns/dates/between/")
	public ResponseEntity<List<Returns>> getBetweenDates(@PathVariable @RequestParam String start,
			@PathVariable @RequestParam String end) {
		start = start.replace("-", "");
		end = end.replace("-", "");

		checkDates(start, end);

		sql = String.format("SELECT * FROM Returns WHERE ReturnDate BETWEEN %s AND %s", start, end);
		response = jdbcTemplate.query(sql, new ReturnsRowMapper());

		if (response.isEmpty())
			throw new EmptyDataException("No data to display");

		responseEntity = new ResponseEntity<List<Returns>>(response, HttpStatus.OK);

		return responseEntity;
	}

	private void checkDates(String start, String end) {

        if (!GenericValidator.isDate(start, "yyyyMMdd", true) && !GenericValidator.isDate(end, "yyyyMMdd", true)) {
			throw new InvalidDateException("Your date format was incorrect");
		} else if (Integer.parseInt(start) > Integer.parseInt(end)) {
			throw new InvalidDateException();
		}
	}

}