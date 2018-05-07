var talkTempl = {
	sendTempl : function(msg) {
		return `
		<div class="media my-1">
			<img class="d-flex mr-3 rounded-circle avata" 
				    	src="/butter/member/avata?userId=${msg.userId}">
		    <div class="media-body text-left ml-3 mr-5">
				<div class="small">${msg.regDate.toDatetime()}</div>
				<div class="send-message talk-message">${msg.message}</div>
		    </div>
		</div>`;
	}, 
	receiveTempl : function(msg) {
		return `
		<div class="media my-1">			
		    <div class="media-body text-right mr-3 ml-5">
				<div class="small">${msg.regDate.toDatetime()}</div>
				<div  class="receive-message talk-message">${msg.message}</div>
		    </div>
		    <img class="d-flex mr-3 rounded-circle avata" 
				    	src="/butter/member/avata?userId=${msg.withTalk}">
		</div>`;	
	}
};


class Talk {
	constructor(opt){
		this.opt = opt;
		this.socket = new SockJS(this.opt.url + '/talk');
		this.socket.onopen = this.onopen.bind(this);
		this.socket.onmessage = this.onmessage.bind(this);
		this.socket.onclose = this.onclose.bind(this);		
		this.socket.onerror = this.onerror.bind(this);
		
		$.get(opt.url + '/talkList', {
			userId : this.opt.userId, 
			withTalk : this.opt.withTalk
		}, this.initMessage.bind(this));
	}
	
	initMessage(messages) {
		var self = this;
		messages.forEach( function(msg) {
			msg.regDate = new Date(msg.regDate)
			if(msg.received == 1) {	// 수신 데이터
				self.opt.panel.append(talkTempl.receiveTempl(msg));
			} else {	// 전송 데이터 
				self.opt.panel.append(talkTempl.sendTempl(msg));
			}
		});
		self.opt.panel.parent()
			.scrollTop(self.opt.panel.height());
	}

	onopen(){
		console.log('접속 성공');
	}
	
	onclose(){
		console.log('접속 해제');
	}
	
	onerror(e) {
		console.log('에러', e);
	}
	
	// 메시지 수신
	onmessage(msg) {
		this.opt.panel.append(talkTempl.receiveTempl(msg));
		this.opt.panel.parent()
				.scrollTop(this.opt.panel.height());
	}

	send(message) {
		var msg = {
				command : 'TALK',
				userId : this.opt.userId,
				withTalk : this.opt.withTalk,
				received : 0, 
				message : message,
				checked : 1				
		};
		this.socket.send(JSON.stringify(msg));
		msg.regDate = new Date();
		return msg;
	}
	
	addSendTempl(msg) {
		this.opt.panel.append(talkTempl.sendTempl(msg));
		this.opt.panel.parent()
				.scrollTop(this.opt.panel.height());		
	}
	
}

$.fn.talk = function(opt) {
	opt = $.extend(opt, {panel: this});
	var talk = new Talk(opt);
	
	function send() {
		var message = opt.sendMessage.val();	
		if(message.trim() == '') return; 
		var msg = talk.send(message);
		talk.addSendTempl(msg);
		opt.sendMessage.val('').focus();
	}
	
	// 전송 버튼 
	opt.sendBtn.click(send);
	// 메시지 작성 input 엘리먼트의 엔터 처리
	opt.sendMessage.keypress(function (e) {
        if (e.keyCode == 13){	// 엔터 인경우 
        	send();
        }
	});
}

