package binhtt.dev.websocket.repositories;

import binhtt.dev.websocket.entities.ChatMessage;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> getChatMessagesByChatRoom_RoomIdOrderBySendTimeAsc(Long chatRoom);
}
