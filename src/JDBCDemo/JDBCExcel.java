package JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;


public class JDBCExcel {
	
	    public static void main(String[] args)
	    {
	    	Locale.setDefault(Locale.ENGLISH);
//	        String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	        String url = "jdbc:odbc:ExcelTable";
	        try {
	        	Connection cn = DriverManager.getConnection(url, null, null);
	        	String q = "";
	        	Statement st = cn.createStatement();
	        	
	        	q = "insert into [sheet1$] values (4004, 'Jack', '12.12.1991')";
	            st.executeUpdate(q);

	            
	             q = "select * from [sheet1$]";
	            ResultSet rs = st.executeQuery(q);
	            while (rs.next())
	            {
	                System.out.print( rs.getInt("id") );
	                System.out.print("\t|\t");
	               	System.out.print( rs.getString(2) );
	               	System.out.print("\t|\t");
	                System.out.println( rs.getDate(3) );
	       	    }
	            
	            cn.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
	}
}
