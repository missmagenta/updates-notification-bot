package edu.java.domain.dao;

import edu.java.model.Chat;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface ChatDao {
    Boolean add(@NotNull int id);
    Boolean remove(@NotNull int id);
    List<@NotNull Chat> findAll();
}
