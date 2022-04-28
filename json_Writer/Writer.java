package File_reader_JSON;

import java.sql.*;

public class Writer {

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
			String url = "jdbc:mysql://localhost:3306/jsonWriter";
			String user = "vahoertnagl";
			String pass = ""; // pass ist gewollt leer

			Connection c = getConnection(url, user, pass);

			Lehrer.write(c, "/home/hoertnagl/Schreibtisch/json_writer.txt");
			
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
