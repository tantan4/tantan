package com.dream.ssh.service.impl;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dream.ssh.dao.DictionaryDao;
import com.dream.ssh.dto.DictionaryDto;
import com.dream.ssh.dto.StaffDto;
import com.dream.ssh.po.Dictionary;
import com.dream.ssh.service.DictionaryService;
import com.dream.ssh.vo.DataTable;

@Service
@Transactional
public class DictionaryServiceImpl implements DictionaryService {
	private final static Logger LOG = LogManager.getLogger(DictionaryServiceImpl.class);

	@Resource
	private DictionaryDao dictionaryDao;
	
	@Override
	public List<DictionaryDto> findAllByType(String string) {
		List<Dictionary> pos = dictionaryDao.findAllByType (string);
		return DictionaryDto.getDtos(pos);
	}

	@Override
	public DataTable<DictionaryDto> findAllBySearch(Integer start, Integer length, String search, String typeDir) {
		List<Dictionary> pos = this.dictionaryDao.findAllBySearch(start, length, search, typeDir);
		Long count = (Long) this.dictionaryDao.countAllBySearch (search);
		DataTable<DictionaryDto> dt = new DataTable<DictionaryDto> ();
		dt.setData(DictionaryDto.getDtos(pos));
		dt.setRecordsFiltered(count);
		dt.setRecordsTotal(count);
		return dt;
	}

	@Override
	public void create(DictionaryDto dto) {
		Dictionary d2 = this.dictionaryDao.findByTypeAndName(dto.getType(), dto.getName());
		if (d2 != null) {
			throw new RuntimeException ("该数据字典已经存在！");
		}
		Dictionary po = new Dictionary ();
		po.setDescription(dto.getDescription());
		po.setName(dto.getName());
		po.setType(dto.getType());
		po.setValue(dto.getValue());
		this.dictionaryDao.create(po);
	}

	@Override
	public List<DictionaryDto> findAllByTypeWithSelected(String string, StaffDto u) {
		List<Dictionary> pos = dictionaryDao.findAllByType (string);
		List<DictionaryDto> dtos = DictionaryDto.getDtos(pos);
		for(DictionaryDto dto:dtos){
			if(dto.getName().equals(u.getGender())){
				dto.setSelected(true);
			}
		}
		return dtos;
	}
}
