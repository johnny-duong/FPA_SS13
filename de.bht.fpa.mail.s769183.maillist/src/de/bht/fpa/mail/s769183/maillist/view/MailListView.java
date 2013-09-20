package de.bht.fpa.mail.s769183.maillist.view;

import java.text.SimpleDateFormat;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import de.bht.fpa.mail.s000000.common.mail.messagedetails.IMailProviderService;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.model.Recipient;
import de.bht.fpa.mail.s000000.common.mail.model.Sender;
import de.bht.fpa.mail.s000000.common.rcp.selection.SelectionHelper;
import de.bht.fpa.mail.s000000.common.table.MessageValues;
import de.bht.fpa.mail.s769183.maillist.Activator;
import de.bht.fpa.mail.s769183.maillist.BarFilter.SearchBarFilter;
import de.ralfebert.rcputils.tables.ColumnBuilder;
import de.ralfebert.rcputils.tables.ICellFormatter;
import de.ralfebert.rcputils.tables.TableViewerBuilder;
import de.ralfebert.rcputils.tables.format.Formatter;
import de.ralfebert.rcputils.tables.format.StringValueFormatter;

public class MailListView extends ViewPart implements ISelectionListener {

	private static TableViewer tv;
	private static TableViewerBuilder tvb;

	private static List<Message> messages;
	private static List<Message> messagesToFilter;
	private static List<Message> savedMessages;
	private SearchBarFilter filter;

	// Column Sizes
	private static final int COL_WIDTH_XL = 300;
	private static final int COL_WIDTH_L = 150;
	private static final int COL_WIDTH_M = 75;
	private static final int COL_WIDTH_S = 50;

	private static final int FORMATTACHMENT_OFFSET_3 = 3;
	private static final int FORMATTACHMENT_OFFSET_5 = 5;
	private static final int FORMATTACHMENT_OFFSET_N5 = -5;
	private static final int FORMATTACHMENT_NUMERATOR_100 = 100;
	private static final int FORMATTACHMENT_NUMERATOR_10 = 10;

	@Override
	public void createPartControl(Composite parent) {

		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(1, false));

