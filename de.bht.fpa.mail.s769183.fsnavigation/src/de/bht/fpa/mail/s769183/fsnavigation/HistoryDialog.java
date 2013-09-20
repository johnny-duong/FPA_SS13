package de.bht.fpa.mail.s769183.fsnavigation;

import java.util.ArrayList;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;

import de.bht.fpa.mail.s769183.fsnavigation.handlers.SaveFileHandler;

public class HistoryDialog extends SelectionDialog {

	private ListViewer listViewer;
	private ArrayList<String> dirList = new ArrayList<String>();
	private final SaveFileHandler sfh = new SaveFileHandler();
	private final int heightHint = 15;
	private final int widthHint = 55;

	public HistoryDialog(Shell parentShell) {
		super(parentShell);
	}

	public void setInput(ArrayList<String> list) {
		this.dirList = list;
	}

	public ListViewer getListViewer() {
		return this.listViewer;
	}

	@Override
	protected org.eclipse.swt.widgets.Control createDialogArea(
			Composite container) {
		Composite parent = (Composite) super.createDialogArea(container);
		listViewer = new ListViewer(parent, getListStyle());
		// listViewer.setContentProvider(contentProvider);
		List list = listViewer.getList();
		// listViewer.setLabelProvider(labelProvider);
		dirList = sfh.loadHistory();

		for (String s : dirList) {
			list.add(s);
		}

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = convertWidthInCharsToPixels(widthHint);
		gd.heightHint = convertHeightInCharsToPixels(heightHint);
		list.setLayoutData(gd);
		applyDialogFont(parent);
		//
		// Button selectBtn = new Button(parent, getShellStyle());
		// selectBtn.setText("Select");

		return parent;
	}

	protected int getListStyle() {
		return SWT.SINGLE | SWT.H_SCROLL | SWT.BORDER;
	}
}
