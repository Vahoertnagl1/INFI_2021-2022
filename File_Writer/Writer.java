package File_Writer;

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
				String url = "jdbc:mysql://localhost:3306/excelWriter";
				String user = "vahoertnagl";
				String pass = ""; //pass ist gewollt leer
				
				Connection c = getConnection(url, user, pass);
				
				Schueler.writeSchueler(c, "/home/hoertnagl/eclipse-workspace/INFI_csv/schueler_writer.csv");
				Klassen.writeKlassen(c, "/home/hoertnagl/eclipse-workspace/INFI_csv/klassen_writer.csv");
				SchuelerZuKlassen.writeSK(c, "/home/hoertnagl/eclipse-workspace/INFI_csv/schuelerZuKlassen_writer.csv");
				
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
















