package com.ipartek.formacion.libro.DAO;

import java.util.List;

public interface CrudAble <T> {
	
	List<T> getAll();
	T getById(String id);
	
	void insert(T pojo);
	void update(T pojo);
	void delete(long id);

}
