package de.bht.fpa.mail.s769183.fsnavigation.view;

import java.io.File;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s769183.fsnavigation.handlers.SaveFileHandler;
import de.bht.fpa.mail.s769183.fsnavigation.model.MyDirectory;
import de.bht.fpa.mail.s769183.fsnavigation.provider.ViewContentProvider;
import de.bht.fpa.mail.s769183.fsnavigation.provider.ViewLabelProvider;

public class NavigationView extends ViewPart {

	private static TreeViewer tViewer;
	private final String userHome = System.getProperty("user.home");
	private final File f = new File("baseDir.sf");
	private final SaveFileHandler sfh = new SaveFileHandler();

	@Override
	public void createPartControl(Composite parent) {

		tViewer = new TreeViewer(parent);
		tViewer.setContentProvider(new ViewContentProvider());
		tViewer.setLabelProvider(new ViewLabelProvider());
		// tViewer.addSelectionChangedListener(new ISelectionChangedListener() {
		//
		// @Override
		// public void selectionChanged(SelectionChangedEvent event) {
		// if (event.getSelection().isEmpty()) {
		// return;
		// }
		// if (event.getSelection() instanceof IStructuredSelection) {
		// IStructuredSelection selection = (IStructuredSelection) event
		// .getSelection();
		// for (Iterator iterator = selection.iterator(); iterator
		// .hasNext();) {
		// MyDirectory dir = (MyDirectory) iterator.next();
		// System.out.println("\n" + "Selected Directory: "
		// + dir.getPath());
		// for (Message m : dir.getMessage()) {
		// System.out.println(m.toString());
		// }
		// }
		// }
		// }
		// });

		if (f.exists()) {
			tViewer.setInput(new MyDirectory(sfh.loadBaseDir()));
		} else {
			tViewer.setInput(new MyDirectory(userHome));

		}
	}

	@Override
	public void setFocus() {
		tViewer.getControl().setFocus();
		getSite().setSelectionProvider(tViewer);
	}

	public static TreeViewer getTreeViewer() {
		return tViewer;
	}

}
