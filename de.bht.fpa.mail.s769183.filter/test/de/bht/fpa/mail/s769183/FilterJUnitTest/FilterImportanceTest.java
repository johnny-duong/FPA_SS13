package de.bht.fpa.mail.s769183.FilterJUnitTest;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterImportance;

public class FilterImportanceTest {

	private final int NUM_MESSAGES = 100;
	private final Collection<Message> randomMessages = new RandomTestDataProvider(
			NUM_MESSAGES).getMessages();
	private final Collection<Message> testResult = new HashSet<Message>();

	// private final Importance HIGH = Importance.HIGH;
	private final Importance NORMAL = Importance.NORMAL;

	// private final Importance LOW = Importance.LOW;

	@Test
	public void TestImportanceFilter() {
		for (Message m : randomMessages) {
			if (m.getImportance().equals(NORMAL)) {
				testResult.add(m);
			}
		}
		FilterImportance importance = new FilterImportance(NORMAL);
		assertEquals(testResult, importance.filter(randomMessages));
	}
}
