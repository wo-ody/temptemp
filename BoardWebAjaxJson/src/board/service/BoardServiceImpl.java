package board.service;

import java.util.List;

import board.dao.BoardDao;
import board.dao.BoardDaoImpl;
import board.dto.BoardDto;

// Service Layer 의 리턴 값에 대한 개선 필요.
// true / false 형태로 Service Layer 의 작업 결과에 대해 반한한다던가 하는 부분
// try - catch 를 이용한 부분 등 검토
// Spring 에서 개선 BoardResultDto With try-catch
public class BoardServiceImpl implements BoardService {

    private static BoardServiceImpl instance = new BoardServiceImpl();
    
    private BoardServiceImpl() {}
    
    
    public static BoardServiceImpl getInstance() {
        return instance;
    }
    
    BoardDao dao = BoardDaoImpl.getInstance();
    
    @Override
    public int boardInsert(BoardDto dto) {
        return dao.boardInsert(dto);
    }

    @Override
    public int boardUpdate(BoardDto dto) {
        return dao.boardUpdate(dto);
    }

    @Override
    public int boardDelete(int boardId) {
        return dao.boardDelete(boardId);
    }

    @Override
    public BoardDto boardDetail(int boardId, int userSeq) { // 현재 세션 사용자의 userSeq
        BoardDto boardDto = dao.boardDetail(boardId);
        if( boardDto.getUserSeq() == userSeq ) {
            boardDto.setSameUser(true);
        }else {
            boardDto.setSameUser(false);
        }
        
        return boardDto;
    }

    @Override
    public List<BoardDto> boardList(int limit, int offset) {
        return dao.boardList(limit, offset);
    }


    @Override
    public int boardListTotalCnt() {
        return dao.boardListTotalCnt();
    }
    
    @Override
    public List<BoardDto> boardListSearchWord(int limit, int offset, String searchWord) {
        return dao.boardListSearchWord(limit, offset, searchWord);
    }


    
    @Override
    public int boardListSearchWordTotalCnt(String searchWord) {
        return dao.boardListSearchWordTotalCnt(searchWord);
    }

}
