package binhtt.dev.websocket.services;

import binhtt.dev.websocket.entities.ChatMessage;

import java.util.List;

public interface ChatMessageService {
    List<ChatMessage> getMessages(Long roomId);

    ChatMessage insertMessage(ChatMessage chatMessage);
}
