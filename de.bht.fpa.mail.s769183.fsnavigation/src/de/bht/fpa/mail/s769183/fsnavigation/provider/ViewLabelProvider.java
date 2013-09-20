package de.bht.fpa.mail.s769183.fsnavigation.provider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import de.bht.fpa.mail.s769183.fsnavigation.model.MyFileSystem;

public class ViewLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		return ((MyFileSystem) element).getName();
	}

	@Override
	public Image getImage(Object element) {
		return ((MyFileSystem) element).getImage();
	}
}
