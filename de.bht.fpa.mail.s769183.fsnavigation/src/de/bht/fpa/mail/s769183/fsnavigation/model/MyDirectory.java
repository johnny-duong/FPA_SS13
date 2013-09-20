package de.bht.fpa.mail.s769183.fsnavigation.model;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXB;

import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s769183.fsnavigation.Activator;
import de.bht.fpa.mail.s769183.fsnavigation.FileFilter.XMLFileFilter;

public class MyDirectory extends MyFileSystem {

	private final FileFilter ff = new XMLFileFilter();

	public MyDirectory(String path) {

		super(path);

		// if (path == null) {
		// throw new IllegalArgumentException("file was 'null'");
		// }
		// if (ff.accept(new File(path))) {
		// throw new IllegalArgumentException();
		// }
	}

	@Override
	public boolean hasChildren() {

		if (children.length > 0) {
			return true;
		}
		return false;

	}

	@Override
	public MyFileSystem[] getChildren() {
		List<MyFileSystem> list = new ArrayList<MyFileSystem>();

		for (File f : children) {
			if (f.listFiles() != null) {
				list.add(new MyDirectory(f.getAbsolutePath()));
			}
		}

		MyFileSystem[] children = new MyFileSystem[list.size()];

		return list.toArray(children);
	}

	@Override
	public List<Message> getMessages() {
		List<Message> result = new ArrayList<Message>();
		for (File f : children) {
			if (ff.accept(f)) {
				if (MyXMLFile.isXML(f)) {
					if (getMessage(f) != null) {
						result.add(getMessage(f));
					}
				}
			}
		}
		return result;
	}

	public Message getMessage(File f) {
		Message m = JAXB.unmarshal(f, Message.class);
		try {
			if (m != null && m.getId() != null) {
				return m;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Image getImage() {
		Image img = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
				"icons/folder open.ico").createImage();
		return img;
	}
} // Ende
