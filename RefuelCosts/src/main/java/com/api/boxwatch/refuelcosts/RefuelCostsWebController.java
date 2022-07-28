package com.api.boxwatch.refuelcosts;

import java.util.List;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefuelCostsWebController {
	
	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	public RefuelCostsWebController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
    @CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/refuelcosts")
	public List<RefuelCosts> getRefuelCosts() {
		String sql = "SELECT * FROM RefuelCosts;";
		return jdbcTemplate.query(sql, new RefuelCostsRowMapper());
	}
    
    @CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/refuelcosts/since-date")
	public List<RefuelCosts> getRefuelCostsSince(@PathVariable @RequestParam String date) {
    	String currDate = stripDateChars(LocalDate.now().toString());
    	date = stripDateChars(date);
    	
		String sql = String.format("SELECT * FROM RefuelCosts WHERE date BETWEEN %s AND %s", date, currDate);
		return jdbcTemplate.query(sql, new RefuelCostsRowMapper());
	}
    
    private String stripDateChars(String date) {
    	date = date.replace("-", "");
    	date = date.replace("/", "");
    	
    	return date;
    }
	
}