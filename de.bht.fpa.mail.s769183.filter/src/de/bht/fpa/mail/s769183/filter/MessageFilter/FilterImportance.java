package de.bht.fpa.mail.s769183.filter.MessageFilter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class FilterImportance implements IFilter {

	private final Importance importance;

	// private static Set<Message> messageList = new HashSet<Message>();

	public FilterImportance(Importance importance) {
		this.importance = importance;
	}

	@Override
	public Set<Message> filter(Iterable<Message> messagesToFilter) {
		Set<Message> messageList = new HashSet<Message>();
		if (messagesToFilter == null) {
			return new HashSet<Message>();
		}
		for (Message m : messagesToFilter) {
			if (m.getImportance().equals(importance)) {
				messageList.add(m);
			}
		}
		return messageList;
	}

	public static void showMessages(Collection<Message> messagesToShow) {
		for (Message m : messagesToShow) {
			System.out.println(m.toShortString());
			System.out.println("Amount of Messages: " + messagesToShow.size());
			System.out.println("Importance: " + m.getImportance() + "\n");
		}
	}
}
