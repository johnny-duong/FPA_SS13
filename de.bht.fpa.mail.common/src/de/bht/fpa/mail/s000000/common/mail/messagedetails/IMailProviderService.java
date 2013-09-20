package de.bht.fpa.mail.s000000.common.mail.messagedetails;

import java.util.List;

import de.bht.fpa.mail.s000000.common.mail.model.Message;

public interface IMailProviderService {
  List<Message> getMessages();
}
