package first;

import javafx.scene.control.TextArea;

public class IN {

	// Variablen der IN Seite
	static String abWo = ""; // ab welchem Pfad sollen alle Bilder gesucht werden...
	static String tableName = "";
	
	static void setAbWo(String s) {
		abWo = s;
	} 
	
	static void setTableName(String s) {
		tableName = s;
	}
	
	static void buttonAction(TextArea area, TextArea output) {
		if (SQL.checkTables(tableName)) {
			SQL.createPictureTable(tableName); // table wird erstellt
			SQL.insertCustomTableTable(tableName, area); // name der table wird in CustomTable eingetragen
			Worker.writeTablesOutOnAllTabs(IN_graphic.tables2, MANI_graphic.tables3, OUT_graphic.tables); // CustomTables werden eusgelesen
			PictureFileTree.getAllPicturesFrom(abWo); // speichert die Pics in eine ArrayList
			SQL.insertIntoDB(PictureFileTree.createAndStorePictureObjects(), tableName); // insertiert die Pics in die DB
		} else {
			output.setText("Der gewählte Tablename wird bereits verwendet.");
		}
	}
	
}
