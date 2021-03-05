package binhtt.dev.websocket.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "chat-message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    @Column(name = "message-id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "send-time", nullable = false)
    private Timestamp sendTime;
    @ManyToOne
    @JoinColumn(name = "room-id", nullable = false)
    private ChatRoom chatRoom;
    @ManyToOne
    @JoinColumn(name = "sender-id", nullable = false)
    private Participant participant;
}
