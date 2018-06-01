package com.lzj.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lzj.service.IProviderService;

import jsontag.annotation.JsonTagAnnotation;

@Service
@Component
public class ProviderServiceImpl implements IProviderService {

	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public String getName(String abc) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> mapList=this.jdbcTemplate.queryForList("select * from TLZServiceAreas");
		return mapList.size()+":hello:"+abc;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
