package first;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MANI_graphic {
	
	static TextArea tables3 = new TextArea();
	static String defaultImage = "/home/hoertnagl/Dokumente/start.jpeg"; // put into config file! //Valle do m�sche onfoch an pfad fi irgend an Bild drin t�n
	static ImageView iv1;
	
	static void start_MANI_graphik(HBox hb) throws FileNotFoundException {
		VBox MANIvb1 = new VBox(); // große vertikale Boxen von MANI
		VBox MANIvb2 = new VBox();

		HBox zeile1_links_MANI = new HBox();
		HBox zeile2_links_MANI = new HBox();
		HBox zeile3_links_MANI = new HBox();
		HBox zeile4_links_MANI = new HBox();
		HBox zeile5_links_MANI = new HBox();
		HBox zeile6_links_MANI = new HBox();
		HBox zeile7_links_MANI = new HBox();
		HBox zeile1_rechts_MANI = new HBox();
		HBox zeile2_rechts_MANI = new HBox();

		// linke Seite
		Font f = new Font(20);
		Label existingTable3 = new Label("Existing Tables:");
		Button select_table = new Button("Select Tabelle:");
		TextArea select_table_area = new TextArea();
		Button rename = new Button("Rename to:");
		TextArea rename_area = new TextArea();
		Button restore = new Button("Restore to:");
		TextArea restore_area = new TextArea();
		Button delete = new Button("DELETE");
		TextArea output3 = new TextArea();
		delete.setMinSize(80, 40);
		select_table_area.setMaxSize(150, 15);
		rename_area.setMaxSize(150, 15);
		restore_area.setMaxSize(150, 15);
		tables3.setMaxSize(500, 250);
		output3.setMaxSize(500, 50);
		select_table.setFont(f);
		rename.setFont(f);
		restore.setFont(f);
		existingTable3.setFont(f);
		tables3.setEditable(false);
		output3.setEditable(false);

		// rechte Seite
		Button links = new Button("<--");
		Button rechts = new Button("-->");
		Image i = new Image(new FileInputStream(defaultImage));
		iv1 = new ImageView(i);
		iv1.setFitWidth(500);
		iv1.setFitHeight(300);

		zeile1_links_MANI.getChildren().addAll(select_table, select_table_area);
		zeile2_links_MANI.getChildren().addAll(rename, rename_area);
		zeile3_links_MANI.getChildren().addAll(restore, restore_area);
		zeile4_links_MANI.getChildren().add(delete);
		zeile5_links_MANI.getChildren().add(existingTable3);
		zeile6_links_MANI.getChildren().add(tables3);
		zeile7_links_MANI.getChildren().add(output3);

		zeile1_rechts_MANI.getChildren().addAll(links, rechts);
		zeile2_rechts_MANI.getChildren().add(iv1);

		MANIvb1.getChildren().addAll(zeile1_links_MANI, zeile2_links_MANI, zeile3_links_MANI, zeile4_links_MANI,
				zeile5_links_MANI, zeile6_links_MANI, zeile7_links_MANI);
		MANIvb2.getChildren().addAll(zeile2_rechts_MANI,zeile1_rechts_MANI);
		hb.getChildren().addAll(MANIvb1, MANIvb2);

//----------------------------------------------------------------------------------------------		
		// Buttons

		select_table.setOnAction(e -> {
			if (SQL.existingTables(select_table_area.getText())) {
				try {
					MANI.selectTable(select_table_area.getText());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

		rename.setOnAction(e -> {
			MANI.renameAction(rename_area.getText());
		});

		restore.setOnAction(e -> {
			MANI.restoreAction(restore_area.getText());
		});
		
		links.setOnAction(e ->{
			try {
				MANI.selectPrevious();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		
		rechts.setOnAction(e ->{
			try {
				MANI.selectNext();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		delete.setOnAction(e ->{
			try {
				MANI.deleteAction();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});

	}
	static void showPic(Picture p) throws FileNotFoundException {
		String path = p.getFullPath();
		Image image = new Image(new FileInputStream(path)); 
		iv1.setImage(image);
	}
	static void noPicLeft() throws FileNotFoundException {
		Image image = new Image(new FileInputStream(defaultImage));
		iv1.setImage(image);  
	}

}
