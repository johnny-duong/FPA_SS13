package de.bht.fpa.mail.s769183.statusbar;

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import de.bht.fpa.mail.s000000.common.rcp.selection.SelectionHelper;
import de.bht.fpa.mail.s769183.fsnavigation.model.MyDirectory;
import de.bht.fpa.mail.s769183.fsnavigation.model.MyFileSystem;

public class MyStatusBarSelectionListener implements ISelectionListener {

	private String dirPath;
	private final IStatusLineManager manager;

	public MyStatusBarSelectionListener(final IStatusLineManager manager) {
		this.manager = manager;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		MyFileSystem fs = SelectionHelper.handleStructuredSelection(selection,
				MyFileSystem.class);
		if (fs instanceof MyDirectory) {
			dirPath = fs.getPath();
			manager.setMessage("Directory " + dirPath + " was selected.");
		}
	}
}
