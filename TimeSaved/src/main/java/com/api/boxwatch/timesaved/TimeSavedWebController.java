package com.api.boxwatch.timesaved;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeSavedWebController {
	
	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	public TimeSavedWebController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
    @CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/timeSaved")
	public List<TimeSaved> getData() {
		String sql = "SELECT * FROM TimeSaved;";
		return jdbcTemplate.query(sql, new TimeSavedRowMapper());
	}
    
}