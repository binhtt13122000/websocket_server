package binhtt.dev.websocket.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "participant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "name")
    private String name;
    @Column(name = "avatar")
    private String avatar;
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @JsonIgnore
    private ChatRoom chatRoom;
}
