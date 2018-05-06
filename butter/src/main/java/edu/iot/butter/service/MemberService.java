package edu.iot.butter.service;

import java.util.List;

import edu.iot.butter.model.Avata;
import edu.iot.butter.model.Login;
import edu.iot.butter.model.Member;
import edu.iot.butter.model.Pagination;
import edu.iot.butter.model.Password;

public interface MemberService {
	Member checkLogin(Login login) throws Exception;

	boolean checkId(String userId) throws Exception;

	boolean create(Member member) throws Exception;
	
	boolean update(Member member) throws Exception;
	
	Member getMember(String userId) throws Exception;
	
	boolean changePassword(Password password) throws Exception;

	Pagination getPagination(int page) throws Exception ;
	
	List<Member> getList(Pagination pagination) throws Exception;

	
	// 관리자만 호출하는 메서드
	boolean updateByAdmin(Member member) throws Exception;

	boolean changePasswordByAdmin(Password password) throws Exception;

	List<Member> getListWithMessages(String userId) throws Exception;
	
	// 아바타 처리
	byte[] getAvata(String userId) throws Exception;
	boolean insertAvata(Avata avata)throws Exception;
	boolean updateAvata(Avata avata)throws Exception;
	boolean deleteAvata(String userId) throws Exception;

}
