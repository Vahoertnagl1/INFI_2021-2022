package first;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class OUT_graphic {
	
	static TextArea tables = new TextArea();

	static void start_OUT_graphik(HBox hb) {
		Font f = new Font(20);
		Label path = new Label("Pfad:");
		Label name = new Label("Dateiname:");
		Label table = new Label("Tabellenname:");
		Label dateiformat = new Label("Dateiformat:");
		Label json = new Label("JSON");
		Label csv = new Label("CSV");
		path.setFont(f);
		name.setFont(f);
		table.setFont(f);
		dateiformat.setFont(f);
		json.setFont(f);
		csv.setFont(f);

		VBox OUTvb1 = new VBox(); // große vertikale Boxen von OUT
		VBox OUTvb2 = new VBox();

		HBox zeile1_links = new HBox();
		HBox zeile2_links = new HBox();
		HBox zeile3_links = new HBox();
		HBox zeile4_links = new HBox();
		HBox zeile5_links = new HBox();
		HBox zeile6_links = new HBox();
		HBox zeile7_links = new HBox();

		// linke Seite
		TextArea pathArea = new TextArea();
		TextArea nameArea = new TextArea();
		TextArea tableArea = new TextArea();
		CheckBox csvBox = new CheckBox();
		CheckBox jsonBox = new CheckBox();
		Button createFile = new Button("create File");
		pathArea.setMaxSize(250, 15);
		nameArea.setMaxSize(250, 15);
		tableArea.setMaxSize(250, 15);
		createFile.setMinSize(150, 50);

		// rechte Seite
		Label existingTable = new Label("Existing Tables:");
		TextArea output = new TextArea();
		output.setEditable(false);
		tables.setEditable(false);
		output.setMaxSize(500, 50);
		tables.setMaxSize(500, 250);
		existingTable.setFont(f);

		zeile1_links.getChildren().addAll(path, pathArea);
		zeile2_links.getChildren().addAll(name, nameArea);
		zeile3_links.getChildren().addAll(table, tableArea);
		zeile4_links.getChildren().add(dateiformat);
		zeile5_links.getChildren().addAll(csvBox, csv);
		zeile6_links.getChildren().addAll(jsonBox, json);
		zeile7_links.getChildren().add(createFile);

		OUTvb1.getChildren().addAll(zeile1_links, zeile2_links, zeile3_links, zeile4_links, zeile5_links, zeile6_links, zeile7_links);
		OUTvb2.getChildren().addAll(existingTable, tables, output);
		hb.getChildren().addAll(OUTvb1, OUTvb2);

		
//----------------------------------------------------------------------------------------------		
		//Buttons
		
		createFile.setOnAction(e -> {
			output.setText("");
			OUT.setPath(pathArea.getText());
			OUT.setDateiname(nameArea.getText());
			OUT.setCSV(csvBox.isSelected());
			OUT.setJSON(jsonBox.isSelected());
			OUT.setTable(tableArea.getText());
			if (SQL.existingTables(tableArea.getText())) {
				if (csvBox.isSelected()) {
					OUT.writeCSV();
					//Worker.textTimer(createFile, "DONE", 2000);
				}
				if (jsonBox.isSelected()) {
					OUT.writeJSON();
					//Worker.textTimer(createFile, "DONE", 2000);
				}
				if (!csvBox.isSelected() && !jsonBox.isSelected()) {
					output.setText("Bitte wähle eine Dateiformat --> csv, json.");
				}
			}
			else {
				output.setText("Die angegebene Table existiert nicht.");
			}		
		});
	}
}
