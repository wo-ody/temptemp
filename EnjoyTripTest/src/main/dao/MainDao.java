package main.dao;

import java.util.List;

import dto.SidoDto;

public interface MainDao {
	List<SidoDto> sidoList(); // 시도 (시도코드, 시도이름) 리스트
}
