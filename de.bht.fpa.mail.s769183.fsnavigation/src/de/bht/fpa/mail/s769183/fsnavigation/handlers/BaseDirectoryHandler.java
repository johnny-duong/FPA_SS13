package de.bht.fpa.mail.s769183.fsnavigation.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.bht.fpa.mail.s769183.fsnavigation.model.MyDirectory;
import de.bht.fpa.mail.s769183.fsnavigation.model.MyFileSystem;
import de.bht.fpa.mail.s769183.fsnavigation.view.NavigationView;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class BaseDirectoryHandler extends AbstractHandler {

	private final SaveFileHandler sfh = new SaveFileHandler();

	/**
	 * The constructor.
	 */
	public BaseDirectoryHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);

		DirectoryDialog dirDialog = new DirectoryDialog(window.getShell());

		dirDialog.setMessage("Please select a base directory: ");
		dirDialog.setText("Set Base Directory");

		String dirPath = dirDialog.open();

		if (dirPath != null) {
			MyFileSystem fs = new MyDirectory(dirPath);
			NavigationView.getTreeViewer().setInput(fs);
			sfh.savePath(dirPath);
		}

		return null;
	}
}
