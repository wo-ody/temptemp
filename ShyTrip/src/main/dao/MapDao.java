package main.dao;

import java.util.List;

import dto.MapDto;

public interface MapDao {
	List<MapDto> getPlace(String keyword);
	
	String getDescription(int content_id);
}
