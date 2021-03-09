package binhtt.dev.websocket.services;

import binhtt.dev.websocket.entities.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ChatRoomService {
    Optional<ChatRoom> getRoomById(Long roomId);
    ChatRoom createRoom(ChatRoom chatRoom);
    Page<ChatRoom> getChatRoomsOfUser(String userId, Pageable pageable);
}
