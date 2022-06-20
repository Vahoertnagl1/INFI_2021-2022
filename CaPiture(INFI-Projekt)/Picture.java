package first;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

public class Picture {
	private int pid;
	private File f;
	private String parentPath;
	private String name;
	private String extension;
	private long lastModTime;
	private long fileSize;

	public Picture(File f) {
		this.f = f;
		this.parentPath = f.getParent();
		this.name = f.getName();
		this.lastModTime = f.lastModified();

		if (f.getPath().lastIndexOf('.') > 0) {
			this.extension = f.getPath().substring(f.getPath().lastIndexOf('.') + 1);
		}

		try {
			this.fileSize = Files.size(Paths.get(f.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Picture(int pid, String parentPath, String name, String ext, long fileSize, long lastModTime) {
		this.pid = pid;
		this.parentPath = parentPath;
		this.name = name;
		this.extension = ext;
		this.fileSize = fileSize;
		this.lastModTime = lastModTime;

	}

	public void deletePic(String tableName) {
		File nf = new File(getFullPath());
		f = nf;
		f.delete();
		SQL.deletePicture(pid, tableName);

	}

	public void renamePic(String tableName, String newName) {
		File nf = new File(parentPath + File.separator + newName + "." + extension);
		File nnf = new File(getFullPath());
		f = nnf;
		f.renameTo(nf);
		SQL.updateName(pid, newName, extension, tableName);
		name = newName + "." + extension;
		System.out.println(name);
	}

	public String getFullPath() {
		return parentPath + File.separator + name;
	}

	public void movePicTo(String newPath, String tableName) {
		Path copiedToPath = Paths.get(newPath);
		Path originPath = Paths.get(getFullPath());
		try {
			Files.move(originPath, copiedToPath, StandardCopyOption.REPLACE_EXISTING);
			SQL.updateParentPath(pid, newPath, tableName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getParentPath() {
		return parentPath;
	}

	public String getName() {
		return name;
	}

	public String getExtension() {
		return extension;
	}

	public long getLastModTime() {
		return lastModTime;
	}

	public long getFileSize() {
		return fileSize;
	}

	public int getPid() {
		return pid;
	}

	public Date getRealDate() {
		Date d = new Date(lastModTime);
		return d;
	}
}