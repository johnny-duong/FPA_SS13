package de.bht.fpa.mail.s769183.filter.MessageFilter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;

public class FilterUnion implements IFilter {

	private final List<IFilter> filters;

	public FilterUnion(List<IFilter> filterList) {
		this.filters = filterList;
	}

	@Override
	public Set<Message> filter(Iterable<Message> messagesToFilter) {
		Set<Message> finalMessageList = new HashSet<Message>();
		for (IFilter f : filters) {
			Set<Message> currentMessageList = f.filter(messagesToFilter);
			for (Message m : currentMessageList) {
				finalMessageList.add(m);
			}
		}
		return finalMessageList;
	}

	public static void showMessages(Collection<Message> messagesToShow) {
		for (Message m : messagesToShow) {
			System.out.println(m.toShortString());
			System.out.println("Amount of Messages: " + messagesToShow.size());
			System.out.println("Importance: " + m.getImportance());
			System.out.println("Read: " + m.isRead());
			System.out.println("Sender Name: " + m.getSender().getPersonal());
			System.out.println("Sender Email: " + m.getSender().getEmail());
			for (Recipient r : m.getRecipients()) {
				System.out.println("Recipients Name: " + r.getPersonal());
				System.out.println("Recipients Email: " + r.getEmail());
			}
			System.out.println("Subject: " + m.getSubject());
			System.out.println("Text: " + m.getText() + "\n");
		}
	}
}