		Composite barTableComp = new Composite(comp, SWT.NONE);
		barTableComp.setLayout(new GridLayout(1, false));
		barTableComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));

		createSearchBar(barTableComp);
		createTable(barTableComp);

		getSite().getPage().addSelectionListener(this);
		getSite().setSelectionProvider(tv);
	}

	private void createSearchBar(Composite comp) {
		Composite searchBarComp = new Composite(comp, SWT.NONE);
		searchBarComp.setLayout(new FormLayout());
		searchBarComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false, 1, 1));

		Label label = new Label(searchBarComp, SWT.NONE);
		FormData fdLabel = new FormData();
		fdLabel.top = new FormAttachment(0, FORMATTACHMENT_OFFSET_3);
		fdLabel.left = new FormAttachment(0, FORMATTACHMENT_OFFSET_5);
		label.setLayoutData(fdLabel);
		label.setText("Search: ");

		final Text text = new Text(searchBarComp, SWT.BORDER);
		FormData fdText = new FormData();
		fdText.right = new FormAttachment(FORMATTACHMENT_NUMERATOR_100,
				FORMATTACHMENT_OFFSET_N5);
		fdText.left = new FormAttachment(FORMATTACHMENT_NUMERATOR_10);
		text.setLayoutData(fdText);
		text.setText("");

		filter = new SearchBarFilter();

		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filter.setSearchRequest(text.getText());
				tv.refresh();
			}
		});
	}

	private void createTable(Composite comp) {
		Composite tableComp = new Composite(comp, SWT.BORDER
				| SWT.FULL_SELECTION);
		tableComp.setLayout(new GridLayout(1, false));
		tableComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1));

		tvb = new TableViewerBuilder(tableComp, SWT.BORDER | SWT.FULL_SELECTION);
		tv = tvb.getTableViewer();

		ColumnBuilder importance = tvb.createColumn("Importance");
		importance.bindToValue(MessageValues.IMPORTANCE);
		importance.setPixelWidth(COL_WIDTH_M);
		importance.alignCenter();
		importance.format(new ICellFormatter() {

			@Override
			public void formatCell(ViewerCell cell, Object value) {
				Image high = AbstractUIPlugin.imageDescriptorFromPlugin(
						Activator.PLUGIN_ID, "icons/Arrow-double-up.ico")
						.createImage();
				Image normal = AbstractUIPlugin.imageDescriptorFromPlugin(
						Activator.PLUGIN_ID, "icons/Arrow-right.ico")
						.createImage();
				Image low = AbstractUIPlugin.imageDescriptorFromPlugin(
						Activator.PLUGIN_ID, "icons/Arrow-down.ico")
						.createImage();

				if (value.toString().equals("high")) {
					cell.setImage(high);
					cell.setText("");
				}
				if (value.toString().equals("normal")) {
					cell.setImage(normal);
					cell.setText("");
				}
				if (value.toString().equals("low")) {
					cell.setImage(low);
					cell.setText("");
				}
			}
		});
		importance.build();

		ColumnBuilder received = tvb.createColumn("received");
		received.bindToValue(MessageValues.RECEIVED);
		received.setPixelWidth(COL_WIDTH_M);
		received.alignCenter();
		StringValueFormatter svf = Formatter.forDate(SimpleDateFormat
				.getDateInstance(SimpleDateFormat.MEDIUM));
		received.format(svf);
		received.useAsDefaultSortColumn();
		received.build();

		ColumnBuilder read = tvb.createColumn("Read");
		read.bindToValue(MessageValues.READ);
		read.setPixelWidth(COL_WIDTH_S);
		read.alignCenter();
		read.build();

		ColumnBuilder sender = tvb.createColumn("Sender");
		sender.bindToValue(MessageValues.SENDER);
		sender.setPixelWidth(COL_WIDTH_L);
		sender.alignCenter();
		sender.format(new ICellFormatter() {
			@Override
			public void formatCell(ViewerCell cell, Object value) {
				if (!(value instanceof Sender)) {
					return;
				}
				Sender s = (Sender) value;
				String sender = s.getEmail();
				cell.setText(sender);
			}
		});
		sender.build();

		ColumnBuilder recipient = tvb.createColumn("recipient");
		recipient.bindToValue(MessageValues.RECIPIENT);
		recipient.setPixelWidth(COL_WIDTH_XL);
		recipient.alignCenter();
		recipient.format(new ICellFormatter() {

			@SuppressWarnings("unchecked")
			@Override
			public void formatCell(ViewerCell cell, Object value) {
				if (!(value instanceof List)) {
					return;
				}
				StringBuilder builder = new StringBuilder();

				List<Recipient> recipients = (List<Recipient>) value;
				for (Recipient r : recipients) {
					builder.append(r.getEmail());
					builder.append("; ");
				}
				cell.setText(builder.toString());
			}
		});
		recipient.build();

		ColumnBuilder subject = tvb.createColumn("Subject");
		subject.bindToValue(MessageValues.SUBJECT);
		subject.setPixelWidth(COL_WIDTH_XL);
		subject.alignCenter();
		subject.build();

		tv.addFilter(filter);
	}

	@Override
	public void setFocus() {
		tv.getTable().setFocus();
	}

	@Override
	public void dispose() {
		ISelectionService s = getSite().getWorkbenchWindow()
				.getSelectionService();
		s.removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		IMailProviderService mailProvider = SelectionHelper
				.handleStructuredSelection(selection,
						IMailProviderService.class);
		if (mailProvider instanceof IMailProviderService) {
			messages = mailProvider.getMessages();
			savedMessages = messages;
			tvb.setInput(messages);
		}
	}

	public static TableViewer getTableViewer() {
		return tv;
	}

	public static void setTableViewer(TableViewer tableViewer) {
		tv = tableViewer;
	}

	public static TableViewerBuilder getTableViewBuilder() {
		return tvb;
	}

	public static void setTableViewBuilder(TableViewerBuilder tableViewBuilder) {
		tvb = tableViewBuilder;
	}

	public static List<Message> getMessagesToFilter() {
		return messagesToFilter;
	}

	public static void setMessagesToFilter(List<Message> messageList) {
		messagesToFilter = messageList;
	}

	public static List<Message> getMessages() {
		return messages;
	}

	public static void setMessages(List<Message> messageList) {
		messages = messageList;
	}

	public static List<Message> getSavedMessages() {
		return savedMessages;
	}

}
