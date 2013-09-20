package de.bht.fpa.mail.s769183.imapnavigation.view;

import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Account;
import de.bht.fpa.mail.s000000.common.mail.model.builder.AccountBuilder;
import de.bht.fpa.mail.s000000.common.mail.model.builder.FolderBuilder;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;

public class CreateTestAccounts {
	private final List<Account> accountList;

	public CreateTestAccounts(List<Account> accountList) {
		this.accountList = accountList;
	}

	public List<Account> getDummyAccounts() {
		Account account1 = AccountBuilder
				.newAccountBuilder()
				.host("de.somewhere.com")
				.id(1L)
				.username("alice")
				.password("secret")
				.name("Alice-IMAP")
				.folder(FolderBuilder
						.newFolderBuilder()
						.fullName("INBOX")
						.id(2L)
						.builtMessages(
								new RandomTestDataProvider(20).getMessages())
						.folder(FolderBuilder
								.newFolderBuilder()
								.fullName("Customers")
								.id(3L)
								.builtMessages(
										new RandomTestDataProvider(30)
												.getMessages())))
				.folder(FolderBuilder
						.newFolderBuilder()
						.fullName("Sent")
						.id(4L)
						.builtMessages(
								new RandomTestDataProvider(5).getMessages()))
				.build();

		accountList.add(account1);

		Account account2 = AccountBuilder
				.newAccountBuilder()
				.host("de.elsewhere.com")
				.id(11L)
				.username("vodafone")
				.password("geheim")
				.name("Vodafone-IMAP")
				.folder(FolderBuilder
						.newFolderBuilder()
						.fullName("INBOX")
						.id(22L)
						.builtMessages(
								new RandomTestDataProvider(25).getMessages())
						.folder(FolderBuilder
								.newFolderBuilder()
								.fullName("Customers")
								.id(33L)
								.builtMessages(
										new RandomTestDataProvider(40)
												.getMessages())))
				.folder(FolderBuilder
						.newFolderBuilder()
						.fullName("Sent")
						.id(44L)
						.builtMessages(
								new RandomTestDataProvider(6).getMessages()))
				.build();
		accountList.add(account2);
		return accountList;
	}
}