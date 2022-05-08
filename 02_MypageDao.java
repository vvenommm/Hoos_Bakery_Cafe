package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import service.UserService;
import util.JDBCUtil;

public class MypageDao {
	
	//싱글톤 패턴
		public MypageDao() {
				
		}
		public static MypageDao instance; 
		public static MypageDao getInstance() { 
			if(instance == null) {
				instance = new MypageDao();
			}
			return instance;
		}
		
		
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////
		
		
		//[1.마이페이지] -> [1.내정보 보기]
		//전화번호 중복 확인 매서드
		public Map<String, Object> bringNumber(String memNumber){
			String sql ="SELECT MEM_NUMBER"
					+ "		FROM B_MEMBER"
					+ "		WHERE MEM_NUMBER = ?";
			List<Object> param = new ArrayList<Object>();
			param.add(memNumber);
			return JDBCUtil.selectOne(sql, param);
		}
		
		
	/////////////////////////////////////////////////////////////////////////////////
		
		
		//마이페이지 -> 전화번호 변경
		public int changeNumber(String changeNumber) {
			String sql = "UPDATE B_MEMBER SET MEM_NUMBER = ? WHERE MEM_NUMBER = ?";
			List<Object> param = new ArrayList<Object>();
			
			param.add(changeNumber);
			param.add(UserService.LoginMember.get("MEM_NUMBER"));
			
			return JDBCUtil.update(sql, param);
		}

		
	/////////////////////////////////////////////////////////////////////////////////

		
	      public int changePassWord(String changePassWord) {
	          String sql = "UPDATE B_MEMBER SET MEM_PW = ? WHERE MEM_PW = ?";
	          List<Object> param = new ArrayList<Object>();
	          
	          param.add(changePassWord);
	          param.add(UserService.LoginMember.get("MEM_PW"));
	          
	          return JDBCUtil.update(sql, param);
	       }
		
		
	/////////////////////////////////////////////////////////////////////////////////
			
		
		//내 예약 현황 -> 내가 한 예약들 목록
		public List<Map<String, Object>> myReserv(){
			String sql = "SELECT C.CART_NO, SUBSTR(B.BUY_NO, 1,2)||'/'||SUBSTR(B.BUY_NO, 3,2) BUY_DATE, B.BUY_NO, B.BUY_MUSED, B.BUY_MWILL, B.BUY_TP, to_char(B.BUY_PICKUP, 'MM/DD') as BUY_PICKUP, B.BUY_ETC, B.BUY_APRV FROM B_BUY B, B_CART C WHERE B.CART_NO = C.CART_NO AND B.MEM_ID = C.MEM_ID AND B.MEM_ID = ? order by c.cart_no";
            
            List<Object> param = new ArrayList<Object>();
            param.add(UserService.LoginMember.get("MEM_ID"));
            return JDBCUtil.selectList(sql, param);
         }
		
		//내 예약 현황 -> 내가 한 예약들 목록 -> 조회
		public List<Map<String, Object>> myReservDetail(int cartNo){
			String sql = "SELECT C.CART_NO, C.MEM_ID, A.MENU_NAME, C.CART_PRICE, C.CART_QTY, C.CART_STATE\r\n" + 
					"FROM B_CART C, (SELECT MENU_ID, MENU_NAME FROM B_MENU) A\r\n" + 
					"WHERE C.MENU_ID = A.MENU_ID \r\n" + 
					"AND MEM_ID = ?\r\n" + 
					"AND CART_NO = ?\r\n" + 
					"UNION ALL\r\n" + 
					"SELECT null, null, '총합', SUM(CART_PRICE), SUM(CART_QTY), NULL\r\n" + 
					"FROM B_CART C, (SELECT MENU_ID, MENU_NAME FROM B_MENU) A\r\n" + 
					"WHERE C.MENU_ID = A.MENU_ID \r\n" + 
					"AND MEM_ID = ?\r\n" + 
					"AND CART_NO = ?";
			
			List<Object> param = new ArrayList<Object>();
			param.add(UserService.LoginMember.get("MEM_ID"));
			param.add(cartNo);
			param.add(UserService.LoginMember.get("MEM_ID"));
			param.add(cartNo);
			
			return JDBCUtil.selectList(sql, param);
		}

	     
	/////////////////////////////////////////////////////////////////////////////////
	 	
			
		 //내 한줄평 보기 -> 내가 쓴 한줄평 목록 보기
	      public List<Map<String, Object>>bringReview(String memId){
	            String sql = "SELECT R.BUY_NO, R.MEM_ID, R.MENU_ID, TO_CHAR(R.REVIEW_DATE, 'YY-MM-DD') REVIEW_DATE , R.REVIEW_COMMENT, R.REVIEW_STAR, M.MEM_NNAME, MN.MENU_NAME FROM B_REVIEW R, B_MEMBER M, B_MENU MN WHERE R.MEM_ID = M.MEM_ID AND MN.MENU_ID = R.MENU_ID AND R.MEM_ID = ?";
	         
	            List<Object> param = new ArrayList<Object>();
	            param.add(memId);
	            return JDBCUtil.selectList(sql, param);
	         }
			
			
		/////////////////////////////////////////////////////////////////////////////////
	     
	     
	     public int cancleReserv() {
			String sql = "UPDATE B_BUY SET BUY_APRV = '취소' WHERE BUY_APRV LIKE '승인' AND MEM_ID = ?";
			List<Object> param = new ArrayList<Object>();
				
			param.add(UserService.LoginMember.get("MEM_ID"));
			return JDBCUtil.update(sql, param);
		}	

}
