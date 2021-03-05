package binhtt.dev.websocket.services;

import sun.plugin2.message.Message;

import java.util.List;

public interface ChatMessageService {
    List<Message> getMessages(String roomId);
}
