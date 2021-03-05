package binhtt.dev.websocket.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "chat-room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    @Id
    @Column(name = "room-id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    @Column(name = "room-name")
    private String roomName;
    @Column(name = "create-time")
    private Timestamp createTime;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private List<ChatMessage> chatMessages;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private List<Participant> participants;
}
