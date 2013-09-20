package de.bht.fpa.mail.s769183.fsnavigation.provider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.bht.fpa.mail.s769183.fsnavigation.model.MyDirectory;
import de.bht.fpa.mail.s769183.fsnavigation.model.MyFileSystem;

public class ViewContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {

		if (parentElement instanceof MyDirectory) {
			MyDirectory dir = (MyDirectory) parentElement;
			return dir.getChildren();
		}

		return new MyDirectory[0];

	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof MyFileSystem) {
			MyDirectory dir = (MyDirectory) element;
			return dir.hasChildren();
		} else {
			return false;
		}
	}
}
