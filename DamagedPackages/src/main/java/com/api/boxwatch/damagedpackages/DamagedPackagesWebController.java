package com.api.boxwatch.damagedpackages;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DamagedPackagesWebController {
	
	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	public DamagedPackagesWebController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
    @CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/damagedPackages")
	public List<DamagedPackages> getData() {
		String sql = "SELECT * FROM DamagedPackages;";
		return jdbcTemplate.query(sql, new DamagedPackagesRowMapper());
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/damagedPackages/dates/between")
    public List<DamagedPackages> getBetweenDates(@PathVariable @RequestParam String start, @PathVariable @RequestParam String end) {
    	start = start.replace("-", "");
    	end = end.replace("-", "");
		String sql = String.format("SELECT * FROM DamagedPackages WHERE date BETWEEN %s AND %s", start, end);
    	return jdbcTemplate.query(sql, new DamagedPackagesRowMapper());
    }

}