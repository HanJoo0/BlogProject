package edu.iot.butter.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.iot.butter.model.Member;
import edu.iot.butter.model.Talk;
import edu.iot.butter.service.TalkService;

@Component
public class TalkInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	TalkService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
						HttpServletResponse response, Object handler)
			throws Exception {
		
		Member member = (Member)request.getSession().getAttribute("USER");
		if(member != null) {
			List<Talk> talks = service.getNewTalks(member.getUserId());
			if(talks.size()>0) {
				System.out.println(talks.size());
				request.setAttribute("newTalks", talks.size());
			}
		}		
		// return super.preHandle(request, response, handler);
		return true;
	}	
}
