package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.RSVDao;
import util.JDBCUtil;
import util.PrintBlankUtil;
import util.ScanUtil;
import util.View;

public class RSVService {
	
	private RSVService() {
		
	}
	private static RSVService instance;
	public static RSVService getInstance() { 
		if(instance == null) {
			instance = new RSVService();
		}  
		return instance;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////


	private static RSVDao rsvDao = RSVDao.getInstance();
	private static UserService userService = UserService.getInstance();
	List<Map<String, Object>> rsvMenuList;
	int paidNo = 0;
	String paidID = "";
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//[4.예약 바구니] 선택 시 예약 바구니의 홈
	public int rsvCart() {
		String memId = (String) userService.LoginMember.get("MEM_ID");
		rsvMenuList = rsvDao.rsvCart(memId);
		
		System.out.println(rsvMenuList.size());
		Map<String, Object>svRow = new HashMap<String, Object>();
		String total1 = "";
		int total2 = 0;
		int total3 = 0;
		int num = 1;
		int input = 0;

		
		System.out.println("\t< 예약 바구니 >");
		
		if(rsvMenuList.size() > 1) {
		
		String menu = " 메뉴명";
		String qtyString = "수량";
		String priceString = "금액";
		
		System.out.println("\t┌────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│" + PrintBlankUtil.printBlank(menu, 45) + PrintBlankUtil.printBlank(qtyString, 20) + PrintBlankUtil.printBlank(priceString, 15) + "│");
		System.out.println("\t├────────────────────────────────────────────────────────────────────────────────┤");
		
		for(Map<String, Object> row : rsvMenuList) {
			if(row.get("MENU_NAME").equals("총합")) {
				svRow = row;
				total1 = (String) row.get("MENU_NAME");
				total2 = (int) row.get("CART_QTY");
				total3 = (int) row.get("CART_PRICE");
				break;
				}
			
			menu = (String) row.get("MENU_NAME");
			int qty = (int) row.get("CART_QTY");
			int price = (int) row.get("CART_PRICE");
			System.out.println("\t   " + num + "\t" + PrintBlankUtil.printBlank(menu, 34) + "\t\t" + qty + "\t\t" + price + "     ");
					
			System.out.print("\t│ - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - │\n");
			num++;
			
		}
		System.out.println("\t  " + total1 + "\t\t\t\t\t\t\t" + total2 +" 개"+ "\t\t" + total3 +"원    " );
		System.out.println("\t└────────────────────────────────────────────────────────────────────────────────┘\n\n\n");
		
		

			System.out.println("\t[1.예약 주문 완료하기 2.수량 수정하기 3.메뉴 삭제하기 9.뒤로가기 0.홈으로]");
			input = ScanUtil.nextInt();
		}else {
			System.out.println("\t┌───────────────────────────────────────────────┐");
			System.out.println("             예약 바구니가 비어있습니다.");
			System.out.println("\t└───────────────────────────────────────────────┘\n\n\n");
			
			System.out.println("[1.메뉴 구경가기 0.홈으로]\n->");
			int moveTo = ScanUtil.nextInt();
			
			switch(moveTo) {
			case 1:
				return View.MENU;
			case 0:
				if(userService.LoginMember.get("MEM_ID").equals(null)) {
					return View.ADMIN_HOME;
				}else {
				return View.MEM_HOME;
				}
			}
		}
		
		switch(input) {
		case 1: //[1.예약 주문하기]
			return View.RSV_PURCHASE ;
		case 2: //[2.수정하기]
			return View.RSV_EDIT_PURCHASE;
		case 3: //[3.삭제하기]
			return View.RSV_DEL_PURCHASE;
		case 4: // [9.뒤로가기]
			return View.RSV_HOME;
		case 5: //[0.홈으로]
			if(userService.LoginMember.get("MEM_ID").equals(null)) {
				return View.ADMIN_HOME;
			}else {
			return View.MEM_HOME;
			}
		}
		return View.MEM_HOME;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//[4.예약 바구니] ->[1.예약 주문하기]
	public int rsvBuy() {
		Map<String, Object>svRow = new HashMap<String, Object>();
		for(Map<String, Object>row : rsvMenuList) {
			if(row.get("MENU_NAME").equals("총합")) {
				svRow = row;
				}
			}
		int totalPrice = (int) svRow.get("CART_PRICE");
		String memId = (String) userService.LoginMember.get("MEM_ID");
		int myMile = (int) userService.LoginMember.get("MEM_MILEAGE");
		paidID = memId;
		
		System.out.println("\t예약 주문을 완료 하시겠습니까?\n\t[1.네 2.아니오]");
		int input = ScanUtil.nextInt();
		
		switch(input) {
		
		case 1://주문하기
			int pickup = 0;
			
			Map<String, Object> today = rsvDao.getDate();
			int date = (int)today.get("TODAY");
			boolean flag = true;
			
			do {
			System.out.println("\t수령일을 'MMDD' 형식으로 입력해주세요.\n\t(당일 수령은 불가능합니다.)\n\t->");
			pickup = ScanUtil.nextInt();
				if(pickup > date) {
					flag = false;
				}else {
			System.out.println("\t당일 수령 예약을 불가합니다.\n\t수령일을 다시 입력해주세요.");
			}
			} while(flag);
			
			
			System.out.println("\t기타 전달사항을 작성해주세요.\n\t(예 : 3시에 가지러 갈게요~ / 3개씩 담아주세요 등)");
			String etc = ScanUtil.nextLine();
			if(etc.equals(null)) {
				etc = "내용 없음";
			}
			
			System.out.println("\n\n\n\t결제 하시겠습니까?\n\t[1.네 2.아니오]");
			input = ScanUtil.nextInt();
			if(input == 1) {
				
				Map<String, Object> menuInCart = rsvDao.getCartNo();
				int cartNo = (int)menuInCart.get("CART_NO");
				paidNo = cartNo;
				int result = rsvDao.rsvToBuy(cartNo, memId,totalPrice, pickup, etc);

				for(Map<String, Object>row : rsvMenuList) {
					String menuId = (String)row.get("MENU_ID");
					rsvDao.rsvStateChg(menuId, cartNo);
				}
				
				if(result == 1) {
					System.out.println("\n\n\t결제가 완료되었습니다.");
					rsvMenuList = rsvDao.rsvCart(memId);
					return View.RSV_HOME;
				}
				else {
					System.out.println("\n\n\t앗, 결제 오류 발생!\n\t예약바구니로 이동합니다.");
					return View.RSV_HOME;
				}
			}
			
		case 2://뒤로가기
			return View.RSV_HOME; //[4.예약 바구니]로 이동
		}
		return View.RSV_HOME;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//[4.예약 바구니] ->[2.수량 수정하기]
	public int editPch() {
		String memId = (String) userService.LoginMember.get("MEM_ID");
		System.out.println("\t수량을 변경하려는 메뉴의 번호를 입력해주세요.");
		int idx = ScanUtil.nextInt();
		System.out.println("\t몇 개로 변경하시겠습니까?");
		int cartQty = ScanUtil.nextInt();
		
		Map<String, Object> menuInCart = rsvMenuList.get(idx-1);
		String menuId = (String)menuInCart.get("MENU_ID");
		
		menuInCart = rsvDao.getCartNo();
		int cartNo = (int)menuInCart.get("CART_NO");
		
		System.out.println(rsvMenuList);
		
		int result = rsvDao.editBuy(cartQty, cartNo, menuId);
		if(result == 1) {
			System.out.println("\n\n\n\t수량 변경 성공!");
			rsvMenuList = rsvDao.rsvCart(memId);
		}else {
			System.out.println("\n\n\n\t수량 변경에 실패하였습니다.\n\t예약 바구니로 이동합니다.");
		}
		return View.RSV_HOME;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////


	//[4.예약 바구니] ->[3.메뉴 삭제하기]
	public int delPch() {
		String memId = (String) userService.LoginMember.get("MEM_ID");
		
		System.out.println("\n\n\n\t삭제하려는 메뉴의 번호를 입력해주세요.");
		int idx = ScanUtil.nextInt();
		
		Map<String, Object> menuInCart = rsvMenuList.get(idx-1);
		String menuId = (String)menuInCart.get("MENU_ID");
		
		menuInCart = rsvDao.getCartNo();
		int cartNo = (int)menuInCart.get("CART_NO");
		
		System.out.println("\n\n\n\t삭제하시겠습니까?\n\t[1.네 2.아니오]");
		int agree = ScanUtil.nextInt();
		
		if(agree == 1) {
			int result = rsvDao.delBuy(cartNo, menuId);
			if(result == 1) {
				System.out.println("\n\n\n\t삭제 성공!");
				rsvMenuList = rsvDao.rsvCart(memId);
			}else {
				System.out.println("\n\n\n\t메뉴 삭제에 실패하였습니다.\n\t예약 바구니로 이동합니다.");
			}
		}else {
			System.out.println("\n\n\n\t예약 바구니로 이동합니다.");
		}
		return View.RSV_HOME;
	}

	
/////////////////////////////////////////////////////////////////////////////////
}
