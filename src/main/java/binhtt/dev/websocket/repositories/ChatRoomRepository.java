package binhtt.dev.websocket.repositories;

import binhtt.dev.websocket.entities.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
//    @Query("select c from ChatRoom c where :participantId in (select p.participantId from Participant p)")
//    Page<ChatRoom> findChatRoomsByParticipant(String participantId);
}
