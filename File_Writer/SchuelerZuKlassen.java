package File_Writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SchuelerZuKlassen {

	static void writeSK(Connection c, String file) {
		try {
			File f = new File(file);
			FileWriter fw = new FileWriter(f);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Statement stmt = c.createStatement();
			String sql = "select sID, kID, zuordnungsDatum from schueler_zu_klassen;";
			ResultSet rs = stmt.executeQuery(sql);
			int x = 1; //Laufvariable
			System.out.println();System.out.println("Schueler_Zu_Klassen:");
			
			while (rs.next()) {
				int sID = rs.getInt("sID");
				int kID = rs.getInt("kID");
				Date d = rs.getDate("zuordnungsDatum");
				String date = df.format(d);
				String s = x + ", " + sID + ", " + kID + ", " + date +  "\n";
				fw.write(s);
				String output = x + "|" + sID + "|" + kID + "|" + date;
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
