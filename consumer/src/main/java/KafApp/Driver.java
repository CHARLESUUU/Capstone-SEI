package KafApp;
import java.sql.*;
import java.util.*;
public class Driver {


	public static Connection myConn;

	public static void makeConn(){
		try{
		 myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&character_set_server=utf8mb4","root","lehighsei2018");
		} catch(Exception exc){
			exc.printStackTrace();
		}	
	}

	public static void closeConn(){
		try {
			myConn.close();
		}
		catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public static void getDB(ArrayList<String> msg) {
		// TODO Auto-generated method stub
		try {
			//connect to db
			
			//create stm
			
			Statement myStmt = myConn.createStatement();
			//query
			// ArrayList<String> msg = new ArrayList<String>();
			// msg.add("yes");
			// msg.add("no");

			for(int a =0 ; a<msg.size() ; a++) {
			String temp = msg.get(a);
			//System.out.println(str);
			String str = " INSERT INTO demo Values ( " + a + ","  + "'"+ temp + "'" + "); ";
		   
			myStmt.executeUpdate(str);
		
			}
		//	ResultSet result = myStmt.executeQuery("select * from demo");
			
			
			ResultSet result = myStmt.executeQuery("select * from demo");
			while(result.next()) {
				System.out.println(result.getString("id")+ " "+ result.getString("text"));
			}
			
			
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}

}
