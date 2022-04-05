package File_Writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Klassen {

	static void writeKlassen(Connection c, String file) {
		try {
			File f = new File(file);
			FileWriter fw = new FileWriter(f);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Statement stmt = c.createStatement();
			String sql = "select klasse, anzahlSchueler, zuordnungsDatum from klassen;";
			ResultSet rs = stmt.executeQuery(sql);
			int x = 1; //Laufvariable
			System.out.println();System.out.println("Klassen:");
			
			while (rs.next()) {
				String kl = rs.getString("klasse");
				int anz = rs.getInt("anzahlSchueler");
				Date d = rs.getDate("zuordnungsDatum");
				String date = df.format(d);
				String s = x + ", " + kl + ", " + anz + ", " + date +  "\n";
				fw.write(s);
				String output = x + "|" + kl + "|" + anz + "|" + date;
				System.out.println(output);
				x++;
			}

			rs.close();
			stmt.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
