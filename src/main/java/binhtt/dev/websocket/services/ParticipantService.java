package binhtt.dev.websocket.services;

import binhtt.dev.websocket.entities.ChatRoom;

import java.util.List;

public interface ParticipantService {
    ChatRoom addParticipants(List<String> participantNames, ChatRoom chatRoom);
}
