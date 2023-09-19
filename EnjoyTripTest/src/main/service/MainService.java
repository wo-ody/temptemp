package main.service;

import java.util.List;

import dto.SidoDto;

public interface MainService {
	List<SidoDto> sidoList(); // 시도 (시도코드, 시도이름) 리스트
}
