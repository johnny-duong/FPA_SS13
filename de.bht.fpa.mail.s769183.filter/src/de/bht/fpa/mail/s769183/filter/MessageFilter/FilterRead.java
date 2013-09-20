package de.bht.fpa.mail.s769183.filter.MessageFilter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;

public class FilterRead implements IFilter {

	private final boolean isRead;
	private static Set<Message> messageList = new HashSet<Message>();

	public FilterRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public Set<Message> filter(Iterable<Message> messagesToFilter) {
		if (messagesToFilter != null) {
			for (Message m : messagesToFilter) {
				if (m.isRead() == isRead) {
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
			System.out.println("Read: " + m.isRead() + "\n");
		}
	}
}
