package com.godrej.kyc.connection;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbConnection 
{
	Connection c = null;
	InputStream inputStream;
	String host,port,dbname,username,password;
	
	
	
	public Connection connect()
		{
			try 
		    {
				
				Properties prop = new Properties();
				String propFileName = "application.properties";
				
				inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

				if (inputStream != null) {
					prop.load(inputStream);
				} else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
				
			/*	host = prop.getProperty("dbhost");
				port = prop.getProperty("dbport");
				dbname = prop.getProperty("dbusername");
				username = prop.getProperty("username");
				password = prop.getProperty("password");
				*/
			/*
			 * host = "10.2.90.49:5432"; port = "5432"; dbname = "gplsurvey"; username =
			 * "gplsurvey"; password = "gplsurvey";
			 */
				
				
				/*jdbc.driverClassName = com.microsoft.sqlserver.jdbc.SQLServerDriver
						jdbc.url = jdbc:sqlserver://10.21.24.22;databaseName=godrejGPL
						jdbc.username = safety
						jdbc.password = pass@123
						hibernate.dialect =org.hibernate.dialect.SQLServerDialect
						hibernate.show_sql = true
						hibernate.format_sql = true*/
				
				
		       Class.forName("org.postgresql.Driver");
		       c = DriverManager
		          .getConnection("jdbc:postgresql://ec2-3-85-180-122.compute-1.amazonaws.com:5432/dfjs7psaki4b4p?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory",
		        		  "u3pq3bpsmllhqt","p98da94e6778b786e4b9e4dd137c086d2b7251c828cea6c77c8348150a1fcc8ed");
		       		/*.getConnection("jdbc:postgresql://10.2.90.49:5432/gplsurvey",
		        		  "gplsurvey","gplsurvey");*/
		       c.setAutoCommit(false);
		    } 
		    catch (Exception e) 
		    {
		    	connect();
		       System.err.println(e.getClass().getName()+": "+e.getMessage());
		       e.printStackTrace();
		      
		    }
			return c;
		}
	public void conClose(Connection con,Statement stmt,ResultSet rs){
		try{
		if(con!=null){
			con.close();
		}
		if(stmt!=null){
			stmt.close();
		}

		if(rs!=null){
			rs.close();
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	}
