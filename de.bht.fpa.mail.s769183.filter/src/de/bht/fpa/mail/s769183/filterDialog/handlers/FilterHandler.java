package de.bht.fpa.mail.s769183.filterDialog.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.bht.fpa.mail.s000000.common.filter.FilterCombination;
import de.bht.fpa.mail.s000000.common.filter.FilterDialog;
import de.bht.fpa.mail.s000000.common.filter.FilterGroupType;
import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.FilterType;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterImportance;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterIntersection;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterRead;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterRecipient;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterSender;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterSubject;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterText;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterUnion;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class FilterHandler extends AbstractHandler {

	private final List<IFilter> filterList = new ArrayList<IFilter>();

	// private List<FilterCombination> filterCombinations;
	// private FilterGroupType filterGroup;
	// private FilterType filterType;
	// private FilterOperator filterOperator;
	// private Object filterValue;

	/**
	 * The constructor.
	 */
	public FilterHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindowChecked(event);

		FilterDialog filterDialog = new FilterDialog(window.getShell());
		filterDialog.open();

		List<FilterCombination> filterCombinations = filterDialog
				.getFilterCombinations();
		FilterGroupType filterGroup = filterDialog.getFilterGroupType();

		if (filterCombinations != null) {
			for (FilterCombination fc : filterCombinations) {
				FilterType filterType = fc.getFilterType();
				FilterOperator filterOperator = fc.getFilterOperator();
				Object filterValue = fc.getFilterValue();

				try {
					if (filterType == FilterType.IMPORTANCE) {
						FilterImportance importance = new FilterImportance(
								(Importance) filterValue);
						filterList.add(importance);
					} else if (filterType == FilterType.READ) {
						FilterRead read = new FilterRead((Boolean) filterValue);
						filterList.add(read);
					} else if (filterType == FilterType.SENDER) {
						FilterSender sender = new FilterSender(
								filterValue.toString(), filterOperator);
						filterList.add(sender);
					} else if (filterType == FilterType.RECIPIENTS) {
						FilterRecipient recipients = new FilterRecipient(
								filterValue.toString(), filterOperator);
						filterList.add(recipients);
					} else if (filterType == FilterType.SUBJECT) {
						FilterSubject subject = new FilterSubject(
								filterValue.toString(), filterOperator);
						filterList.add(subject);
					} else if (filterType == FilterType.TEXT) {
						FilterText text = new FilterText(
								filterValue.toString(), filterOperator);
						filterList.add(text);

					}
				} catch (NullPointerException e) {
					System.out.println("Nullpointer");
				}
			}

			if (filterGroup.equals(FilterGroupType.UNION)) {
				FilterUnion union = new FilterUnion(filterList);
				return union;
			}
			if (filterGroup.equals(FilterGroupType.INTERSECTION)) {
				FilterIntersection intersection = new FilterIntersection(
						filterList);
				return intersection;
			}
		}

		filterList.clear();
		filterCombinations.clear();
		return null;
	}
}
