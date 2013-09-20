package de.bht.fpa.mail.s769183.FilterJUnitTest;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterSender;

public class FilterSenderTest {

	private final int NUM_MESSAGES = 10;
	private final Collection<Message> randomMessages = new RandomTestDataProvider(
			NUM_MESSAGES).getMessages();
	private final Collection<Message> testResult = new HashSet<Message>();

	// private final FilterOperator IS = FilterOperator.IS;
	private final FilterOperator CONTAINS = FilterOperator.CONTAINS;

	// private final FilterOperator CONTAINS_NOT = FilterOperator.CONTAINS_NOT;
	// private final FilterOperator STARTS_WITH = FilterOperator.STARTS_WITH;
	// private final FilterOperator ENDS_WITH = FilterOperator.ENDS_WITH;

	@Test
	public void TestSenderFilter() {
		for (Message m : randomMessages) {
			if (m.getSender().getEmail().contains("schwarz")
					|| m.getSender().getPersonal().contains("schwarz")) {
				testResult.add(m);
			}
		}
		FilterSender sender = new FilterSender("schwarz", CONTAINS);
		assertEquals(testResult, sender.filter(randomMessages));
	}

}