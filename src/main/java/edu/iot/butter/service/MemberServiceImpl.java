package edu.iot.butter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.iot.butter.dao.AvataDao;
import edu.iot.butter.dao.MemberDao;
import edu.iot.butter.exception.LoginFailException;
import edu.iot.butter.model.Avata;
import edu.iot.butter.model.Login;
import edu.iot.butter.model.Member;
import edu.iot.butter.model.Pagination;
import edu.iot.butter.model.Password;
import edu.iot.butter.util.ImageUtil;
import net.coobird.thumbnailator.Thumbnails;

@Service
public class MemberServiceImpl 
			implements MemberService{

	@Autowired
	MemberDao dao;
	
	@Autowired
	AvataDao avataDao;
	
	@Override
	public Member checkLogin(Login login) throws Exception {
		Member member = dao.selectOne(login.getUserId());
		if(member != null && 
				member.getPassword().equals(login.getPassword())) {
			return member;
		}
		throw new LoginFailException("사용자 ID 또는 비밀번호가 일치하지 않습니다.");
	}

	@Override
	public boolean checkId(String userId) throws Exception {
		Member member = dao.selectOne(userId);
		return member != null;		
	}
	
	@Transactional
	@Override
	public boolean create(Member member)  throws Exception{
		int result = dao.insert(member);
		return result == 1;
	}
	
	
	

	@Transactional
	@Override
	public boolean update(Member member) throws Exception {
		int result = dao.update(member);
		 
		
		return result == 1;
	}

	@Override
	public Member getMember(String userId) throws Exception {
		return dao.selectOne(userId);
	}

	@Transactional
	@Override
	public boolean changePassword(Password password) throws Exception {
		return dao.changePassword(password) == 1;
	}

	@Override
	public Pagination getPagination(int page) throws Exception {
		int total = 123; //dao.getCount();
		return new Pagination(page, total);
	}

	@Override
	public List<Member> getList(Pagination pagination) 
				throws Exception {
		return dao.selectList(pagination);
	}

	@Transactional
	@Override
	public boolean updateByAdmin(Member member) throws Exception {
		return dao.updateByAdmin(member) == 1;
	}

	@Transactional
	@Override
	public boolean changePasswordByAdmin(Password password) throws Exception {
		return dao.changePasswordByAdmin(password) == 1;
	}
	
	@Override
	public List<Member> getListWithMessages(String userId) throws Exception {
		return dao.selectListWithMessages(userId);
	}

	// 아바타 처리 
	@Override
	public byte[] getAvata(String userId) throws Exception {
		Avata avata = avataDao.selectOne(userId);
		if(avata==null) {		// 아바타 이미지가 없는 경우
			avata = avataDao.selectOne("anonymous");			
		}
		return avata.getImage();
	}

	
	
	@Override
	public boolean insertAvata(Avata avata) throws Exception {
		avata.setImage(ImageUtil.makeThumb(avata.getImage()));
		return avataDao.insert(avata) == 1;
	}

	@Override
	public boolean updateAvata(Avata avata) throws Exception {
		
		avata.setImage(ImageUtil.makeThumb(avata.getImage()));
		if(avataDao.update(avata) == 1) {
			return true;
		} else {
			// 존재하지 않는 경우 
			return avataDao.insert(avata) == 1;
		}
	}

	@Override
	public boolean deleteAvata(String userId) throws Exception {
		return avataDao.delete(userId) == 1;
	}
	
}


