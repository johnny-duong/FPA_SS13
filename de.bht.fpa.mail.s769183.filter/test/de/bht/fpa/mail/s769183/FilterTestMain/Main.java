package de.bht.fpa.mail.s769183.FilterTestMain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.bht.fpa.mail.s000000.common.filter.FilterOperator;
import de.bht.fpa.mail.s000000.common.filter.IFilter;
import de.bht.fpa.mail.s000000.common.mail.model.Importance;
import de.bht.fpa.mail.s000000.common.mail.model.Message;
import de.bht.fpa.mail.s000000.common.mail.testdata.RandomTestDataProvider;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterImportance;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterIntersection;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterRead;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterRecipient;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterSender;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterSubject;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterText;
import de.bht.fpa.mail.s769183.filter.MessageFilter.FilterUnion;

public final class Main {

	private final static int NUMBER_OF_MESSAGES = 20;
	private final static Collection<Message> messages = new RandomTestDataProvider(
			NUMBER_OF_MESSAGES).getMessages();

	private final static Importance HIGH = Importance.HIGH;
	@SuppressWarnings("unused")
	private final static Importance LOW = Importance.LOW;
	private final static Importance NORMAL = Importance.NORMAL;

	private final static FilterOperator IS = FilterOperator.IS;
	private final static FilterOperator CONTAINS = FilterOperator.CONTAINS;
	private final static FilterOperator CONTAINS_NOT = FilterOperator.CONTAINS_NOT;
	private final static FilterOperator STARTS_WITH = FilterOperator.STARTS_WITH;
	private final static FilterOperator ENDS_WITH = FilterOperator.ENDS_WITH;

	private static Collection<Message> importanceList = new FilterImportance(
			HIGH).filter(messages);

	private static Collection<Message> readList = new FilterRead(false)
			.filter(messages);

	private static Collection<Message> senderList = new FilterSender("frank",
			CONTAINS).filter(messages);

	private static Collection<Message> recipientsList = new FilterRecipient(
			"frank schmidt", IS).filter(messages);

	private static Collection<Message> subjectList = new FilterSubject("free",
			ENDS_WITH).filter(messages);

	private static Collection<Message> textList = new FilterText("that",
			CONTAINS).filter(messages);

	private static List<IFilter> filterList = new ArrayList<IFilter>();

	private static Collection<Message> unionList;
	private static Collection<Message> intersectionList;

	// = new FilterIntersection(
	// new FilterSender("frank", STARTS_WITH), new FilterRead(false),
	// new FilterImportance(NORMAL)).filter(messages);

	public static void main(String[] args) {
		System.out.println("------------------------------");
		System.out.println("------- Test Importance Begin -------" + "\n");
		FilterImportance.showMessages(importanceList);
		System.out.println("------- Test Importance End -------");
		System.out.println("------------------------------" + "\n");

		System.out.println("------------------------------");
		System.out.println("------- Test Read Begin -------" + "\n");
		FilterRead.showMessages(readList);
		System.out.println("------- Test Read End -------");
		System.out.println("------------------------------" + "\n");

		System.out.println("------------------------------");
		System.out.println("------- Test Sender Begin -------" + "\n");
		FilterSender.showMessages(senderList);
		System.out.println("------- Test Sender End -------");
		System.out.println("------------------------------" + "\n");

		System.out.println("------------------------------");
		System.out.println("------- Test Recipient Begin -------" + "\n");
		FilterRecipient.showMessages(recipientsList);
		System.out.println("------- Test Recipient End -------");
		System.out.println("------------------------------" + "\n");

		System.out.println("------------------------------");
		System.out.println("------- Test Subject Begin -------" + "\n");
		FilterSubject.showMessages(subjectList);
		System.out.println("------- Test Subject End -------");
		System.out.println("------------------------------" + "\n");

		System.out.println("------------------------------");
		System.out.println("------- Test Text Begin -------" + "\n");
		FilterText.showMessages(textList);
		System.out.println("------- Test Text End -------");
		System.out.println("------------------------------" + "\n");

		System.out.println("------------------------------");
		System.out.println("------- Test Union Begin -------" + "\n");

		filterList.add(new FilterSender("lola", CONTAINS_NOT));
		filterList.add(new FilterRecipient("frank stulle", IS));
		filterList.add(new FilterRead(false));
		unionList = new FilterUnion(filterList).filter(messages);

		FilterUnion.showMessages(unionList);
		System.out.println("------- Test Union End -------");
		System.out.println("------------------------------" + "\n");

		System.out.println("------------------------------");
		System.out.println("------- Test Intersection Begin -------" + "\n");

		filterList.clear();
		filterList.add(new FilterSender("frank", STARTS_WITH));
		filterList.add(new FilterRead(false));
		filterList.add(new FilterImportance(NORMAL));
		intersectionList = new FilterIntersection(filterList).filter(messages);

		FilterIntersection.showMessages(intersectionList);
		System.out.println("------- Test Intersection End -------");
		System.out.println("------------------------------" + "\n");

	}
}
