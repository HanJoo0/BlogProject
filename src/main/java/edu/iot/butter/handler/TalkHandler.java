package edu.iot.butter.handler;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import edu.iot.butter.model.Member;
import edu.iot.butter.model.Talk;
import edu.iot.butter.service.TalkService;

@Component
public class TalkHandler extends TextWebSocketHandler {
	Map<String, WebSocketSession> sessionMap = 
						Collections.synchronizedMap(new HashMap<>());


	@Autowired
	TalkService service;

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionMap.remove(getUserId(session));
		super.afterConnectionClosed(session, status);
	}
	
	String getUserId(WebSocketSession session) {
		Member member = (Member) session.getAttributes().get("USER");
		return member.getUserId();
	}
	

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionMap.put(getUserId(session), session);
		super.afterConnectionEstablished(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// 메시지 수신자 식별 및 처리
		Gson gson = new Gson();
		Talk talk = gson.fromJson(message.getPayload(), Talk.class);
		service.create(talk);
		send(talk, message);
		super.handleTextMessage(session, message);
	}
	public void saveTalk(Talk talk, int checked) throws Exception {
		// 상대방거로 저장
		Talk talk2 = Talk.builder()
						.userId(talk.getWithTalk())
						.withTalk(talk.getUserId())
						.checked(checked)
						.received(1)
						.message(talk.getMessage())
						.build();
		service.create(talk2);
	}
	

	public void send(Talk talk, TextMessage message) throws Exception {
		WebSocketSession s = sessionMap.get(talk.getWithTalk());
		if (s != null) { 
			s.sendMessage(message);
			saveTalk(talk, 1);
		} else {
			saveTalk(talk, 0);
		}
	}
	
	
}
