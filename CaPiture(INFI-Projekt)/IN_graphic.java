package first;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class IN_graphic {
	
	static TextArea tables2 = new TextArea();

	static void start_IN_graphik(HBox hb) {
		VBox INvb1 = new VBox(); // große vertikale Boxen von IN
		VBox INvb2 = new VBox();

		HBox zeile1_links_IN = new HBox();
		HBox zeile2_links_IN = new HBox();
		HBox zeile3_links_IN = new HBox();

		// linke Seite
		Font f = new Font(20);
		Label abWo = new Label("Ab Wo:");
		Label tableName = new Label("Tablename:");
		TextArea abWo_area = new TextArea();
		TextArea tableName_area = new TextArea();
		Button createPicTable = new Button("create Table");
		abWo_area.setMaxSize(250, 15);
		tableName_area.setMaxSize(250, 15);
		createPicTable.setMinSize(150, 50);
		tableName.setFont(f);
		abWo.setFont(f);

		// rechte Seite
		Label existingTable2 = new Label("Existing Tables:");
		TextArea output2 = new TextArea();
		tables2.setEditable(false);
		output2.setEditable(false);
		tables2.setMaxSize(500, 250);
		output2.setMaxSize(500, 50);
		existingTable2.setFont(f);

		zeile1_links_IN.getChildren().addAll(abWo, abWo_area);
		zeile2_links_IN.getChildren().addAll(tableName, tableName_area);
		zeile3_links_IN.getChildren().add(createPicTable);

		INvb1.getChildren().addAll(zeile1_links_IN, zeile2_links_IN, zeile3_links_IN);
		INvb2.getChildren().addAll(existingTable2, tables2, output2);
		hb.getChildren().addAll(INvb1, INvb2);

		Worker.writeTablesOutOnAllTabs(tables2, MANI_graphic.tables3, OUT_graphic.tables);
				
//----------------------------------------------------------------------------------------------		
		//Buttons
		
		createPicTable.setOnAction(e -> {
			output2.setText("");
			boolean x = true;
			boolean y = true;
			if (abWo_area.getText().equals("")) {
				output2.setText("Bitte geben Sie einen Pfad ein.");
				x = false;
			}
			if (tableName_area.getText().equals("")) {
				output2.setText("Bitte geben Sie einene Table-Namen ein.");
				y = false;
			}
			if (tableName_area.getText().equals("") && abWo_area.getText().equals("")) {
				output2.setText("Bitte fülle Sie die Felder aus.");
			}
			if (x && y) {
				IN.setAbWo(abWo_area.getText());
				IN.setTableName(tableName_area.getText());
				IN.buttonAction(tables2, output2);
			}
		});
		
		
	}
}
