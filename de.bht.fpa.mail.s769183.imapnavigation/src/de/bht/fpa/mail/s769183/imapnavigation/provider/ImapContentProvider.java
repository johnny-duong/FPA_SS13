package de.bht.fpa.mail.s769183.imapnavigation.provider;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.Folder;

public class ImapContentProvider implements ITreeContentProvider {

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

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof List) {
			return ((List<Account>) parentElement).toArray();
		}
		if (parentElement instanceof Account) {
			return ((Account) parentElement).getFolders()
					.toArray(new Folder[0]);
		} else {
			return ((Folder) parentElement).getFolders().toArray(new Folder[0]);
		}
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Account) {
			Account account = (Account) element;
			if (account.getFolders().isEmpty()) {
				return false;
			}
		} else if (element instanceof Folder) {
			Folder folder = (Folder) element;
			if (folder.getFolders().isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
