package JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class JDBCBatchUpdates {
	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		
		String url = "jdbc:oracle:oci8:@"+"localhost:1521/xe";
		String user = "anton";
		String pass = "qwer";
		
		try {
			Connection cn = DriverManager.getConnection(url, user, pass);
			
			Statement st = cn.createStatement();
			String q = "";
//			q = "drop table products";  // Пример DDL 
//			q = "create table products (id number primary key, title varchar(20), price decimal, seller_id number)";
//			int result = st.executeUpdate(q);
//			System.out.println("returns nothing: " + result);

//			st.addBatch("insert into products values (2001, 'Camera', 53.60,  1001)");
//		    st.addBatch("insert into products values (2002, 'Monitor', 98.62, 1001)");
//		    st.addBatch("insert into products values (2003, 'Keyboard', 5.00, 1003)");
//
//		    int[] rowsInserted = st.executeBatch();
//		    
//		    for (int i : rowsInserted) {
//		    	System.out.println(i);
//		    }
		    
		    
		    q = "update products set price=? where id=?"; // показать!
		    PreparedStatement prepSt = cn.prepareStatement(q);

		    prepSt.setDouble(1, 100.0);
	        prepSt.setInt(2, 2002);
	        prepSt.addBatch();

	        prepSt.setDouble(1, 10.0);
	        prepSt.setInt(2, 2003);
	        prepSt.addBatch();

		    int[] rowsUpdated = prepSt.executeBatch();
		    
		    for (int i : rowsUpdated) {
		    	System.out.println(i);
		    }

			st.close();
			cn.close();
			System.out.println("connection closed: " + cn.isClosed());
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
