package board.dao;

import java.util.List;

import board.dto.BoardDto;

public interface BoardDao {
    public int boardInsert(BoardDto dto);
    public int boardUpdate(BoardDto dto);
    public int boardDelete(int boardId);
    
    public BoardDto boardDetail(int boardId);
    
    public List<BoardDto> boardList(int limit, int offset);
    public int boardListTotalCnt();
    
    public List<BoardDto> boardListSearchWord(int limit, int offset, String searchWord);
    public int boardListSearchWordTotalCnt(String searchWord);
}