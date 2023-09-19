package main.service;

import java.util.List;

import dto.SidoDto;
import main.dao.MainDao;
import main.dao.MainDaoImpl;

public class MainServiceImpl implements MainService{
	private MainDao maindao = new MainDaoImpl();
	
	@Override
	public List<SidoDto> sidoList() {
		return maindao.sidoList();
	}
	
}
