package de.bht.fpa.mail.s769183.imapnavigation.synchronize;

import java.util.Observable;

import de.bht.fpa.mail.s000000.common.mail.model.Account;

public class AccountObservable extends Observable {

	private static AccountObservable folderInstance = new AccountObservable();

	public static AccountObservable getInstance() {
		return folderInstance;
	}

	public void setAccount(Account account) {
		setChanged();
		notifyObservers(account);
	}
}