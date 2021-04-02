package binhtt.dev.websocket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "chat_message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    @Id
    @Column(name = "message_id", nullable = false, updatable = false, columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "send_time", nullable = false)
    private Timestamp sendTime;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "room_id", nullable = false)
    private ChatRoom chatRoom;
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Participant participant;
}
