package de.bht.fpa.mail.s769183.fsnavigation.FileFilter;

import java.io.File;
import java.io.FileFilter;

import de.bht.fpa.mail.s769183.fsnavigation.model.MyXMLFile;

public class XMLFileFilter implements FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		if (MyXMLFile.isXML(f)) {
			return true;
		}
		return false;
	}
}
