package edu.iot.butter.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.iot.butter.model.Member;
import edu.iot.butter.model.Talk;
import edu.iot.butter.service.MemberService;
import edu.iot.butter.service.TalkService;

@Controller
@RequestMapping("/talk")
public class TalkController {

	@Autowired
	MemberService service;

	@Autowired
	TalkService talkService;

	@RequestMapping(value="/echo", method=RequestMethod.GET)
	public void talk()  throws Exception {
	
	}
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public void talk(
			@RequestParam(value="page", defaultValue="1") int page,
			HttpSession session,
			Model model)  throws Exception {
		Member member = (Member)session.getAttribute("USER");
		String userId = member.getUserId();
		model.addAttribute("memberList", 
				service.getListWithMessages(userId));
		model.addAttribute("talkList", 
				talkService.selectOneListPerUser(userId));
	}

	@RequestMapping(value="/with", method=RequestMethod.GET)
	public void with(Talk talk)  throws Exception {
		// checked --> 1
		talkService.updateCheck(talk);
	}

	@ResponseBody
	@RequestMapping(value="/talkList", method=RequestMethod.GET)
	public List<Talk> talkList(Talk talk)  throws Exception {
		System.out.println(talk);
		return talkService.getTalkList(talk);
	}
	
	
}

