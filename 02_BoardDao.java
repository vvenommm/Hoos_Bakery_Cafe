package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;
import util.View;

public class BoardDao {
	
	//싱글톤 패턴
	public BoardDao() {
					
	}
	public static BoardDao instance;
	public static BoardDao getInstance() { 
		if(instance == null) {
			instance = new BoardDao();
		}
		return instance;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
	
	
	//모든 글 출력
	public List<Map<String, Object>> allBoardList(){
		String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_CONTENT, (SELECT MEM_NNAME FROM B_MEMBER WHERE MEM_ID = 'admin') MEM_NNAME, TO_CHAR(BOARD_DATE, 'YY/MM/DD') BOARD_DATE FROM B_BOARD ORDER BY BOARD_NO DESC";
		return JDBCUtil.selectList(sql);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//공지사항 출력
	public List<Map<String, Object>> noticeList(){
		String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_CONTENT, (SELECT MEM_NNAME FROM B_MEMBER WHERE MEM_ID = 'admin') MEM_NNAME, TO_CHAR(BOARD_DATE, 'YY/MM/DD') BOARD_DATE FROM B_BOARD WHERE BOARD_CODE = 1 ORDER BY BOARD_NO DESC";
		return JDBCUtil.selectList(sql);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//이벤트 출력
	public List<Map<String, Object>> eventList(){
		String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_CONTENT, (SELECT MEM_NNAME FROM B_MEMBER WHERE MEM_ID = 'admin') MEM_NNAME, TO_CHAR(BOARD_DATE, 'YY/MM/DD') BOARD_DATE FROM B_BOARD WHERE BOARD_CODE = 2 ORDER BY BOARD_NO DESC";
		return JDBCUtil.selectList(sql);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//글읽기
	public Map<String, Object> readPost(int boardNo){
		String sql = "SELECT BOARD_NO, BOARD_TITLE, BOARD_CONTENT, (SELECT MEM_NNAME FROM B_MEMBER WHERE MEM_ID = 'admin') MEM_NNAME, TO_CHAR(BOARD_DATE, 'YY/MM/DD') BOARD_DATE FROM B_BOARD WHERE BOARD_NO = ?";
		List<Object> param = new ArrayList<Object>();

		param.add(boardNo);
		return JDBCUtil.selectOne(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//게시판 관리 : 글 수정, 삭제, 작성
	
		//글 수정
		public int editPost(String title, String content, Map<String, Object> board){
			String sql = "UPDATE B_BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ? WHERE BOARD_NO = ?";
			List<Object> param = new ArrayList<Object>();
			
			int num = (int)board.get("BOARD_NO");
			
			param.add(title);
			param.add(content);
			param.add(num);
			
			return JDBCUtil.update(sql, param);
		}
		
		
	/////////////////////////////////////////////////////////////////////////////////
		
		
		//글 삭제
		public int deletePost(Map<String, Object> board) {
			String sql = "DELETE FROM B_BOARD WHERE BOARD_NO = ?";
			List<Object> param = new ArrayList<Object>();
			
			param.add(board.get("BOARD_NO"));
			return JDBCUtil.update(sql, param);
		}
		
		
	/////////////////////////////////////////////////////////////////////////////////
		
		
		//글 작성
		public int insertPost(List<Object> input) {
			String sql = "INSERT INTO B_BOARD(BOARD_NO, BOARD_CODE, BOARD_TITLE, BOARD_CONTENT, MEM_ID, BOARD_DATE) VALUES((SELECT MAX(NVL(BOARD_NO, 0))+1 FROM B_BOARD), ?, ?, ?, 'admin', SYSDATE)";
			
			return JDBCUtil.update(sql, input);
		}	

}
