package edu.iot.butter.dao;

import java.util.List;

import edu.iot.butter.model.Pagination;

public interface BaseDao<M, K> {
	
	int getCount() throws Exception;
	
	List<M> selectList(Pagination pagination) throws Exception;

	M selectOne(K k) throws Exception;

	int insert(M m) throws Exception;

	int update(M m) throws Exception;

	int delete(K k) throws Exception;	

}
