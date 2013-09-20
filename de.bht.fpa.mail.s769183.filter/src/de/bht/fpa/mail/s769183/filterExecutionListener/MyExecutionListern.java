package de.bht.fpa.mail.s769183.filterExecutionListener;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s769183.maillist.view.MailListView;

public class MyExecutionListern implements IExecutionListener {

	@Override
	public void notHandled(String commandId, NotHandledException exception) {

	}

	@Override
	public void postExecuteFailure(String commandId,
			ExecutionException exception) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void postExecuteSuccess(String commandId, Object returnValue) {
		List<Message> messagesToFilter = new ArrayList<Message>();
		List<Message> result = new ArrayList<Message>();

		if (commandId
				.equals("de.bht.fpa.mail.s769183.filter.commands.filterCommand")) {

			messagesToFilter = (List<Message>) MailListView
					.getTableViewBuilder().getTableViewer().getInput();
			// MailListView.getMessagesToFilter();
			for (Message m : messagesToFilter) {
				System.out.println(m.toShortString());
				// result.add(m);
			}
			if (returnValue instanceof IFilter) {
				System.out.println(returnValue.toString());
				IFilter filter = (IFilter) returnValue;
				result.clear();
				for (Message m : filter.filter(messagesToFilter)) {
					System.out.println(m.toShortString());
					result.add(m);
				}
				MailListView.setMessagesToFilter(result);
				System.out.println(MailListView.getMessagesToFilter().size());
				MailListView.getTableViewBuilder().setInput(result);
				MailListView.getTableViewer().refresh();
			}

		} else if (commandId
				.equals("de.bht.fpa.mail.s769183.filter.commands.ClearFilter")) {
			if (returnValue instanceof List<?>) {
				result = (List<Message>) returnValue;
				MailListView.setMessagesToFilter(result);
				MailListView.getTableViewBuilder().setInput(result);
				MailListView.getTableViewer().refresh();
			}
		}
	}

	@Override
	public void preExecute(String commandId, ExecutionEvent event) {

	}

}
