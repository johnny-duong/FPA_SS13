package de.bht.fpa.mail.s769183.statusbar;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;

@SuppressWarnings("restriction")
public class Statusbar implements IStartup {

	@Override
	public void earlyStartup() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null) {
		}

		workbench.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();

				if (window != null) {
					WorkbenchWindow w = (WorkbenchWindow) window;
					IWorkbenchPage page = window.getActivePage();

					page.addSelectionListener(new MyStatusBarSelectionListener(w
							.getStatusLineManager()));
				}
			}
		});

	}
}
