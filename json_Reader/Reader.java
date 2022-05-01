package File_Reader_JSON;

import java.sql.*;

public class Reader {
	
	static Connection getConnection(String url, String user, String pass) {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://localhost:3306/jsonReader";
			String user = "vahoertnagl";
			String pass = ""; // pass ist gewollt leer

			Connection c = getConnection(url, user, pass);
			
			Schueler.read(c, "/home/hoertnagl/Schreibtisch/json_reader.txt", "schueler");
			
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
