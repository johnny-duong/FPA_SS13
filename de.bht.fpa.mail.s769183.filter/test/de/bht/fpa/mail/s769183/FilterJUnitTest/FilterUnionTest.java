package de.bht.fpa.mail.s769183.FilterJUnitTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterImportance;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterRead;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterSender;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterUnion;

public class FilterUnionTest {

	private final int NUM_MESSAGES = 10;
	private final Collection<Message> randomMessages = new RandomTestDataProvider(
			NUM_MESSAGES).getMessages();
	private final Collection<Message> testResult = new HashSet<Message>();

	// private final Importance HIGH = Importance.HIGH;
	// private final Importance LOW = Importance.LOW;
	private final Importance NORMAL = Importance.NORMAL;

	private final FilterOperator CONTAINS = FilterOperator.CONTAINS;
	// private final FilterOperator IS = FilterOperator.IS;
	// private final FilterOperator CONTAINS_NOT = FilterOperator.CONTAINS_NOT;
	// private final FilterOperator STARTS_WITH = FilterOperator.STARTS_WITH;
	// private final FilterOperator ENDS_WITH = FilterOperator.ENDS_WITH;

	private final FilterImportance importance = new FilterImportance(NORMAL);
	private final FilterSender sender = new FilterSender("frank", CONTAINS);
	private final FilterRead read = new FilterRead(false);

	private final List<IFilter> filterList = new ArrayList<IFilter>();

	@Test
	public void TestUnionFilter() {
		filterList.add(importance);
		filterList.add(read);
		filterList.add(sender);

		for (Message m : randomMessages) {
			if (m.getImportance().equals(NORMAL) || m.isRead().equals(false)
					|| m.getSender().getEmail().contains("frank")
					|| m.getSender().getPersonal().contains("frank")) {
				testResult.add(m);
			}
		}

		FilterUnion union = new FilterUnion(filterList);
		assertEquals(testResult, union.filter(randomMessages));
	}
}