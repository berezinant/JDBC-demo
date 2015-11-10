package JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class JDBCUpdatingResultSet {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		
		String url = "jdbc:oracle:oci8:@"+"localhost:1521/xe";
		String user = "anton";
		String pass = "qwer";
		
		try {
		
			Connection cn = DriverManager.getConnection(url, user, pass);
			
			String q = "select * from products order by price asc";
			Statement st = cn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			);
			
			System.out.println("Supports: " + cn.getMetaData().supportsResultSetConcurrency(1004, 1007));
			
			ResultSet rs = st.executeQuery(q);
			System.out.println("Concurreny: " + rs.getConcurrency());
			System.out.println("Scrollable: " + rs.getType());
	
			int titleIndex   = rs.findColumn("title");
			int priceIndex = rs.findColumn("price");
			rs.absolute(5);
			while (rs.previous()) {
				System.out.println(rs.getString(titleIndex) + "\t\t|\t" + rs.getString(priceIndex));
			}
			
			rs.close();
			st.close();
			cn.close();
			System.out.println("connection closed: " + cn.isClosed());
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
