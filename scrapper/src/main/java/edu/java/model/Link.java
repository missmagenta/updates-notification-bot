package edu.java.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Link {
    private int id;
    private String name;
    private LocalDateTime lastUpdateDate;
    private List<Chat> chats = new ArrayList<>();
}
