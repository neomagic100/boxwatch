package com.api.boxwatch.callervolume;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallerVolumeWebController {
	
	@Autowired
	private final JdbcTemplate jdbcTemplate;
	
	public CallerVolumeWebController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
    @CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/callerVolume")
	public List<CallerVolume> getTuples() {
		String sql = "SELECT * FROM CallerVolume;";
		return jdbcTemplate.query(sql, new CallerVolumeRowMapper());
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/callerVolume/dates/between")
    public List<CallerVolume> getBetweenDates(@PathVariable @RequestParam String start, @PathVariable @RequestParam String end) {
		start = stripDateChars(start);
		end = stripDateChars(end);
    	String sql = String.format("SELECT * FROM CallerVolume WHERE CallsDate BETWEEN %s AND %s", start, end);
    	return jdbcTemplate.query(sql, new CallerVolumeRowMapper());
    }
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/callerVolume/dates/quarter")
	public List<CallerVolumeView> getQuarter(@RequestParam int quarter) {
		String sql = "CALL queryquarter(";
		String daterange = "";
		
		switch(quarter) {
		case 1:
			daterange = "20230102, 20230331);";
			break;
		
		case 2:
			daterange = "20230401, 20230630);";
			break;
			
		case 3:
			daterange = "20230701, 20230930);";
			break;
		
		case 4:
			daterange = "20231001, 20231230);";
			break;
		
		default:
			daterange = "00000101, NOW());";
			break;
		
		}
		//daterange += " GROUP BY State;";
		
		sql += daterange;
		
		return jdbcTemplate.query(sql, new CallerVolumeViewRowMapper());
	}
	
	private String stripDateChars(String date) {
    	date = date.replace("-", "");
    	date = date.replace("/", "");
    	
    	return date;
    }
}