package de.bht.fpa.mail.s769183.filter.MessageFilter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class FilterSubject implements IFilter {

	private final String subject;
	private final FilterOperator filter;
	private static Set<Message> messageList = new HashSet<Message>();

	public FilterSubject(String subject, FilterOperator filterOperator) {
		this.subject = subject;
		this.filter = filterOperator;
	}

	@Override
	public Set<Message> filter(Iterable<Message> messagesToFilter) {
		if (messagesToFilter == null) {
			return new HashSet<Message>();
		}
		for (Message m : messagesToFilter) {
			if (StringCompareHelper.matches(m.getSubject(), this.subject,
					this.filter)) {
				messageList.add(m);
			}
		}
		return messageList;
	}

	public static void showMessages(Collection<Message> messagesToShow) {
		for (Message m : messagesToShow) {
			System.out.println(m.toShortString());
			System.out.println("Amount of Messages: " + messagesToShow.size());
			System.out.println("Subject: " + m.getSubject() + "\n");

		}
	}
}
