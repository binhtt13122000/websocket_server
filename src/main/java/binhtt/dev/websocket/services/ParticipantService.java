package binhtt.dev.websocket.services;

import binhtt.dev.websocket.entities.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantService {
    Optional<Participant> getParticipantById(Long id);
    void saveParticipants(List<Participant> participants);
}
