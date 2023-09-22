package board.service;

import java.util.List;

import board.dto.BoardDto;

public interface BoardService {
    int boardInsert(BoardDto dto);
    int boardUpdate(BoardDto dto);
    int boardDelete(int boardId);
    
    BoardDto boardDetail(int boardId, int userSeq);
    
    List<BoardDto> boardList(int limit, int offset);
    int boardListTotalCnt();
    
    List<BoardDto> boardListSearchWord(int limit, int offset, String searchWord);
    int boardListSearchWordTotalCnt(String searchWord);
}