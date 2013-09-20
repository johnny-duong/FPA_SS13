package de.bht.fpa.mail.s769183.filter.MessageFilter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;

public class FilterRecipient implements IFilter {

	private final String recipient;
	private final FilterOperator filter;
	private static Set<Message> messageList = new HashSet<Message>();

	public FilterRecipient(String recipient, FilterOperator filterOperator) {
		this.recipient = recipient;
		this.filter = filterOperator;
	}

	@Override
	public Set<Message> filter(Iterable<Message> messagesToFilter) {
		if (messagesToFilter == null) {
			return new HashSet<Message>();
		}
		for (Message m : messagesToFilter) {
			for (Recipient r : m.getRecipients()) {
				if (StringCompareHelper.matches(r.getEmail(), this.recipient,
						this.filter)) {
					messageList.add(m);
				} else if (StringCompareHelper.matches(r.getPersonal(),
						this.recipient, this.filter)) {
					messageList.add(m);
				}
			}
		}
		return messageList;
	}

	public static void showMessages(Collection<Message> messagesToShow) {
		for (Message m : messagesToShow) {
			System.out.println(m.toShortString());
			System.out.println("Amount of Messages: " + messagesToShow.size());
			for (Recipient r : m.getRecipients()) {
				System.out.println("Recipients Name: " + r.getPersonal());
				System.out.println("Recipients Email: " + r.getEmail() + "\n");
			}
		}
	}
}
