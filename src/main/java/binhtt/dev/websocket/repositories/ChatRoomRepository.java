package binhtt.dev.websocket.repositories;

import binhtt.dev.websocket.entities.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @Query("select c from ChatRoom c where c.participants in (select p from Participant p where p.userId = :userId)")
    Page<ChatRoom> findChatRoomsByParticipant(String userId, Pageable pageable);
}
