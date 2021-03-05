package binhtt.dev.websocket.controllers;


import binhtt.dev.websocket.entities.ChatMessage;
import binhtt.dev.websocket.entities.ChatRoom;
import binhtt.dev.websocket.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatRoomService chatRoomService;

    @MessageMapping("/chat/{roomId}")
    public void processMessage(@DestinationVariable("roomId") Optional<String> roomId, @Payload ChatMessage chatMessage){
        String correctRoomId = null;
        boolean isNeedCreateRoom = false;
        if(roomId.isPresent()){
            Optional<ChatRoom> room = chatRoomService.getRoomById(Long.parseLong(roomId.get()));
            if(room.isPresent()){
                correctRoomId = String.valueOf(room.get().getRoomId());
            } else {
                isNeedCreateRoom = true;
            }
        } else {
            isNeedCreateRoom = true;
        }

        if(isNeedCreateRoom){
            ChatRoom chatRoom = new ChatRoom();
            chatRoom.setCreateTime(new Timestamp(new Date().getTime()));
            chatRoom.setRoomName(chatMessage.getParticipant().getUsername());
            ChatRoom createdChatRoom = chatRoomService.createRoom(chatRoom);
            correctRoomId = String.valueOf(createdChatRoom.getRoomId());
        }
        messagingTemplate.convertAndSendToUser(
                correctRoomId,
                "/queue/messages",
                chatMessage
        );
    }
}
