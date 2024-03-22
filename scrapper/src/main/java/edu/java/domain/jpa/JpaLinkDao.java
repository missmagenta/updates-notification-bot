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
@ConditionalOnProperty(prefix = "app", name = "access-type", havingValue = "jpa")
public interface JpaLinkDao extends JpaRepository<Link, Integer> {
    Optional<Link> findLinkByChatAndUrl(Chat chat, String url);
    Optional<List<Link>> findAllLinksPerChat(Chat chat);
    List<Link> findByLastUpdatedBefore(LocalDateTime threshold);
}
