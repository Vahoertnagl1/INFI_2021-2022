package first;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Worker extends Application {

	static String url = "jdbc:mysql://localhost:3306/CaPiture";
	static String user = "vahoertnagl";
	static String pass = "ahoi12345"; // pass ist gewollt leer

	public static void main(String[] args) {
			
			SQL.init(url, user, pass);
			launch(args);
			SQL.closeConn();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		TabPane tabPane = new TabPane();
		BorderPane root = new BorderPane();

		HBox IN_hb = new HBox();
		HBox MANI_hb = new HBox();
		HBox OUT_hb = new HBox();

		IN_graphic.start_IN_graphik(IN_hb);
		MANI_graphic.start_MANI_graphik(MANI_hb);
		OUT_graphic.start_OUT_graphik(OUT_hb);

		Tab tab1 = new Tab("IN");
		Tab tab2 = new Tab("MANI");
		Tab tab3 = new Tab("OUT");

		tab1.setContent(IN_hb);
		tab2.setContent(MANI_hb);
		tab3.setContent(OUT_hb);
		tabPane.getTabs().add(tab1);
		tabPane.getTabs().add(tab2);
		tabPane.getTabs().add(tab3);
		tab1.setClosable(false);
		tab2.setClosable(false);
		tab3.setClosable(false);

		VBox vb = new VBox(tabPane);
		Scene s = new Scene(vb, 1000, 850);
		root.setMinSize(1500, 1500);
		root.setVisible(true);
		arg0.setScene(s);
		arg0.show();
	}

	static void writeTablesOutOnAllTabs(TextArea area1, TextArea area2, TextArea area3) {
		area1.setText("");
		area2.setText("");
		area3.setText("");
		ArrayList<String> as = SQL.getExistingTables();
		for (String s : as) {
			area1.appendText(s + "\n");
			area2.appendText(s + "\n");
			area3.appendText(s + "\n");
		}
	}
	
	static void textTimer(Button b, String text, int t) {
		class MyTimerTask extends TimerTask {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						b.setText(text);
					}
				});
			}
		}
		Timer timer = new Timer();
		timer.schedule(new MyTimerTask(), t);
	}

}
