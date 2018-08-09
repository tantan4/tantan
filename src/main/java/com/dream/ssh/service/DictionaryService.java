package com.dream.ssh.service;

import java.util.List;

import com.dream.ssh.dto.DictionaryDto;
import com.dream.ssh.dto.StaffDto;
import com.dream.ssh.vo.DataTable;

public interface DictionaryService {

	List<DictionaryDto> findAllByType(String string);

	DataTable<DictionaryDto> findAllBySearch(Integer start, Integer length, String search, String typeDir);

	void create(DictionaryDto dto);

	List<DictionaryDto> findAllByTypeWithSelected(String string, StaffDto u);

}
