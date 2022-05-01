package File_Reader_JSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Schueler {

	static void read(Connection c, String file, String tableName) {
		try {
			File f = new File(file);
			Scanner scanner = new Scanner(f);
			String data = "";
			while (scanner.hasNextLine()) {
				data = scanner.nextLine();
				Object ob = new JSONParser().parse(data);
				JSONObject js = (JSONObject) ob;
				
				String vorname = (String)js.get("vorname");
				String nachname = (String)js.get("nachname");
				long age = (long)js.get("age");
				String klasse = (String)js.get("klasse");
				
				String sql = "insert into " + tableName + " (vorname, nachname, age, klasse) values (?, ?, ?, ?);";
				
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setString(1, vorname);
				stmt.setString(2, nachname);
				stmt.setLong(3, age);
				stmt.setString(4, klasse);
				stmt.executeUpdate();
				stmt.close();
				System.out.printf("Vorname: %s\nNachname: %s\nAlter: %d\nKlasse: %s\n", vorname, nachname, age, klasse);
				System.out.println();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File wurde nicht gefunden.");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
