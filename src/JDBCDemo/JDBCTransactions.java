package JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;

public class JDBCTransactions {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		
		String url = "jdbc:oracle:oci8:@"+"localhost:1521/xe";
		String user = "anton";
		String pass = "qwer";
		
		try {		
			Connection cn = DriverManager.getConnection(url, user, pass);
			
			try {
				cn.setAutoCommit(false);
				String q = "update accounts set balance=(balance + ?) where id = ?";
				PreparedStatement st = cn.prepareStatement(q);
				st.setDouble(1, -15.0);
				st.setInt(2, 3003);
		        st.executeUpdate();
		        
		        st.close();
		        
		        st.setDouble(1, 15.0);
		        st.setInt(2, 3001);
		        st.executeUpdate();
		        cn.commit();
		        st.close();
			} catch (Exception e) {
				System.out.println(e);
				cn.rollback();
			}

			cn.close();
			System.out.println("connection closed: " + cn.isClosed());
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
