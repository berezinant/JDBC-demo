package JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class JDBCDemo {
	
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		
		String url = "jdbc:oracle:oci8:@"+"localhost:1521/xe";
		String user = "anton";
		String pass = "qwer";
		
		try {
//			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		
			Connection cn = DriverManager.getConnection(url, user, pass);
			
			Statement st = cn.createStatement();
			String q = "";
			
			q = "update sellers set city='Barcelona' where id=1001";
			int rowsUpdated = st.executeUpdate(q);
			System.out.println("updated: " + rowsUpdated + " row(s)");
			
//			q = "delete from sellers where id=1001";
//			int rowsDeleted = st.executeUpdate(q);
//			System.out.println("deleted: " + rowsDeleted + " row(s)");
//			
//			q = "insert into sellers values (1001, 'Jonh', 'London', 11)";
//			int rowsInserted = st.executeUpdate(q);
//			System.out.println("inserted: " + rowsInserted + " row(s)");
			
			
			q = "select * from sellers";
			ResultSet rs = st.executeQuery(q);
			int salaryIndex   = rs.findColumn("salary");
			while (rs.next()) {
				System.out.print(rs.getInt(1));
				System.out.print("\t|\t");
				System.out.print(rs.getString(2));
				System.out.print("\t|\t");
				System.out.print(rs.getString("city"));
				System.out.print("\t|\t");
				System.out.println(rs.getInt(salaryIndex));
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
