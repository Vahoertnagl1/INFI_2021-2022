package File_reader_JSON;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

import org.json.simple.JSONObject;

public class Lehrer {

	static void write(Connection c, String file) {
		try {
			FileWriter fw = new FileWriter(file);
			JSONObject json = new JSONObject();
			String s = "";

			Statement stmt = c.createStatement();
			String sql = "select vorname, nachname, age, gehalt, KVvon from lehrer;";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				int age = rs.getInt("age");
				int gehalt = rs.getInt("gehalt");
				String KVvon = rs.getString("KVvon");
				
				json.put("vorname", vorname);
				json.put("nachname", nachname);
				json.put("age", age);
				json.put("gehalt", gehalt);
				json.put("KVvon", KVvon);	
				s = s + json;
			}
			fw.write(s);
			fw.flush();
			fw.close();
			rs.close();
			stmt.close();
			System.out.println("Write...");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
