package binhtt.dev.websocket.services.imp;

import binhtt.dev.websocket.entities.ChatMessage;
import binhtt.dev.websocket.repositories.ChatMessageRepository;
import binhtt.dev.websocket.services.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageServiceImp implements ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Override
    public List<ChatMessage> getMessages(Long roomId) {
        return chatMessageRepository.getChatMessagesByChatRoom_RoomIdOrderBySendTimeAsc(roomId);
    }

    @Override
    public ChatMessage insertMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }
}
