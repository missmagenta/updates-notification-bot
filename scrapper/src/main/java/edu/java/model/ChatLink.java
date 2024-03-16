package edu.java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ChatLink {
    private int chatId;
    private int linkId;
    private LocalDateTime linkStartedTrackDate;
    private LocalDateTime linkUntrackDate;
}
