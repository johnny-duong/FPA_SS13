package de.bht.fpa.mail.s769183.filterExecutionListener;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

public class MyStartup implements IStartup {

	@Override
	public void earlyStartup() {
		final IWorkbench workbench = PlatformUI.getWorkbench();

		ICommandService cmdService = (ICommandService) workbench
				.getService(ICommandService.class);
		cmdService.addExecutionListener(new MyExecutionListern());
	}

}
