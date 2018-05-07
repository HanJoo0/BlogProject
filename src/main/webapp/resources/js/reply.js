
$.fn.replyBoard = function(opt, replyList) {
	var templ = $(replyTempl.replyAddTempl);
	var content = templ.find('#reply-content');
	templ.find('#reply-add').click(function(e){
		var reply = {
			boardId : opt.boardId,
			writer : opt.writer,
			parent : 0,
			replyLevel : 0,
			content : content.val() 	
		};		
		opt.api.create(reply, function(data) {
			data.regDate = new Date(data.regDate);
			content.val('');
			opt.replyList.prepend(
					replyTempl.mediaObjectTempl(data, opt.writer));
		});	
	})
	
	this.append(templ);
	return this;
}




// 1차원 배열을 트리 구조로 변경 
function makeDom(datas) {
	var dom = [];
	var objects = {};
	datas.forEach(data=>{
		data.regDate = new Date(data.regDate);
		data.children = [];
		objects[data.replyId] = data;		
		if(data.parent == 0) {	// 최상위 댓글
			dom.push(data);
		} else {	
			// 하위 댓글인 경우 부모의 children 배열에 추가 
			objects[data.parent].children.push(data);
		}		
	})
	return dom;
}

//트리 구조를 탐색하여 Media Object 구조 생성 
function makeMediaObject(reply, writer) {
	
	// 단말 노드
	if(reply.children.length==0) {
		return replyTempl.mediaObjectTempl(reply, writer);
	}

	var self = replyTempl.mediaObjectTempl(reply, writer);

	// 중간 노드 
	reply.children.forEach(child=>{
		var child = makeMediaObject(child, writer);
		 self.find('.children').eq(0).append(child);	// eq(0) 추가	
	});
	return self;
}

$.fn.replyList = function(opt) {
	var self = this;
	// opt = $.extend({target : this}, opt)
	// 초기 목록 구성 
	opt.api.list('', function(replyList) {
		makeDom(replyList).forEach(function(reply){
			self.append(makeMediaObject(reply, opt.writer))		
		}); 
	});
	
	
	// 댓글 쓰기 판넬 보여주기, this는 reply 버튼
	function showReplyPanel() {
		var parent = $(this).closest('.media-body');
		parent.children('.work')
			.append(replyTempl.addTempl);	// 하위 댓글 작성 추가
	}
	
	// 댓글 쓰기 판넬 제거하기, this는 취소 버튼
	function hideReplyPanel() {
		$(this).parent().empty();
	}	
	
	// 댓글 등록, this는 하위댓글 등록 버튼 
	function createReply() {
		var obj = $(this).closest('.media');
		var reply = {
			boardId 	: opt.boardId,
			writer 		: opt.writer,			
			parent 		: parseInt(obj.data('reply-id')),
			replyLevel 	: parseInt(obj.data('reply-level'))+1,
			content 	: obj.find('textarea').val()
		}
		opt.api.create(reply,function(result){
			if(result) {
				result.regDate = new Date(result.regDate);
				obj.find('.children').eq(0)	// 추가
					.prepend(
							replyTempl.mediaObjectTempl(result, reply.writer));
				obj.find('.work').empty();
			} else {
				alert('댓글 쓰기 실패')
			}
		});
	}
	// 하위 댓글 추가 관련
	this.on('click', '.reply-add-show', showReplyPanel);
	this.on('click', '.reply-cancel', hideReplyPanel);
	this.on('click', '.reply-add',createReply);
	
	
	// 댓글 수정 창 보이기, this는 수정 버튼 
	function showEditPanel() {		 		
		var content = $(this).closest('.media-body ')
							.children('.reply-content');
		var text = content.text();	// 현재 내용 추출
		content.empty()				// 현재 내용 화면 제거
				.data('old', text)	// 취소시 복원용 이전 데이터
				.append(replyTempl.editTempl);	// 수정창 추가 
		content.find('textarea').val(text);	// 수정창에 데이터 설정	
	}
	
	// 댓글 수정 창 제거, this는 취소 버튼
	function hideEditPanel() {
		var text = $(this).parent()
						.data('old');	// 이전 데이터 추출	
		$(this).parent()
				.empty()		// 현재 내용 제거
				.text(text);	// 이전 데이터로 복원
	}
	
	function editReply() {
		var obj = $(this).parent();
		var reply = {
			replyId : $(this).closest('.media').data('reply-id'),
			content : obj.find('textarea').val()
		};	
		
		api.update(reply, function(result){
			if(result) {
				result.regDate = new Date(result.regDate);
				obj.empty().text(reply.content);			
			} else {
				alert('수정 실패');
			}
		});
	}
	// 댓글 수정 관련
	this.on('click', '.reply-edit-show', showEditPanel);
	this.on('click', '.reply-edit-cancel',hideEditPanel);
	this.on('click', '.reply-edit', editReply);
	
	
	//댓글 삭제, this는 삭제 버튼 
	function deleteContent() {
		var self = $(this);	
		var media = $(this).closest('.media');
		opt.api.remove(media.data('reply-id'),	// replyId 추출 
					function(result){
			if(result) {	// 삭제 성공시 
				media.find('.reply-content').eq(0)
						.text('삭제된 글입니다.');
				self.parent().remove();	// 버튼 그룹 제거 
			} else {
				alert('삭제 실패');
			}
		});		
	}
	
	

	

	
	// 댓글 삭제
	this.on('click', '.reply-delete',deleteContent);	
	
	return this;

}
