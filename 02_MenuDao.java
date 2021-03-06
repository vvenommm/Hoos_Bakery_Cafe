package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MenuDao {
	
	private MenuDao() {
		
	}
	private static MenuDao instance;
	public static MenuDao getInstance() {
		if(instance == null) {
			instance = new MenuDao();
		}
		return instance;
	}
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
	
	
	//빵 목록
	public List<Map<String,Object>> breadList(){
		String sql = "SELECT TO_NUMBER(SUBSTR(MENU_ID,2)),MENU_NAME, MENU_PRICE FROM B_MENU WHERE MENU_ID LIKE 'a%'";
		
		return JDBCUtil.selectList(sql);
	}

	
/////////////////////////////////////////////////////////////////////////////////

	
	//빵 상세화면
	public Map<String, Object> breadDetail(int menuNum){
		String sql = "SELECT MENU_ID, MENU_NAME, MENU_PRICE, MENU_DETAIL, MENU_ACTIVE, (SELECT MEM_NNAME FROM B_MEMBER WHERE MEM_ID = 'admin') MEM_NNAME FROM B_MENU WHERE TO_NUMBER(SUBSTR(MENU_ID,2)) =? AND MENU_ID LIKE 'a%'";
		List<Object>param = new ArrayList<Object>();
		param.add(menuNum);
		
		return JDBCUtil.selectOne(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//빵 리뷰&별점
	
		public List<Map<String, Object>> breadReview(int menuNum){
			String sql = "SELECT R.REVIEW_COMMENT, R.REVIEW_STAR, MB.MEM_NNAME " + 
		               " FROM B_MENU M, B_REVIEW R, B_MEMBER MB" + 
		               " WHERE TO_NUMBER(SUBSTR(M.MENU_ID,2)) =? AND M.MENU_ID LIKE 'a%' AND M.MENU_ID = R.MENU_ID AND MB.MEM_ID = R.MEM_ID";
			List<Object>param = new ArrayList<Object>();
			param.add(menuNum);
			
			return JDBCUtil.selectList(sql, param);
		}	
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//쿠키 목록
	public List<Map<String, Object>> cookieList(){
		String sql = "SELECT TO_NUMBER(SUBSTR(MENU_ID,2)), MENU_NAME, MENU_PRICE FROM B_MENU WHERE MENU_ID LIKE 'b%'";
		
		return JDBCUtil.selectList(sql);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//쿠키 상세화면
	public Map<String, Object> cookieDetail(int menuNum){
		String sql = "SELECT MENU_ID, MENU_NAME, MENU_PRICE, MENU_DETAIL, MENU_ACTIVE, (SELECT MEM_NNAME FROM B_MEMBER WHERE MEM_ID = 'admin') FROM B_MENU WHERE TO_NUMBER(SUBSTR(MENU_ID,2)) =? AND MENU_ID LIKE 'b%'";
		List<Object>param = new ArrayList<Object>();
		param.add(menuNum);
		
		return JDBCUtil.selectOne(sql, param);
	}

	
/////////////////////////////////////////////////////////////////////////////////

	
	//쿠키 리뷰&별점
	
		public List<Map<String, Object>> cookieReview(int menuNum){
			String sql = "SELECT R.REVIEW_COMMENT, R.REVIEW_STAR, MB.MEM_NNAME " + 
		               " FROM B_MENU M, B_REVIEW R, B_MEMBER MB" + 
		               " WHERE TO_NUMBER(SUBSTR(M.MENU_ID,2)) =? AND M.MENU_ID LIKE 'b%' AND M.MENU_ID = R.MENU_ID AND MB.MEM_ID = R.MEM_ID";
			List<Object>param = new ArrayList<Object>();
			param.add(menuNum);
			
			return JDBCUtil.selectList(sql, param);
		}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//케이크 목록
	public List<Map<String,Object>> cakeList(){
		String sql = "SELECT TO_NUMBER(SUBSTR(MENU_ID,2)), MENU_NAME, MENU_PRICE FROM B_MENU WHERE MENU_ID LIKE 'c%'";
		
		return JDBCUtil.selectList(sql);
	}

	
/////////////////////////////////////////////////////////////////////////////////

	
	//케이크 상세화면
	public Map<String, Object> cakeDetail(int menuNum){
		String sql = "SELECT MENU_ID, MENU_NAME, MENU_PRICE, MENU_DETAIL, MENU_ACTIVE FROM B_MENU WHERE TO_NUMBER(SUBSTR(MENU_ID,2)) =? AND MENU_ID LIKE 'c%'";
		List<Object>param = new ArrayList<Object>();
		param.add(menuNum);
		
		return JDBCUtil.selectOne(sql, param);
		
	}
	

/////////////////////////////////////////////////////////////////////////////////

	
	//케이크 리뷰&별점
	
	public List<Map<String, Object>> cakeReview(int menuNum){
		String sql = "SELECT R.REVIEW_COMMENT, R.REVIEW_STAR, MB.MEM_NNAME " + 
	               " FROM B_MENU M, B_REVIEW R, B_MEMBER MB" + 
	               " WHERE TO_NUMBER(SUBSTR(M.MENU_ID,2)) =? AND M.MENU_ID LIKE 'c%' AND M.MENU_ID = R.MENU_ID AND MB.MEM_ID = R.MEM_ID";
		List<Object>param = new ArrayList<Object>();
		param.add(menuNum);
		
		return JDBCUtil.selectList(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//음료 목록
	public List<Map<String,Object>> drinkList(){
		String sql = "SELECT TO_NUMBER(SUBSTR(MENU_ID,2)), MENU_NAME, MENU_PRICE FROM B_MENU WHERE MENU_ID LIKE 'd%'";
		
		return JDBCUtil.selectList(sql);
	}

	
/////////////////////////////////////////////////////////////////////////////////

	
	//음료 상세화면
	public Map<String, Object> drinkDetail(int menuNum){
		String sql = "SELECT MENU_ID, MENU_NAME, MENU_PRICE, MENU_DETAIL, MENU_ACTIVE FROM B_MENU WHERE TO_NUMBER(SUBSTR(MENU_ID,2)) =? AND MENU_ID LIKE 'd%'";
		List<Object>param = new ArrayList<Object>();
		param.add(menuNum);
		
		return JDBCUtil.selectOne(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//음료 리뷰&별점
	
	public List<Map<String, Object>> drinkReview(int menuNum){
		String sql = "SELECT R.REVIEW_COMMENT, R.REVIEW_STAR, MB.MEM_NNAME " + 
	               " FROM B_MENU M, B_REVIEW R, B_MEMBER MB" + 
	               " WHERE TO_NUMBER(SUBSTR(M.MENU_ID,2)) =? AND M.MENU_ID LIKE 'd%' AND M.MENU_ID = R.MENU_ID AND MB.MEM_ID = R.MEM_ID";
		List<Object>param = new ArrayList<Object>();
		param.add(menuNum);
		
		return JDBCUtil.selectList(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//장바구니 번호 검색
	public Map<String, Object> getCartNo() {
		String sql = "SELECT NVL(MAX(CART_NO),0) CART_NO FROM B_CART WHERE CART_STATE = '결제'";
		
		return JDBCUtil.selectOne(sql);
	}
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//메뉴 예약바구니에 넣기
	public int intoCart(int cartNo, String menuId, int qty, String memId) {
		String sql = "\r\n" + 
				"INSERT INTO B_CART(CART_NO, MEM_ID, MENU_ID, CART_PRICE, CART_QTY, CART_STATE) VALUES(?, ?, ?, (SELECT MENU_PRICE*? FROM B_MENU WHERE MENU_ID = ?), ?, '미결')";
		List<Object>param = new ArrayList<Object>();
		
		param.add(cartNo);
		param.add(memId);
		param.add(menuId);
		param.add(qty);
		param.add(menuId);
		param.add(qty);
	
		return JDBCUtil.update(sql, param);
	}

}
