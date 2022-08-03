package com.api.boxwatch.timesaved.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.api.boxwatch.timesaved.errorhandling.EmptyDataException;
import com.api.boxwatch.timesaved.mapping.*;

@RestController
public class TimeSavedWebController {

	@Autowired
	private final JdbcTemplate jdbcTemplate;
	private static ResponseEntity<List<TimeSaved>> responseEntity;
	private static String sql;
	private static List<TimeSaved> response;

	public TimeSavedWebController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/timeSaved")
	public ResponseEntity<List<TimeSaved>> getData() {
		sql = "SELECT * FROM TimeSaved;";
		response = jdbcTemplate.query(sql, new TimeSavedRowMapper());
		
		if (response.isEmpty()) throw new EmptyDataException("No data to display");
			
		responseEntity = new ResponseEntity<List<TimeSaved>>(response, HttpStatus.OK);

		return responseEntity;
	}

}