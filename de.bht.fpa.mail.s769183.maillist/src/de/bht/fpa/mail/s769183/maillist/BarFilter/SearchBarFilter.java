package de.bht.fpa.mail.s769183.maillist.BarFilter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;

public class SearchBarFilter extends ViewerFilter {

	private String searchRequest;

	public String getSearchRequest() {
		return searchRequest;
	}

	public void setSearchRequest(String text) {
		searchRequest = ".*" + text + ".*";
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		Message message = (Message) element;

		if (searchRequest == null || searchRequest.length() == 0) {
			return true;
		} else if (message.getSent().toString().matches(searchRequest)) {
			return true;
		} else if (message.getSender().getEmail().matches(searchRequest)) {
			return true;
		} else if (message.getSender().getPersonal().matches(searchRequest)) {
			return true;
		} else if (message.getReceived().toString().matches(searchRequest)) {
			return true;
		} else if (message.getText().matches(searchRequest)) {
			return true;
		}
		for (Recipient r : message.getRecipients()) {
			if (r.getEmail().matches(searchRequest)) {
				return true;
			} else if (r.getPersonal().matches(searchRequest)) {
				return true;
			}

		}
		return false;
	}
}
