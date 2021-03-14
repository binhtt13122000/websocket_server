package binhtt.dev.websocket.services.imp;

import binhtt.dev.websocket.entities.ChatRoom;
import binhtt.dev.websocket.repositories.ChatRoomRepository;
import binhtt.dev.websocket.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomServiceImp implements ChatRoomService {
    @Autowired
    private ChatRoomRepository chatRoomRepository;


    @Override
    public Optional<ChatRoom> getRoomById(Long roomId) {
        return chatRoomRepository.findById(roomId);
    }

    @Override
    public ChatRoom createRoom(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }


    @Override
    public Page<ChatRoom> getChatRoomsOfUser(String userId, Pageable pageable) {
        return chatRoomRepository.findChatRoomsByParticipant(userId, pageable);
    }
}
