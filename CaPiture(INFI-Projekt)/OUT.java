package first;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;

public class OUT {

	// Variablen der OUT Seite
	static String path_fx = ""; // Pfad, wo das File hingespeichert werden soll
	static String dateiname_fx = ""; // Name, wie das File heißen soll
	static boolean csv_fx;
	static boolean json_fx;
	static String table_fx = ""; // welcher Table soll in File geschrieben werden?

	static void setPath(String s) {
		path_fx = s;
	}
	
	static void setDateiname(String s) {
		dateiname_fx = s;
	}
	
	static void setCSV(boolean s) {
		csv_fx = s;
	}

	static void setJSON(boolean s) {
		json_fx = s;
	}
	
	static void setTable(String s) {
		table_fx = s;
	}
	
	static boolean getCSV() {
		return csv_fx;
	}
	
	static boolean getJSON() {
		return json_fx;
	}
	
	static void writeCSV() {
		try {
			String gesPath = path_fx + File.separator + dateiname_fx + ".csv";
			File f = new File(gesPath);
			PrintWriter writer = new PrintWriter(f);
			String header = "parentPath, name, extension, lastModTime, fileSize\n";
			writer.write(header);
			writer.flush();
			Pictures ps = SQL.selectAll(table_fx); // bekommt alle Pictures
			for (Picture pic : ps.getPictures()) {
				String parentPath = pic.getParentPath();
				String name = pic.getName();
				String extension = pic.getExtension();
				long lastModTime = pic.getLastModTime();
				long fileSize =  pic.getFileSize();
				String string = parentPath + ", " + name + ", " + extension + ", " + lastModTime + ", " + fileSize + "\n";
				writer.write(string);
				writer.flush();
			}
			writer.close();
			System.out.printf("CSV-File wurde erstellt -> %s\n", gesPath);
		} catch (FileNotFoundException e) {
			System.out.println("File wurde nicht gefunden.");
			e.printStackTrace();
		}
	}
	
	static void writeJSON() {
		try {
			String gesPath = path_fx + File.separator + dateiname_fx + ".json";
			File f = new File(gesPath);
			PrintWriter writer = new PrintWriter(f);
			JSONObject json = new JSONObject();
			Pictures ps = SQL.selectAll(table_fx); // bekommt alle Pictures
			String s = "";
			for (Picture pic : ps.getPictures()) {
				json.put("pid", pic.getPid());
				json.put("parentPath", pic.getParentPath());
				json.put("name", pic.getName());
				json.put("datum", pic.getLastModTime());
				json.put("ext", pic.getExtension());
				json.put("fileSize", pic.getFileSize());
				s = s + json;
				writer.write(s);
				writer.flush();
				s = "";
			}
			writer.close();
			System.out.printf("JSON-File wurde erstellt -> %s\n", gesPath);
		} catch (FileNotFoundException e) {
			System.out.println("File wurde nicht gefunden.");
			e.printStackTrace();
		}
	}
	
	
}
