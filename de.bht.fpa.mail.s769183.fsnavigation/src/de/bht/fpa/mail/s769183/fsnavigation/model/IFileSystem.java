package de.bht.fpa.mail.s769183.fsnavigation.model;

import org.eclipse.swt.graphics.Image;

interface IFileSystem {

	boolean hasChildren();

	IFileSystem[] getChildren();

	Image getImage();
	// public IFileSystem[] getMessage();

}
