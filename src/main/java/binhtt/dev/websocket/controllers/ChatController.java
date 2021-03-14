package binhtt.dev.websocket.controllers;


import binhtt.dev.websocket.dto.ChatMessageDto;
import binhtt.dev.websocket.entities.ChatMessage;
import binhtt.dev.websocket.entities.ChatRoom;
import binhtt.dev.websocket.entities.Participant;
import binhtt.dev.websocket.services.ChatMessageService;
import binhtt.dev.websocket.services.ChatRoomService;
import binhtt.dev.websocket.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

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

    @MessageMapping("/chat/create")
    public void createRoom(@Payload ChatRoom chatRoom){
        chatRoom.setCreateTime(new Timestamp(new Date().getTime()));
        List<Participant> participants = new ArrayList<>(chatRoom.getParticipants());
        chatRoom.setParticipants(Collections.emptyList());
        ChatRoom newRoom = chatRoomService.createRoom(chatRoom);
        if(newRoom != null){
            System.out.println(newRoom);
            for(Participant participant: participants){
                participant.setChatRoom(newRoom);
            }
            participantService.saveParticipants(participants);
            for (Participant participant: participants) {
                System.out.println(participant.getUserId());
                messagingTemplate.convertAndSendToUser(participant.getUserId(), "/queue/messages", newRoom.getRoomId());
            };
        }
    }
}
