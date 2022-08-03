package com.api.boxwatch.refuelcosts.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class RefuelCostsRowMapper implements RowMapper<RefuelCosts> {

	@Override
	public RefuelCosts mapRow(ResultSet rs, int rowNum) throws SQLException {
		RefuelCosts rc = new RefuelCosts();
		rc.setDate(rs.getString("date"));
		rc.setGasCost(rs.getFloat("CostOfGasPerGallon"));
		rc.setElecCost(rs.getFloat("CostOfElectricityPerKilowattHour"));
		rc.setNatGasCost(rs.getFloat("CostOfNaturalGasPerMMBtu"));

		return rc;
	}

}
