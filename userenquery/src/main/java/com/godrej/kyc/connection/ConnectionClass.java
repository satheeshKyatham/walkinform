package  com.godrej.kyc.connection;

 
import java.sql.Connection;
import java.sql.DriverManager;
 

public class ConnectionClass {
	
	String DB_DRIVER_CLASS = "org.postgresql.Driver";
	
	/*String DB_URL = "jdbc:postgresql://43.24.214:5432/godrejplnew";
	String DB_USERNAME = "godrejplnew";
	String DB_PASSWORD = "godrejplnew";*/
	
	
	/*String DB_URL = "jdbc:postgresql://10.10.20.98:5432/godrejpl";
	String DB_USERNAME = "godrejpl";
	String DB_PASSWORD = "godrejpl";*/
	
	String DB_URL = "jdbc:postgresql://180.179.170.10:5432/godrejpl";
	String DB_USERNAME = "godrejpl";
	String DB_PASSWORD = "godrejpl";

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
