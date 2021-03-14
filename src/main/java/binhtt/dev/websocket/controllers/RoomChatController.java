package binhtt.dev.websocket.controllers;

import binhtt.dev.websocket.entities.ChatRoom;
import binhtt.dev.websocket.services.ChatMessageService;
import binhtt.dev.websocket.services.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@RestController
public class RoomChatController {
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping("/chats/{userId}")
    public ResponseEntity getRoomChats(
            @PathVariable("userId") Optional<String> userId,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("page") Optional<Integer> page
    ){
        Pageable pageable = PageRequest.of(page.orElse(1), size.orElse(20));
        if(userId.isPresent()){
            return new ResponseEntity(chatRoomService.getChatRoomsOfUser(userId.get(), pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity("Don't have user info!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{userId}/rooms")
    public ResponseEntity getRooms(@PathVariable("userId") String userId,
                                   @RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size){
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, size.orElse(4));
        return new ResponseEntity(chatRoomService.getChatRoomsOfUser(userId, pageable).getContent(), HttpStatus.OK);
    }

    @GetMapping("rooms/{roomId}")
    public ResponseEntity getRoomById(@PathVariable("roomId") String roomId){
        try {
            ChatRoom chatRoom = chatRoomService.getRoomById(Long.parseLong(roomId)).orElse(null);
            if(chatRoom == null){
                return  new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
            } else {
                chatRoom.setChatMessages(chatMessageService.getMessages(Long.parseLong(roomId)));
                return new ResponseEntity(chatRoom, HttpStatus.OK);
            }
        } catch (NumberFormatException e){
            return new ResponseEntity("Id is invalid", HttpStatus.BAD_REQUEST);
        }
    }

}
