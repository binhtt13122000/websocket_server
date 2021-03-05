package binhtt.dev.websocket.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "participant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @Id
    @Column(name = "participant-id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;
    @Column(name = "user-id")
    private String userId;
    @Column(name = "user-name")
    private String username;
    @ManyToOne
    @JoinColumn(name = "room-id", nullable = false)
    private ChatRoom chatRoom;

}
