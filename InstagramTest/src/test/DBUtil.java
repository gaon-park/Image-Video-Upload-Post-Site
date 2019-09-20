package test;

import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;
public class DBUtil {

	public static Connection getConnection() throws Exception{
		DataSource ds=(DataSource)new InitialContext().lookup(
				"java:comp/env/jdbc/mysql");	
		return ds.getConnection();
	}
	
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Statement ps) {
		if(ps != null) {
			try {
				ps.close();			
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
