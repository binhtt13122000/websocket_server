package binhtt.dev.websocket.services.imp;

import binhtt.dev.websocket.entities.ChatRoom;
import binhtt.dev.websocket.entities.Participant;
import binhtt.dev.websocket.repositories.ParticipantRepository;
import binhtt.dev.websocket.services.ChatRoomService;
import binhtt.dev.websocket.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImp implements ParticipantService {
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    @Override
    public void saveParticipants(List<Participant> participants) {
        for (Participant participant: participants) {
            participantRepository.save(participant);
        }
    }
}
