package com.gaudetb.chatapp.services;

//import javax.websocket.DecodeException;
//import javax.websocket.Decoder;
//import javax.websocket.EndpointConfig;
//import com.google.gson.Gson;

//import com.gaudetb.chatapp.models.Message;


//	==> { NOTE } <==
//
//	unused MessageDecoder for future use
//
//		{ END NOTE }
//
//


//public

//public class MessageDecoder implements Decoder.Text<Message> {

//    private static Gson gson = new Gson();

//    @Override
//    public Message decode(String s) throws DecodeException {
////        Message message = gson.fromJson(s, Message.class);
//    	Message message = new Message();
//        return message;
//    }

//    @Override
//    public boolean willDecode(String s) {
//        return (s != null);
//    }
//
//    @Override
//    public void init(EndpointConfig endpointConfig) {
//        // Custom initialization logic
//    }
//
//    @Override
//    public void destroy() {
//        // Close resources
//    }
//}