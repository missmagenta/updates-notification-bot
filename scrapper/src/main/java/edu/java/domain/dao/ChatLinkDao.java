package edu.java.domain.dao;

import edu.java.model.ChatLink;
import java.util.List;

public interface ChatLinkDao {
    Boolean add(int chatId, int linkId);
    Boolean remove(int chatId, int linkId);
    Boolean removeByChatId(int chatId);
    List<ChatLink> findAllLinksByChatId(int chatId);
}
