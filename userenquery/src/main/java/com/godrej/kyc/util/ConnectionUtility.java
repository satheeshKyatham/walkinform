package com.godrej.kyc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionUtility {

	//static Logger logger = Logger.getLogger(ConnectionUtility.class);
	private ConnectionUtility()
	{
		
	}
	public static void closeConnection(ResultSet rs,Statement stmt,Connection con)
	{
		if(rs!=null)
		{
				try {
					rs.close();
				} catch (SQLException e) {
					//logger.info("error in closing ResultSet "+e.getMessage());
				}
		}
		if(stmt!=null)
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				//logger.info("error in closing PreparedStatement "+e.getMessage());
				}
		}
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				//logger.info("error in closing connection "+e.getMessage());
			}
		}
		
	}
}
