package binhtt.dev.websocket.controllers;


import binhtt.dev.websocket.dto.ChatMessageDto;
import binhtt.dev.websocket.entities.ChatMessage;
import binhtt.dev.websocket.entities.ChatRoom;
import binhtt.dev.websocket.entities.Participant;
import binhtt.dev.websocket.services.ChatMessageService;
import binhtt.dev.websocket.services.ChatRoomService;
import binhtt.dev.websocket.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private ChatMessageService chatMessageService;
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageDto chatMessage){
        try {
            ChatMessage chatMess = new ChatMessage();
            chatMess.setContent(chatMessage.getContent());
            chatMess.setParticipant(participantService.getParticipantById(Long.parseLong(chatMessage.getSenderId())).get());
            chatMess.setSendTime(chatMessage.getSendTime());
            ChatRoom chatRoom = chatRoomService.getRoomById(Long.parseLong(chatMessage.getChatRoomId())).get();
            chatMess.setChatRoom(chatRoom);
            chatMessageService.insertMessage(chatMess);
            messagingTemplate.convertAndSend("/room/" + chatMessage.getChatRoomId() + "/queue/messages", chatMess);
            for (Participant participant: chatRoom.getParticipants()) {
                messagingTemplate.convertAndSendToUser(participant.getUserId(), "/queue/messages", chatMess);
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

    @MessageMapping("/chat/create/{userId}")
    public void createRoom(@DestinationVariable String userId, @Payload ChatRoom chatRoom){
        List<Participant> participants = new ArrayList<>(chatRoom.getParticipants());
        if(chatRoomService.checkExistedRoom(participants)){
            messagingTemplate.convertAndSendToUser(userId, "/queue/messages", "Failed!");
        } else {
            System.out.println(1);
            Timestamp time = new Timestamp(new Date().getTime());
            chatRoom.setCreateTime(time);
            chatRoom.setParticipants(Collections.emptyList());
            ChatRoom newRoom = chatRoomService.createRoom(chatRoom);
            if(newRoom != null){
                ChatMessage chatMessage = null;
                for(Participant participant: participants){
                    if(userId.equals(participant.getUserId())){
                        chatMessage = chatRoom.getChatMessages().get(0);
                        chatMessage.setChatRoom(newRoom);
                        chatMessage.setSendTime(time);
                        chatMessage.setParticipant(participant);
                    }
                    participant.setChatRoom(newRoom);
                }
                participantService.saveParticipants(participants);
                chatMessageService.insertMessage(chatMessage);
                for (Participant participant: participants) {
                    messagingTemplate.convertAndSendToUser(participant.getUserId(), "/queue/messages", newRoom.getRoomId());
                };
        }
        }
    }
}
