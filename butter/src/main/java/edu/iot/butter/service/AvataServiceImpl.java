package edu.iot.butter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iot.butter.dao.AvataDao;
import edu.iot.butter.model.Avata;

@Service
public class AvataServiceImpl implements AvataService {

	@Autowired
	AvataDao	dao;
	
	@Override
	public byte[] getAvata(String userId) throws Exception {
		Avata avata = dao.selectOne(userId);
		return avata.getImage();
	}

	@Override
	public boolean insertAvata(Avata avata) throws Exception {
		return dao.insert(avata) == 1;
	}

	@Override
	public boolean updateAvata(Avata avata) throws Exception {
		return dao.update(avata) == 1;
	}

	@Override
	public boolean deleteAvata(String userId) throws Exception {
		return dao.delete(userId) == 1;
	}

}
