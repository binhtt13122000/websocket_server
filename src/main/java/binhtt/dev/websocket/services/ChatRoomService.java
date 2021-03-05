package binhtt.dev.websocket.services;

import binhtt.dev.websocket.entities.ChatRoom;

import java.util.Optional;

public interface ChatRoomService {
    Optional<ChatRoom> getRoomById(Long roomId);
    ChatRoom createRoom(ChatRoom chatRoom);
}
