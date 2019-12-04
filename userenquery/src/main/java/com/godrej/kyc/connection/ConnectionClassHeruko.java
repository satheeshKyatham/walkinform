package com.godrej.kyc.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClassHeruko {
	

	
	String DB_DRIVER_CLASS = "org.postgresql.Driver";
	
	/*String DB_URL = "jdbc:postgresql://43.24.214:5432/godrejplnew";
	String DB_USERNAME = "godrejplnew";
	String DB_PASSWORD = "godrejplnew";*/
	
	
	/*String DB_URL = "jdbc:postgresql://10.10.20.98:5432/godrejpl";
	String DB_USERNAME = "godrejpl";
	String DB_PASSWORD = "godrejpl";*/
	
	/*String DB_URL = "jdbc:postgresql://ec2-23-23-50-231.compute-1.amazonaws.com:5432/dfchgt5o680aok?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	String DB_USERNAME = "ubb3mhh56quq28";
	String DB_PASSWORD = "p40b3519da1cda5a8064ad44a89f19aee833c231d97b5996ebd63ce3d059e8bbd";*/
	
	String DB_URL = "jdbc:postgresql://ec2-3-85-180-122.compute-1.amazonaws.com:5432/dfjs7psaki4b4p?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	String DB_USERNAME = "u3pq3bpsmllhqt";
	String DB_PASSWORD = "p98da94e6778b786e4b9e4dd137c086d2b7251c828cea6c77c8348150a1fcc8ed";

	
	/*String DB_URL = "jdbc:postgresql://localhost:5432/gplsurvey";
	String DB_USERNAME = "gplsurvey";
	String DB_PASSWORD = "gplsurvey";*/
	
	public boolean getConnection() {
		 
		boolean flag = false;
		Connection con = null;
		try {
			/*InputStream fis = new FileInputStream(new File("dao.properties"));
			props.load(fis);*/
			Class.forName(DB_DRIVER_CLASS);
			con = DriverManager.getConnection(DB_URL, DB_USERNAME,
					DB_PASSWORD);
			if (con != null) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public Connection getCon()  {
 
		Connection con = null;
		try {
			Class.forName(DB_DRIVER_CLASS);
			con = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}



}
