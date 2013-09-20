package de.bht.fpa.mail.s769183.imapnavigation.synchronize;

import de.bht.fpa.mail.s000000.common.mail.imapsync.ImapHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.builder.AccountBuilder;

public class SyncAccount {

	private static Account syncAcc;

	public static Account createAcc() {
		syncAcc = ImapHelper.getAccount("Google Mail");
		AccountBuilder b = AccountBuilder.newAccountBuilder();

		syncAcc = b.name("Google Mail").host("imap.gmail.com")
				.username("bhtfpa@googlemail.com")
				.password("B-BgxkT_anr2bubbyTLM").build();

		return syncAcc;
	}

}
