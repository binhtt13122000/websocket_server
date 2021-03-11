package binhtt.dev.websocket.services.imp;

import binhtt.dev.websocket.entities.ChatRoom;
import binhtt.dev.websocket.entities.Participant;
import binhtt.dev.websocket.services.ChatRoomService;
import binhtt.dev.websocket.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImp implements ParticipantService {
    @Autowired
    private ChatRoomService chatRoomService;
    @Override
    public ChatRoom addParticipants(List<String> participantNames, ChatRoom chatRoom) {
        List<Participant> participants = participantNames.stream().map(participantName -> new Participant(participantName, chatRoom)).collect(Collectors.toList());
        chatRoom.setParticipants(participants);
        return chatRoomService.updateRoom(chatRoom);
    }
}
