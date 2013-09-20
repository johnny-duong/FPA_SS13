package de.bht.fpa.mail.s769183.FilterJUnitTest;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterRead;

public class FilterReadTest {

	private final int NUM_MESSAGES = 100;
	private final Collection<Message> randomMessages = new RandomTestDataProvider(
			NUM_MESSAGES).getMessages();
	private final Collection<Message> testResult = new HashSet<Message>();

	@Test
	public void TestReadFilter() {
		for (Message m : randomMessages) {
			if (m.isRead() == true) {
				testResult.add(m);
			}
		}
		FilterRead read = new FilterRead(true);
		assertEquals(testResult, read.filter(randomMessages));
	}

}
