package com.api.boxwatch.returns;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnsWebController {
	
	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	public ReturnsWebController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
    @CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/returns")
	public List<Returns> getData() {
		String sql = "SELECT * FROM Returns;";
		return jdbcTemplate.query(sql, new ReturnsRowMapper());
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/returns/dates/between/")
    public List<Returns> getBetweenDates(@PathVariable @RequestParam String start, @PathVariable @RequestParam String end) {
		start = start.replace("-", "");
		end = end.replace("-", "");
    	String sql = String.format("SELECT * FROM Returns WHERE Date BETWEEN %s AND %s", start, end);
    	return jdbcTemplate.query(sql, new ReturnsRowMapper());
    }

}