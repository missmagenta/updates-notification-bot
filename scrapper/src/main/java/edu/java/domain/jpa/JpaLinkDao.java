package edu.java.domain.jpa;

import edu.java.model.Chat;
import edu.java.model.Link;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public interface JpaLinkDao extends JpaRepository<Link, Integer> {
    Optional<Link> findLinkByChatsAndName(Chat chat, String url);
    List<Link> findAllLinksByChats(Chat chat);
    List<Link> findByLastUpdateDateBefore(LocalDateTime threshold);
}
