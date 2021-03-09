package binhtt.dev.websocket.controllers;


import binhtt.dev.websocket.dto.ChatMessageDto;
import binhtt.dev.websocket.entities.ChatMessage;
import binhtt.dev.websocket.entities.ChatRoom;
import binhtt.dev.websocket.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageDto chatMessage){
        System.out.println(chatMessage.toString());
    }
}
