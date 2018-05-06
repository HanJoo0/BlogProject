package edu.iot.butter.handler;

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
	Map<WebSocketSession, String> sessionMap = Collections.synchronizedMap(new HashMap<>());
	Map<String, WebSocketSession> memberMap = Collections.synchronizedMap(new HashMap<>());
	Gson gson = new Gson();

	@Autowired
	TalkService service;

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String memberId = sessionMap.remove(session);
		memberMap.remove(memberId);
		System.out.println(memberId + " 해제");

		super.afterConnectionClosed(session, status);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Member member = (Member) session.getAttributes().get("USER");
		System.out.println(member.getUserId() + " 접속");
		sessionMap.put(session, member.getUserId());
		memberMap.put(member.getUserId(), session);
		super.afterConnectionEstablished(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		Talk talk = gson.fromJson(message.getPayload(), Talk.class);
		
		service.create(talk);
		if (talk.getCommand().equals("TALK")) {
			send(talk, message);
		} else {
			sendAll(session, talk, message);
		}
		super.handleTextMessage(session, message);
	}

	public void saveTalk(Talk talk, int checked) throws Exception {
		Talk talk2 = Talk.builder().
				userId(talk.getWithTalk())
				.withTalk(talk.getUserId())
				.checked(checked)	// 1이 상대방이 읽은 것
				.received(1)
				.message(talk.getMessage())
				.build();
		service.create(talk2);
	}

	public void sendAll(WebSocketSession session, Talk talk, TextMessage message) throws Exception {
		Iterator<WebSocketSession> iterator = sessionMap.keySet().iterator();
		while (iterator.hasNext()) {
			WebSocketSession s = iterator.next();
			if (session != s) {
				s.sendMessage(message);

			} else {

			}
		}
	}

	public void send(Talk talk, TextMessage message) throws Exception {
		WebSocketSession s = memberMap.get(talk.getWithTalk());
		if (s != null) {		 // 같은 페이지에 있으면.
			s.sendMessage(message);
			saveTalk(talk, 1);
		} else {
			saveTalk(talk, 0);
		}
	}

}
