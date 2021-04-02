package binhtt.dev.websocket.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChatMessageDto implements Serializable {
    private String chatRoomId;
    private String content;
    private String senderId;
    private Timestamp sendTime;

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public ChatMessageDto(String chatRoomId, String content, String senderId, Timestamp sendTime) {
        this.chatRoomId = chatRoomId;
        this.content = content;
        this.senderId = senderId;
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "ChatMessageDto{" +
                "chatRoomId='" + chatRoomId + '\'' +
                ", content='" + content + '\'' +
                ", senderId='" + senderId + '\'' +
                ", sendTime=" + sendTime +
                '}';
    }
}
