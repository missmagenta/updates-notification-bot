package edu.java.domain.dao;

import java.util.List;

public interface ChatLinkDao {
    Boolean add(int chatId, int linkId);
    Boolean remove(int chatId, int linkId);
    Boolean removeByChatId(int chatId);
    List<Integer> findAllLinksByChatId(int chatId);
}
