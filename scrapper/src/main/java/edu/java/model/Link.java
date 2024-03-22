package edu.java.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="link")
public class Link {
    @Id
    private int id;
    private String name;
    private LocalDateTime lastUpdateDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name="chat_link",
        joinColumns = @JoinColumn(name="link_id"),
        inverseJoinColumns = @JoinColumn(name="chat_id")
    )
    private List<Chat> chats = new ArrayList<>();

    public void addChat(Chat chat) {
        chats.add(chat);
    }

    public void deleteChat(Chat chat) {
        chats.remove(chat);
    }
}
