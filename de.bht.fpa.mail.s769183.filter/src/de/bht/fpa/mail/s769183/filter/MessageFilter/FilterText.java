package de.bht.fpa.mail.s769183.filter.MessageFilter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.filter.StringCompareHelper;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class FilterText implements IFilter {

	private final String text;
	private final FilterOperator filter;
	private static Set<Message> messageList = new HashSet<Message>();

	public FilterText(String text, FilterOperator filterOperator) {
		this.text = text;
		this.filter = filterOperator;
	}

	@Override
	public Set<Message> filter(Iterable<Message> messagesToFilter) {
		if (messagesToFilter == null) {
			return new HashSet<Message>();
		}
		for (Message m : messagesToFilter) {
			if (StringCompareHelper
					.matches(m.getText(), this.text, this.filter)) {
				messageList.add(m);
			}
		}
		return messageList;
	}

	public static void showMessages(Collection<Message> messagesToShow) {
		for (Message m : messagesToShow) {
			System.out.println(m.toShortString());
			System.out.println("Amount of Messages: " + messagesToShow.size());
			System.out.println("Text: " + m.getText() + "\n");

		}
	}
}
