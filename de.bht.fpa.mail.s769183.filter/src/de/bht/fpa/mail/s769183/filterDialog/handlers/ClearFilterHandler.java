package de.bht.fpa.mail.s769183.filterDialog.handlers;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s769183.maillist.view.MailListView;

public class ClearFilterHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		List<Message> savedMessages = MailListView.getSavedMessages();
		// return new NullFilter();
		return savedMessages;
	}

}
