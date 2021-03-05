package binhtt.dev.websocket.controllers;

import binhtt.dev.websocket.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RoomChatController {
    @Autowired
    private ChatRoomService chatRoomService;
    @GetMapping("/chats/{userId}")
    public ResponseEntity getRoomChats(@PathVariable("userId") Optional<String> userId){
        if(userId.isPresent()){
            return null;
        } else {
            return new ResponseEntity("Don't have user info!",HttpStatus.NOT_FOUND);
        }
    }
}
