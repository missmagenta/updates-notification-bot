package edu.java.domain.dao;

import edu.java.dto.response.LinkResponse;
import edu.java.model.Link;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public interface LinkDao {
    Integer add(@NotNull String url);
    Boolean remove(@NotNull int id);
    @NotNull List<LinkResponse> findAll();
    @Nullable Integer findIdByUrl(String url);
    @Nullable String findUrlById(int id);
    @Nullable Link findById(int id);
    @Nullable List<Integer> findNotUpdatedForLongTime(long seconds);
    void update(int id, LocalDateTime lastUpdate);
}
