package de.bht.fpa.mail.s769183.imapnavigation.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s769183.imapnavigation.provider.ImapContentProvider;
import de.bht.fpa.mail.s769183.imapnavigation.provider.ImapLabelProvider;

public class ImapView extends ViewPart implements Observer {

	private static TreeViewer imapTViewer;
	private static final CreateTestAccounts DATA = new CreateTestAccounts(
			new ArrayList<Account>());
	private static List<Account> accountList = DATA.getDummyAccounts();

	private final String DB_PATH = "C:/Users/jay/Desktop/FPA/eclipse2/db";
	private final File DB_DIRECTORY = new File(DB_PATH);

	@Override
	public void createPartControl(Composite parent) {

		try {
			DeleteDatabase.delete(DB_DIRECTORY);
		} catch (IOException e) {
			e.printStackTrace();
		}

		imapTViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		imapTViewer.setContentProvider(new ImapContentProvider());
		imapTViewer.setLabelProvider(new ImapLabelProvider());

		getSite().setSelectionProvider(imapTViewer);
		imapTViewer.setInput(accountList);

	}

	@Override
	public void setFocus() {
		imapTViewer.getControl().setFocus();
	}

	public static TreeViewer getImapTreeViewer() {
		return imapTViewer;
	}

	public static List<Account> getAccountList() {
		return accountList;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Account) {
			accountList.add((Account) arg);
		}
		imapTViewer.setInput(accountList);
	}
}
