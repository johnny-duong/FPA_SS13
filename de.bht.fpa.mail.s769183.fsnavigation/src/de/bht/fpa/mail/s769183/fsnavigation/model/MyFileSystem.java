package de.bht.fpa.mail.s769183.fsnavigation.model;

import java.io.File;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s000000.common.mail.messagedetails.IMailProviderService;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public abstract class MyFileSystem implements IFileSystem, IMailProviderService {

	protected final File file;
	protected final File[] children;

	public MyFileSystem(String path) {
		file = new File(path);
		children = file.listFiles();
	}

	@Override
	public abstract boolean hasChildren();

	@Override
	public abstract MyFileSystem[] getChildren();

	@Override
	public abstract List<Message> getMessages();

	@Override
	public abstract Image getImage();

	// public abstract MyFileSystem[] getMessage();
	public String getName() {
		return file.getName();
	}

	public String getPath() {
		return file.getPath();
	}

}
