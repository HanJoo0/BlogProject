package edu.iot.butter.service;

import java.util.List;

public interface BaseService<M, K> {
	List<M> getList() throws Exception;
	List<M> getList(int page) throws Exception;
	M getOne(K k)  throws Exception;
	boolean create(M m)  throws Exception;
	boolean update(M m)  throws Exception;
	boolean delete(M m)  throws Exception;

}
