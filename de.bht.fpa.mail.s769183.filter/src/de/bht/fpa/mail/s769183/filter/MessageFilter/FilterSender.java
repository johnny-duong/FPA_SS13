package de.bht.fpa.mail.s769183.filter.MessageFilter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class FilterSender implements IFilter {

	private final String sender;
	private final FilterOperator filterOperator;
	private static Set<Message> messageList = new HashSet<Message>();

	public FilterSender(String sender, FilterOperator filterOperator) {
		this.sender = sender;
		this.filterOperator = filterOperator;
	}

	@Override
	public Set<Message> filter(Iterable<Message> messagesToFilter) {
		if (messagesToFilter == null) {
			messageList = new HashSet<Message>();
		}
		for (Message m : messagesToFilter) {
			if (StringCompareHelper.matches(m.getSender().getEmail(), sender,
					filterOperator)
					|| StringCompareHelper.matches(m.getSender().getPersonal(),
							sender, filterOperator)) {
				messageList.add(m);
			}
		}

		return messageList;
	}

	public static void showMessages(Collection<Message> messagesToShow) {
		for (Message m : messagesToShow) {
			System.out.println(m.toShortString());
			System.out.println("Amount of Messages: " + messagesToShow.size());
			System.out.println("Sender Name: " + m.getSender().getPersonal());
			System.out.println("Sender Email: " + m.getSender().getEmail()
					+ "\n");
		}
	}
}
