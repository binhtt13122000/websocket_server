package binhtt.dev.websocket.repositories;

import binhtt.dev.websocket.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
