package edu.iot.butter.service;

import edu.iot.butter.model.Avata;

public interface AvataService {
	// 아바타 처리
	byte[] getAvata(String userId) throws Exception;
	boolean insertAvata(Avata avata)throws Exception;
	boolean updateAvata(Avata avata)throws Exception;
	boolean deleteAvata(String userId) throws Exception;
}
