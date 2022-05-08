package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class RSVDao {
	
private RSVDao() {
		
	}
	private static RSVDao instance;
	public static RSVDao getInstance() { 
		if(instance == null) {
			instance = new RSVDao();
		}  
		return instance;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////


	//[4.예약 바구니] 선택 시 예약 바구니의 홈.
	//예약 바구니에 있는 상품 출력
	public List<Map<String, Object>> rsvCart(String memId) {
		String sql = "SELECT C.MENU_ID, C.CART_NO CART_NO, M.MENU_NAME MENU_NAME, C.CART_QTY CART_QTY, C.CART_PRICE CART_PRICE FROM B_CART C, B_MENU M" + 
				" WHERE C.MENU_ID = M.MENU_ID" + 
				" AND C.CART_STATE = '미결'" + 
				" AND C.MEM_ID = ?" + 
				" UNION ALL" + 
				" SELECT NULL, NULL, '총합', SUM(C.CART_QTY), SUM(C.CART_PRICE) FROM B_CART C, B_MENU M" + 
				" WHERE C.MENU_ID = M.MENU_ID" + 
				" AND C.CART_STATE = '미결'" + 
				" AND C.MEM_ID = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(memId);
		param.add(memId);
		
		
		return JDBCUtil.selectList(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//수령일 비교하기
	public Map<String, Object> getDate(){
		String sql = "SELECT TO_NUMBER(TO_CHAR(SYSDATE, 'MMDD')) TODAY FROM DUAL";
		
		return JDBCUtil.selectOne(sql);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//[4.예약 바구니] -> 결제 내역에 넣기
	public int rsvToBuy(int cartNo, String memId, int totalPrice, int pickup, String etc) {
		String sql = "INSERT INTO B_BUY(BUY_NO, CART_NO, MEM_ID, BUY_MUSED, BUY_MWILL, BUY_TP, BUY_PICKUP, BUY_ETC, BUY_APRV) " + 
				"VALUES((SELECT LPAD((SELECT TO_CHAR(NVL(MAX(BUY_NO),TO_CHAR(SYSDATE,'MMDD')||'000')+1,'0000000') FROM B_BUY WHERE  SUBSTR(BUY_NO,1,4)  = TO_CHAR(SYSDATE,'MMDD'))+1, 7, '0') FROM DUAL), ?, ?, TO_DATE(?, 'YYMMDD'), ?, '신청')";
		List<Object> param = new ArrayList<Object>();
		
		param.add(cartNo); //예약 바구니 번호
		param.add(memId); //예약 바구니 주인(회원아이디)
		param.add(20220000+pickup); //수령일
		param.add(etc); //비고
		
		return JDBCUtil.update(sql, param);
	}
		
		   
	//[4.예약 바구니] -> '미결'에서 '결제'로 바꾸기
	public int rsvStateChg(String menuId, int cartNo) {
		String sql = "UPDATE B_CART SET CART_STATE = '결제' WHERE MENU_ID = ? AND CART_NO = ?";
		List<Object> param = new ArrayList<Object>();
		
		param.add(menuId);
		param.add(cartNo);
		
		return JDBCUtil.update(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//[4.예약 바구니] ->[2.수량 수정하기]
	public int editBuy(int cartQty, int cartNo, String menuId) {
		String sql = "UPDATE B_CART SET CART_QTY = ?, CART_PRICE = (SELECT MENU_PRICE*? FROM B_MENU WHERE MENU_ID = ?) WHERE CART_NO = ? AND MENU_ID = ?";
		List<Object> param = new ArrayList<Object>();
		
		param.add(cartQty);
		param.add(cartQty);
		param.add(menuId);
		param.add(cartNo);
		param.add(menuId);
		
		return JDBCUtil.update(sql, param);
	}
	
	
	//카트 번호 구하기
	public Map<String, Object> getCartNo(){
		String sql = "SELECT CART_NO FROM B_CART WHERE CART_STATE = '미결'";
		
		return JDBCUtil.selectOne(sql);
	}

	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//[4.예약 바구니] ->[3.메뉴 삭제하기]
	public int delBuy(int cartNo, String menuId) {
		String sql = "DELETE FROM B_CART WHERE CART_NO = ? AND MENU_ID = ?";
		List<Object> param = new ArrayList<Object>();
		
		param.add(cartNo);
		param.add(menuId);
		
		return JDBCUtil.update(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//결제 후 구매내역 영수증 보여주는 화면
	public List<Map<String, Object>> reciept(int paidNo, String paidID){
		String sql = "SELECT BUY_NO, CART_NO, MEM_ID, BUY_MUSED, BUY_MWILL, BUY_TP, BUY_PICKUP, BUY_ETC, BUY_APRV FROM B_BUY "
				+ "WHERE CART_NO = ? AND MEM_ID = ? AND BUY_APRV = '신청'";
		List<Object> param = new ArrayList<Object>();
		
		param.add(paidNo);
		param.add(paidID);
		return JDBCUtil.selectList(sql, param);
	}
}
