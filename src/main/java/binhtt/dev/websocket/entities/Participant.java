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
    private String participantId;
    @Column(name = "participant-name")
    private String getParticipantName;
    @ManyToOne
    @JoinColumn(name = "room-id", nullable = false)
    private ChatRoom chatRoom;

}
