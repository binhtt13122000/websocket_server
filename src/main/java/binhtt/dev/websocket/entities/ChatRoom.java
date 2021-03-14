package binhtt.dev.websocket.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "chat_room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    @Id
    @Column(name = "room_id", nullable = false, updatable = false, columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    @Column(name = "room_name")
    private String roomName;
    @Column(name = "create_time")
    private Timestamp createTime;
    @Column(name = "room_avt")
    private String roomAvt;
    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
    private List<ChatMessage> chatMessages;

    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.EAGER)
    private List<Participant> participants;

}
