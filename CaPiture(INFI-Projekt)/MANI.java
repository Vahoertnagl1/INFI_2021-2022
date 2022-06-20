package first;

import java.io.FileNotFoundException;

public class MANI {
	// Variablen der MANI Seite
	private static Pictures ps;
	private static int picIndex = 0;
	private static boolean anyTableSelected = false;
	private static String tableName = "";

	static void selectTable(String selectedTable) throws FileNotFoundException { // wird ausgef�hrt sobald auf der Mani Seite der Button select Table
		tableName = selectedTable; // gedr�ckt wird
		ps = SQL.selectAll(tableName);
		anyTableSelected = true;
		picIndex = 0;
		MANI_graphic.showPic(ps.getPicture(picIndex));
		System.out.println("1");
	}

	static void selectPrevious() throws FileNotFoundException {
		if (anyTableSelected) {
			if (picIndex > 0) {
				picIndex--;
			}
			MANI_graphic.showPic(ps.getPicture(picIndex));
		}
	}

	static void selectNext() throws FileNotFoundException {
		if (anyTableSelected) {
			if (picIndex < ps.getLength() - 1) {
				picIndex++;
			}
			MANI_graphic.showPic(ps.getPicture(picIndex));
		}
	}

	static void renameAction(String newName) {
		if (anyTableSelected) {
			ps.getPicture(picIndex).renamePic(tableName, newName);
			System.out.println(newName);
		}
	}

	static void restoreAction(String destdir) {
		if (anyTableSelected) {
			ps.getPicture(picIndex).movePicTo(destdir, tableName);
			//TODO find out why FileSystemException is thrown (File already in use)   !?!?
		}
	}

	static void deleteAction() throws FileNotFoundException {
		if (anyTableSelected) {
			ps.getPicture(picIndex).deletePic(tableName);
			ps.removePicFromIndex(picIndex);
			if (picIndex > ps.getLength() - 1) {
				picIndex--;
			}
			if (ps.getLength() > 0) {
				MANI_graphic.showPic(ps.getPicture(picIndex));
			}else {
				MANI_graphic.noPicLeft();
				anyTableSelected = false;
			}

		}
	}
}
