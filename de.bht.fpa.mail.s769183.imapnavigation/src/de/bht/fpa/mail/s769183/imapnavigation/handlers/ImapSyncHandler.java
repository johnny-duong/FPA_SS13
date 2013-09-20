package de.bht.fpa.mail.s769183.imapnavigation.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.ui.progress.UIJob;

import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s769183.imapnavigation.synchronize.SyncAccount;
import de.bht.fpa.mail.s769183.imapnavigation.view.ImapView;

public class ImapSyncHandler extends AbstractHandler {

	private final Account acc = SyncAccount.createAcc();
	private final List<Account> accountList = ImapView.getAccountList();

	public ImapSyncHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Job job = new Job("Synchronized") {

			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				try {
					ImapHelper.syncAllFoldersToAccount(acc, monitor);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return Status.OK_STATUS;
			}

		};

		job.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(final IJobChangeEvent event) {
				UIJob uiJob = new UIJob("Update UI") {

					@Override
					public IStatus runInUIThread(final IProgressMonitor monitor) {
						System.out.println(acc.getName());
						accountList.add(acc);
						ImapView.getImapTreeViewer().setInput(accountList);
						return Status.OK_STATUS;
					}
				};
				uiJob.schedule();
			}
		});

		job.schedule();
		return null;
	}
}
