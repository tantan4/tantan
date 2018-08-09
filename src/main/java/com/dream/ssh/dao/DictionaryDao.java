package com.dream.ssh.dao;

import java.util.List;

import com.dream.ssh.po.Dictionary;

public interface DictionaryDao {

	Dictionary findByTypeAndValue(String string, String value);

	List<Dictionary> findAllByType(String string);

	List<Dictionary> findAllBySearch(Integer start, Integer length, String search, String typeDir);

	Long countAllBySearch(String search);

	void create(Dictionary po);

	Dictionary findByTypeAndName(String type, String name);

}
