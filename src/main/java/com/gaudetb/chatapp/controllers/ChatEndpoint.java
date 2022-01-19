package com.gaudetb.chatapp.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.gaudetb.chatapp.models.Message;
import com.gaudetb.chatapp.models.User;
import com.gaudetb.chatapp.services.UserService;
import com.gaudetb.chatapp.services.MessageDecoder;
import com.gaudetb.chatapp.services.MessageEncoder;

@CrossOrigin
@ServerEndpoint(value = "/chat/{id}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatEndpoint {
	
    private Session session;
    private static final Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static HashMap<String, User> users = new HashMap<String, User>();

    @OnOpen
    public void onOpen(Session session, @PathParam("id") Long id) throws IOException, EncodeException {
    	
    	UserService userService = new UserService();
    	User user = userService.findOne(id);
    	
        this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), user);

        Message message = new Message();
        message.setFrom(user);
        message.setContent("Connected!");
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        message.setFrom(users.get(session.getId()));
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        chatEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }

    private static void broadcast(Message message) throws IOException, EncodeException {
        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote()
                        .sendObject(message);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}