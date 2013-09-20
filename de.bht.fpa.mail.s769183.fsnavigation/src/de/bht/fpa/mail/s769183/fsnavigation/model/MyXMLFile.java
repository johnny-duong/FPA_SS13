package de.bht.fpa.mail.s769183.fsnavigation.model;

import java.io.File;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class MyXMLFile extends MyFileSystem {

	public MyXMLFile(String path) {
		super(path);
	}

	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MyFileSystem[] getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean isXML(File f) {
		return f.getPath().endsWith(".xml");
	}

	@Override
	public List<Message> getMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getImage() {
		return null;
	}
}
