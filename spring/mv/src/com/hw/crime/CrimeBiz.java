package com.hw.crime;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hw.frame.Biz;
import com.hw.frame.Dao;
import com.hw.vo.Crime;

@Service("crimeBiz")
public class CrimeBiz implements Biz<Crime, String> {

	@Resource(name="crimeDao")
	Dao<Crime, String> dao;

	@Override
	public void register(Crime t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modify(Crime t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Crime get(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Crime> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Crime> getoccur(String s) {
		// TODO Auto-generated method stub
		return dao.selectoccur(s);
	}

	@Override
	public List<Crime> getcatch(String s) {
		// TODO Auto-generated method stub
		return dao.selectcatch(s);
	}

	



}
