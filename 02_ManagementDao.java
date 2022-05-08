package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class ManagementDao {
	
	//싱글톤 패턴
	public ManagementDao() {
			
	}
	public static ManagementDao instance; 
	public static ManagementDao getInstance() { 
		if(instance == null) {
			instance = new ManagementDao();
		}
		return instance;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
	//MNG_MENU [1.메뉴관리]
	
	
	//모든 메뉴 5개씩 출력
	
	public List<Map<String, Object>> allProdList(){
		String sql = "SELECT U.* " + 
				" FROM " + 
				" ( " + 
				"    SELECT ROWNUM RNUM, T.* " + 
				"    FROM " + 
				"    ( " + 
				"        SELECT MENU_ID FROM B_MENU " + 
				"        WHERE  MENU_ID LIKE '?%' " + 
				"        ORDER BY MENU_ID DESC " + 
				"    ) T " + 
				" ) U " + 
				" WHERE U.RNUM <= 5";
		
		
		
		return JDBCUtil.selectList(sql);
	}

	
/////////////////////////////////////////////////////////////////////////////////

	
	//각 제품 3개씩 출력
	public List<Map<String, Object>> breadList(){
		String sql = "SELECT U.* " + 
				" FROM " + 
				" ( " + 
				"    SELECT ROWNUM RNUM, T.* " + 
				"    FROM " + 
				"    ( " + 
				"        SELECT MENU_NAME, MENU_PRICE, SUBSTR(MENU_DETAIL, 1, 10) || '...' MENU_DETAIL FROM B_MENU " + 
				"        WHERE  MENU_ID LIKE 'a%' " + 
				"        ORDER BY MENU_ID DESC " + 
				"    ) T " + 
				" ) U " + 
				" WHERE U.RNUM <= 3";
		
		return JDBCUtil.selectList(sql);
	}
	
	public List<Map<String, Object>> cookieList(){
		String sql = "SELECT U.* " + 
				" FROM " + 
				" ( " + 
				"    SELECT ROWNUM RNUM, T.* " + 
				"    FROM " + 
				"    ( " + 
				"        SELECT MENU_NAME, MENU_PRICE, SUBSTR(MENU_DETAIL, 1, 10) || '...' MENU_DETAIL FROM B_MENU " + 
				"        WHERE  MENU_ID LIKE 'b%' " + 
				"        ORDER BY MENU_ID DESC " + 
				"    ) T " + 
				" ) U " + 
				" WHERE U.RNUM <= 3";
		
		return JDBCUtil.selectList(sql);
	}
	
	public List<Map<String, Object>> cakeList(){
		String sql = "SELECT U.* " + 
				" FROM " + 
				" ( " + 
				"    SELECT ROWNUM RNUM, T.* " + 
				"    FROM " + 
				"    ( " + 
				"        SELECT MENU_NAME, MENU_PRICE, SUBSTR(MENU_DETAIL, 1, 10) || '...' MENU_DETAIL FROM B_MENU " + 
				"        WHERE  MENU_ID LIKE 'c%' " + 
				"        ORDER BY MENU_ID DESC " + 
				"    ) T " + 
				" ) U " + 
				" WHERE U.RNUM <= 3";
		
		return JDBCUtil.selectList(sql);
	}
	
	public List<Map<String, Object>> drinkList(){
		String sql = "SELECT U.* " + 
				" FROM " + 
				" ( " + 
				"    SELECT ROWNUM RNUM, T.* " + 
				"    FROM " + 
				"    ( " + 
				"        SELECT MENU_NAME, MENU_PRICE, SUBSTR(MENU_DETAIL, 1, 10) || '...' MENU_DETAIL FROM B_MENU " + 
				"        WHERE  MENU_ID LIKE 'd%' " + 
				"        ORDER BY MENU_ID DESC " + 
				"    ) T " + 
				" ) U " + 
				" WHERE U.RNUM <= 3";
		
		return JDBCUtil.selectList(sql);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//MNG_LIST_MENU [1.메뉴 리스트 보기]
	public List<Map<String, Object>> listMenu(String type){
		String sql = "";
		if(type.equals("a")) {
			sql = "SELECT TO_NUMBER(SUBSTR(MENU_ID, 2)) MENU_ID, MENU_NAME, MENU_PRICE, SUBSTR(MENU_DETAIL, 1, 8) || '...' MENU_DETAIL FROM B_MENU WHERE MENU_ID LIKE 'a%'";
		}else if(type.equals("b")) {
			sql = "SELECT TO_NUMBER(SUBSTR(MENU_ID, 2)) MENU_ID, MENU_NAME, MENU_PRICE, SUBSTR(MENU_DETAIL, 1, 8) || '...' MENU_DETAIL FROM B_MENU WHERE MENU_ID LIKE 'b%'";
		}else if(type.equals("c")) {
			sql = "SELECT TO_NUMBER(SUBSTR(MENU_ID, 2)) MENU_ID, MENU_NAME, MENU_PRICE, SUBSTR(MENU_DETAIL, 1, 8) || '...' MENU_DETAIL FROM B_MENU WHERE MENU_ID LIKE 'c%'";
		}else{
			sql = "SELECT TO_NUMBER(SUBSTR(MENU_ID, 2)) MENU_ID, MENU_NAME, MENU_PRICE, SUBSTR(MENU_DETAIL, 1, 8) || '...' MENU_DETAIL FROM B_MENU WHERE MENU_ID LIKE 'd%'";
		}
		return JDBCUtil.selectList(sql);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//MNG_READ_MENU [1.조회]
	public Map<String, Object> readMenu(int menuNo){
		String sql = "SELECT MENU_ID, MENU_NAME, MENU_PRICE, MENU_ACTIVE, MENU_DETAIL FROM B_MENU WHERE MENU_ID = ?";
		
		List<Object> param = new ArrayList<Object>();
		param.add(menuNo);
		
		return JDBCUtil.selectOne(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//MNG_EDIT_MENU [1-1.메뉴 수정]
	public int editMenu(String name, int price, String detail, String menuId){
		String sql = "INSERT B_MENU(MENU_NAME, MENU_PRICE, MENU_DETAIL) VALUES(?, ?, ?) WHERE MENU_ID = ?";
		List<Object> param = new ArrayList<Object>();
		
		param.add(name);
		param.add(price);
		param.add(detail);
		param.add(menuId);
		
		return JDBCUtil.update(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//MNG_DEL_MENU [1-2.메뉴 항목 삭제]
	public int delMenu(String menuId) {
		String sql = "DELETE FROM B_BOARD WHERE BOARD_NO = ?";
		List<Object> param = new ArrayList<Object>();
		
		param.add(menuId);
		return JDBCUtil.update(sql, param);
	}
	
			
/////////////////////////////////////////////////////////////////////////////////
			
			
	//MNG_INSERT_MENU [2.메뉴 추가]
	public int insertMenu(String type, String name, int price, String detail){
		String sql = "INSERT INTO B_MENU(MENU_ID, MENU_NAME, MENU_PRICE, MENU_DETAIL, MENU_ACTIVE) VALUES((SELECT ? || TRIM(SUBSTR(NVL(MAX(MENU_ID), 0), 2) + 1) FROM B_MENU), ?, ?, ?, ?)";
		List<Object> param = new ArrayList<Object>();

		param.add(type);
		param.add(name);
		param.add(price);
		param.add(detail);
		param.add("활성");
		return JDBCUtil.update(sql, param);
	}

	
/////////////////////////////////////////////////////////////////////////////////

	
	//MNG_ACT_MENU [3.메뉴 항목 활성화]
	public List<Map<String, Object>> deactedList() {
		String sql = "SELECT MENU_ID, MENU_NAME, MENU_PRICE, MENU_ACTIVE FROM B_MENU WHERE MENU_ACTIVE LIKE '비%'";
		return JDBCUtil.selectList(sql);
	}
	
	public int actvating(String code){
		System.out.println("code : " + code);
		String sql = "UPDATE B_MENU SET MENU_ACTIVE = '활성' WHERE TRIM(MENU_ID) = ?";
		List<Object> param = new ArrayList<Object>();

		param.add(code);
		return JDBCUtil.update(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//MNG_DEACTIVATED [2. 메뉴 죽이기]
	public List<Map<String, Object>> actedList() {
		String sql = "SELECT MENU_ID, MENU_NAME, MENU_PRICE, MENU_ACTIVE FROM B_MENU WHERE MENU_ACTIVE LIKE '활%'";
		return JDBCUtil.selectList(sql);
	}
	
	public int deactvating(String code){
		String sql = "UPDATE B_MENU SET MENU_ACTIVE = '활성' WHERE MENU_ID = ?";
		List<Object> param = new ArrayList<Object>();

		param.add(code);
		return JDBCUtil.update(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
	//MNG_ORDER [2.예약 관리]
	
	
	//MNG_ORDER [2.예약 관리] 홈. 앞으로 찾아갈 승인된 예약 주문들
	public List<Map<String, Object>> aprvedOrder(){
		String sql = "SELECT SUBSTR(BUY_NO, 1, 1) || '월 ' || SUBSTR(BUY_NO, 2, 2) || '일' RSV_PO, CART_NO, BUY_NO , BUY_PICKUP, BUY_ETC, MEM_ID FROM B_BUY WHERE BUY_APRV LIKE '승인' AND BUY_PICKUP > SYSDATE";
		return JDBCUtil.selectList(sql);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	public Map<String, Object> readOrder(int rsvNum) {
		String sql = "SELECT BUY_NO, BUY_PICKUP, BUY_ETC, MEM_ID, BUY_APRV FROM B_BUY WHERE BUY_NO = ?";

		List<Object> param = new ArrayList<Object>();

		param.add(rsvNum);
		return JDBCUtil.selectOne(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//MNG_ORDER [2.예약 관리] - [2.예약 승인하기]
	
	//새로 예약해서 승인을 기다리는 주문 목록
		public List<Map<String, Object>> newOrder(){
			  String sql = "SELECT '22/' || SUBSTR(BUY_NO, 1,2) || '/' || SUBSTR(BUY_NO, 3,2) as BUY_ORDER, BUY_NO, TO_CHAR(BUY_PICKUP, 'YY/MM/DD') BUY_PICKUP, BUY_ETC, MEM_ID, BUY_APRV FROM B_BUY WHERE BUY_APRV LIKE '신청'";

			return JDBCUtil.selectList(sql);
		}
	
	
/////////////////////////////////////////////////////////////////////////////////

		
		//'신청'에서 승인으로
	   public int chgAprv(String orderNum, String memId) {
		   String sql = "UPDATE B_BUY SET BUY_APRV = '승인' WHERE BUY_NO = ? AND MEM_ID = ?";
	       List<Object>param = new ArrayList<Object>();

	      
	      param.add(orderNum);
	      param.add(memId);
	      return JDBCUtil.update(sql, param);
	            
	   }
	
	
/////////////////////////////////////////////////////////////////////////////////
	   
	   
	  //'신청'에서 거절로
	 //'신청'에서 승인으로
	   public int chgDeny(String buyNo, String memId) {
	      String sql = "UPDATE B_BUY SET BUY_APRV = '거절' WHERE BUY_NO = ? AND MEM_ID = ?";
	      List<Object>param = new ArrayList<Object>();
	      
	      param.add(buyNo);
	      param.add(memId);
	      return JDBCUtil.update(sql, param);
	            
	   }
	   
	   
/////////////////////////////////////////////////////////////////////////////////

	
	//MNG_ORDER [2.예약 관리] - [3.거절한 예약 목록 보기]
	public List<Map<String, Object>> orderDenied() {
		String sql = "SELECT BUY_NO, CART_NO, MEM_ID, BUY_MUSED, BUY_MWILL, BUY_TP, BUY_PICKUP, BUY_ETC, BUY_APRV FROM B_BUY WHERE BUY_APRV = '거절'";

		return JDBCUtil.selectList(sql);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
	
	
	//MNG_REVIEW [3.한줄평 관리]
	   
	   public List<Map<String,Object>> reviewList() { //한줄평 보기
	      String sql = "SELECT R.BUY_NO BUY_NO, R.MEM_ID MEM_ID, substr(R.MENU_ID, 1, 3)||'...' MENU_ID, TO_CHAR(R.REVIEW_DATE, 'YY/MM/DD') REVIEW_DATE,  R.REVIEW_COMMENT REVIEW_COMMENT, R.REVIEW_STAR REVIEW_STAR, M.MEM_NNAME MEM_NNAME, substr(MN.MENU_NAME, 1, 3)||'..' MENU_NAME FROM B_REVIEW R, B_MEMBER M, B_MENU MN WHERE R.MEM_ID = M.MEM_ID AND MN.MENU_ID = R.MENU_ID ";
	      return JDBCUtil.selectList(sql);

	   }
	   
	   public int reviewDelete(String reviewNum) { //한줄평 삭제
	      String sql = "DELETE FROM B_REVIEW WHERE BUY_NO =?";
	      List<Object>param = new ArrayList<Object>();
	      param.add(reviewNum);
	      return JDBCUtil.update(sql, param);
	      
	   }
	
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
	   
	   
	   //MNG_BAKING [4.생산량 관리]
	      public int today(int pro){
	         String sql = "UPDATE B_ORDER SET ORDER_QTY = ? WHERE to_char(ORDER_DATE, 'YY-MM-DD') = to_char(SYSDATE, 'YY-MM-DD')";
	         List<Object> param = new ArrayList<Object>();
	         
	         param.add(pro);
	      
	      return JDBCUtil.update(sql, param);
	      }
	      
	      //예약 목록
	      public List<Map<String, Object>> todayOrder(){
	         String sql = "SELECT to_char(BUY_PICKUP, 'YYMMDD') as BUY_PICKUP, RSV_OD, RSV_ETC, MEM_ID, RSV_QTY FROM B_RSV WHERE RSV_APRV LIKE '승인' AND RSV_PICKUP >= to_char(SYSDATE, 'YYMMDD')";
	         return JDBCUtil.selectList(sql);
	      }
	      
	      //금일 생산량 수정 
	      public int changeOrder(int changeOrder) {
	         String sql = "UPDATE B_ORDER SET ORDER_QTY = ? WHERE to_char(ORDER_DATE, 'YY-MM-DD') = to_char(SYSDATE, 'YY-MM-DD')";
	         List<Object> param = new ArrayList<Object>();
	         
	         param.add(changeOrder);
	         return JDBCUtil.update(sql, param);
	      }
	      
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////

	      
	   //MNG_SALES [5.매출 관리]
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


} 
