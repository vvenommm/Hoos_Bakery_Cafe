package service;

import java.util.List;
import java.util.Map;

import dao.MenuDao;
import util.PrintBlankUtil;
import util.ScanUtil;
import util.View;

public class MenuService {
	
	private MenuService() {
		
	}
	private static MenuService instance;
	public static MenuService getInstance() {
		if(instance == null) {
			instance = new MenuService();
		}
		return instance;
	}
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
	
	
	private static MenuDao menuDao = MenuDao.getInstance();
	private static UserService userService = UserService.getInstance();
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//메뉴 홈
	public int menu() {
		System.out.println("\t┌─────────────────────┐");
		System.out.println("\t│ 1.빵                │\n\n\t│ 2.쿠키류            │\n\n\t│ 3.케이크            │\n\n\t│ 4.음료              │\n\n\t│ 0.뒤로가기          │\n\t└─────────────────────┘");
		System.out.print("\t->");
		int input = ScanUtil.nextInt();
		switch(input) {
		case 1:
			return View.MENU_BREAD;
		case 2:
			return View.MENU_COOKIE;
		case 3:
			return View.MENU_CAKE;
		case 4:
			return View.MENU_DRINK;
		case 0:
			if(userService.LoginMember.size() > 0) {
				if (userService.LoginMember.get("MEM_ID").equals("admin")){ //관리자 홈
					return View.ADMIN_HOME;
				}else{ //회원 홈
					return View.MEM_HOME;
				}}else { //비회원 홈
					return View.HOME;
				}
		}
	return View.MENU;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//빵 목록
	public int breadList() {
		List<Map<String, Object>> bread = menuDao.breadList();
		
//		String num = " 글번호";
//		String title = "제목";
//		String writer = "작성자";
//		String dated = "작성일";
//		String content = "내용";
//		
//		System.out.println("\t┌────────────────────────────────────────────────────────────────────────────────┐");
//		System.out.println("\t│" + PrintBlankUtil.printBlank(num, 15) + PrintBlankUtil.printBlank(title, 45) + PrintBlankUtil.printBlank(writer, 10) + PrintBlankUtil.printBlank(writer, 10) + "│");
//		System.out.println("\t├────────────────────────────────────────────────────────────────────────────────┤");
//		
//		for(Map<String, Object> board : noticeList) {
//			int number = (int)board.get("BOARD_NO");
//			title = (String) board.get("BOARD_TITLE");
//			writer = (String) board.get("MEM_NNAME");
//			dated = (String) board.get("BOARD_DATE");
//			System.out.println("\t│  " + number + "\t" + PrintBlankUtil.printBlank(title, 49) + PrintBlankUtil.printBlank(writer, 12) + PrintBlankUtil.printBlank(dated, 12) + "│");
//					
//			System.out.print("\t│ - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - │\n");
//		}
//		System.out.println("\t└────────────────────────────────────────────────────────────────────────────────┘");
		
		
		
		System.out.println("\n\n\n==================================");
		for(Map<String, Object> b: bread) {
			System.out.println(b.get("TO_NUMBER(SUBSTR(MENU_ID,2))") + "." + b.get("MENU_NAME") + "(" + b.get("MENU_PRICE") + "원)");
		}
		System.out.println("==================================\n\n\n");
		return View.MENU_BREAD_DETAIL;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//빵 상세화면
	public int breadDetail() {
		System.out.print("메뉴 번호를 입력해주세요.\n->");
		int menuNum = ScanUtil.nextInt();
		Map<String, Object> breadDetail = menuDao.breadDetail(menuNum);
		List<Map<String, Object>> breadReview = menuDao.breadReview(menuNum);
		System.out.println("\n" + breadDetail.get("MENU_DETAIL") + "\n\n");
		
		int star = 0;
		int blackStar = 0;
		
		for(Map<String, Object> br: breadReview) {
			star = (int) br.get("REVIEW_STAR");
			System.out.print(br.get("MEM_NNAME") + "\t"); 
			for (int i = 0; i < star; i++) {
                System.out.print("★");
                blackStar = i;
             }
                for(int j = 0; j < 4-blackStar; j++) {
                   System.out.print("☆");
             }
             
			System.out.println("["+br.get("REVIEW_COMMENT")+"]");
			
		}
		
		System.out.println("\n\n\n[1.구매하기 9.뒤로가기 0.홈으로]");
		int input = ScanUtil.nextInt();
		
		switch(input) {
		case 1:
	         if(userService.LoginMember.size() > 0) {
			 if(!userService.LoginMember.get("MEM_ID").equals(null)) {
				 Map<String, Object> getCartNo = menuDao.getCartNo();
				 int cartNo = (int)getCartNo.get("CART_NO")+1;
				 
				 String menuId = (String)breadDetail.get("MENU_ID");
				 
				 System.out.println("수량을 입력해주세요.");
				 int qty = ScanUtil.nextInt();
				 String memId = (String)userService.LoginMember.get("MEM_ID");
				 int result = menuDao.intoCart(cartNo, menuId, qty, memId);
				 if(result == 1) {
					 System.out.println("예약 바구니에 담겼어요!\n[1.다시 메뉴 고르기 2.예약 바구니로 이동하기 3.홈으로]");
					 input = ScanUtil.nextInt();
					 
					 switch(input) {
					 case 1:
						 return View.MENU;
					 case 2:
						 return View.RSV_HOME;
					 case 3:
						 return View.MEM_HOME;
					 }
					 }else if(result == 0) {
						 System.out.println("다시 시도해주세요.");
						 breadDetail();
					 }
			 }}else {
				 System.out.println("로그인 후 이용 가능합니다.");
				 System.out.println("[1.로그인하기 2.회원가입하기 0.홈으로]");
				 input = ScanUtil.nextInt();
				 switch(input) {
				 case 1:
					 return View.LOGIN;
				 case 2:
					 return View.JOIN_AGREE;
				 case 0:
					 return View.HOME;
				 }
			 }
		case 9:
			 return View.MENU_BREAD;
		case 0:
			try {
				if (userService.LoginMember.get("MEM_ID").equals("admin")){ //관리자 홈
					return View.ADMIN_HOME;
				}else if(!userService.LoginMember.get("MEM_ID").equals(null)){ //회원 홈
					return View.MEM_HOME;
				}else { //비회원 홈
					return View.HOME;
				}
			} catch(NullPointerException e) {
			}
		}
		return View.HOME;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
		
	
	//쿠키 목록
	public int cookieList() {
		List<Map<String, Object>> cookie = menuDao.cookieList();
		System.out.println("==================================");
		for(Map<String, Object> c: cookie) {
			System.out.println(c.get("TO_NUMBER(SUBSTR(MENU_ID,2))") + "." + c.get("MENU_NAME") +"(" + c.get("MENU_PRICE") + "원)" );
		}
		System.out.println("==================================");
		return View.MENU_COOKIE_DETAIL;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//쿠키 상세화면
	public int cookieDetail() {
		System.out.print("메뉴 번호를 입력해주세요.\n->");
		int menuNum = ScanUtil.nextInt();
		Map<String, Object> cookieDetail = menuDao.cookieDetail(menuNum);
		List<Map<String, Object>> cookieReview = menuDao.cookieReview(menuNum);
		System.out.println("\n" + cookieDetail.get("MENU_DETAIL") + "\n\n");


		int star = 0;
		int blackStar = 0;

		for (Map<String, Object> cr : cookieReview) {
			System.out.print(cr.get("MEM_NNAME") + "\t"); 
			star = (int) cr.get("REVIEW_STAR");
			for (int i = 0; i < star; i++) {
				System.out.print("★");
				blackStar = i;
			}
			for (int j = 0; j < 4 - blackStar; j++) {
				System.out.print("☆");
			}

			System.out.println("[" + cr.get("REVIEW_COMMENT") + "]");

		}
		
		System.out.println("\n\n\n[1.구매하기 9.뒤로가기 0.홈으로]");
		int input = ScanUtil.nextInt();
		
		switch(input) {
		case 1:
			 if(userService.LoginMember.size() > 0) {
			 if(!userService.LoginMember.get("MEM_ID").equals(null)) {
				 Map<String, Object> getCartNo = menuDao.getCartNo();
				 int cartNo = (int)getCartNo.get("CART_NO")+1;
				 
				 String menuId = (String)cookieDetail.get("MENU_ID");
				 System.out.println("수량을 입력해주세요.");
				 int qty = ScanUtil.nextInt();
				 String memId = (String)userService.LoginMember.get("MEM_ID");
				 int result = menuDao.intoCart(cartNo, menuId, qty, memId);
				 if(result == 1) {
					 System.out.println("예약 바구니에 담겼어요!\n[1.다시 메뉴 고르기 2.예약 바구니로 이동하기 3.홈으로]");
					 input = ScanUtil.nextInt();
					 
					 switch(input) {
					 case 1:
						 return View.MENU;
					 case 2:
						 return View.RSV_HOME;
					 case 3:
						 return View.MEM_HOME;
					 }
					 }else if(result == 0) {
						 System.out.println("다시 시도해주세요.");
						 breadDetail();
					 }
			 }}else {
				 System.out.println("로그인 후 이용 가능합니다.");
				 System.out.println("[1.로그인하기 2.회원가입하기 0.홈으로]");
				 input = ScanUtil.nextInt();
				 switch(input) {
				 case 1:
					 return View.LOGIN;
				 case 2:
					 return View.JOIN_AGREE;
				 case 0:
					 return View.HOME;
				 }
			 }
		case 9:
			 return View.MENU_COOKIE;
		case 0:
			 if(userService.LoginMember.size() > 0) {
				if (userService.LoginMember.get("MEM_ID").equals("admin")){ //관리자 홈
					return View.ADMIN_HOME;
				}else if(!userService.LoginMember.get("MEM_ID").equals(null)){ //회원 홈
					return View.MEM_HOME;
				}}else { //비회원 홈
					return View.HOME;
				}
		}
		return View.MENU_COOKIE;
	}
	

/////////////////////////////////////////////////////////////////////////////////
	
	
	//케이크 목록
	public int cakeList() {
		List<Map<String, Object>> cake = menuDao.cakeList();
		
		System.out.println("==================================");
		for(Map<String, Object> c: cake) {
			System.out.println(c.get("TO_NUMBER(SUBSTR(MENU_ID,2))") + "." +c.get("MENU_NAME") +"(" + c.get("MENU_PRICE") + "원)");
		}
		System.out.println("==================================");
		
		return View.MENU_CAKE_DETAIL;
	}

	
/////////////////////////////////////////////////////////////////////////////////

	
	//케이크 상세화면
	public int cakeDetail() {
		System.out.print("메뉴 번호를 입력해주세요.\n->");
		int menuNum = ScanUtil.nextInt();
		Map<String, Object> cakeDetail = menuDao.cakeDetail(menuNum);
		List<Map<String, Object>> cakeReview = menuDao.cakeReview(menuNum);
		System.out.print("\n" + cakeDetail.get("MENU_DETAIL") + "\n\n");
		System.out.println();
		
		int star = 0;
		int blackStar = 0;
		
		for(Map<String, Object> cr: cakeReview) {
			System.out.print(cr.get("MEM_NNAME") + "\t"); 
			star = (int) cr.get("REVIEW_STAR");
			for (int i = 0; i < star; i++) {
                System.out.print("★");
                blackStar = i;
             }
                for(int j = 0; j < 4-blackStar; j++) {
                   System.out.print("☆");
             }
             
			System.out.println("["+cr.get("REVIEW_COMMENT")+"]");
			
		}
		
		System.out.println("\n\n\n[1.구매하기 9.뒤로가기 0.홈으로]");
		int input = ScanUtil.nextInt();
		
		switch(input) {
		case 1:
			 if(userService.LoginMember.size() > 0) {
			 if(!userService.LoginMember.get("MEM_ID").equals(null)) {
				 Map<String, Object> getCartNo = menuDao.getCartNo();
				 int cartNo = (int)getCartNo.get("CART_NO")+1;
				 
				 String menuId = (String)cakeDetail.get("MENU_ID");
				 System.out.println("수량을 입력해주세요.");
				 int qty = ScanUtil.nextInt();
				 String memId = (String)userService.LoginMember.get("MEM_ID");
				 int result = menuDao.intoCart(cartNo, menuId, qty, memId);
				 if(result == 1) {
					 System.out.println("예약 바구니에 담겼어요!\n[1.다시 메뉴 고르기 2.예약 바구니로 이동하기 3.홈으로]");
					 input = ScanUtil.nextInt();
					 
					 switch(input) {
					 case 1:
						 return View.MENU;
					 case 2:
						 return View.RSV_HOME;
					 case 3:
						 return View.MEM_HOME;
					 }
					 }else if(result == 0) {
						 System.out.println("다시 시도해주세요.");
						 breadDetail();
					 }
			 }}else {
				 System.out.println("로그인 후 이용 가능합니다.");
				 System.out.println("[1.로그인하기 2.회원가입하기 0.홈으로]");
				 input = ScanUtil.nextInt();
				 switch(input) {
				 case 1:
					 return View.LOGIN;
				 case 2:
					 return View.JOIN_AGREE;
				 case 0:
					 return View.HOME;
				 }
			 }
		case 9:
			 return View.MENU_CAKE;
		case 0:
			 if(userService.LoginMember.size() > 0) {
				if (userService.LoginMember.get("MEM_ID").equals("admin")){ //관리자 홈
					return View.ADMIN_HOME;
				}else if(!userService.LoginMember.get("MEM_ID").equals(null)){ //회원 홈
					return View.MEM_HOME;
				}}else { //비회원 홈
					return View.HOME;
				}
		}
		return View.MENU_CAKE;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//음료 목록
	public int drinkList() {
		List<Map<String, Object>> drink = menuDao.drinkList();
		System.out.println("==================================");
		for(Map<String, Object> d: drink) {
			System.out.println(d.get("TO_NUMBER(SUBSTR(MENU_ID,2))") + "." + d.get("MENU_NAME") +"(" + d.get("MENU_PRICE") + "원)");
		}
		System.out.println("==================================");
		return View.MENU_DRINK_DETAIL;
	}

	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//음료 상세화면
	public int drinkDetail() {
		System.out.print("메뉴 번호를 입력해주세요.\n->");
		int menuNum = ScanUtil.nextInt();
		Map<String, Object> drinkDetail = menuDao.drinkDetail(menuNum);
		List<Map<String, Object>> drinkReview = menuDao.drinkReview(menuNum);
		System.out.print("\n" + drinkDetail.get("MENU_DETAIL") + "\n\n");
		System.out.println();
		int star = 0;
		int blackStar = 0;
		
		for(Map<String, Object> dr: drinkReview) {
			System.out.print(dr.get("MEM_NNAME") + "\t"); 
			star = (int) dr.get("REVIEW_STAR");
			for (int i = 0; i < star; i++) {
                System.out.print("★");
                blackStar = i;
             }
                for(int j = 0; j < 4-blackStar; j++) {
                   System.out.print("☆");
             }
             
			System.out.println("["+dr.get("REVIEW_COMMENT")+"]");
			
		}
		
		System.out.println("\n\n\n[1.구매하기 9.뒤로가기 0.홈으로]");
		int input = ScanUtil.nextInt();
		
		switch(input) {
		case 1:
			 if(userService.LoginMember.size() > 0) {
			 if(!userService.LoginMember.get("MEM_ID").equals(null)) {
				 Map<String, Object> getCartNo = menuDao.getCartNo();
				 int cartNo = (int)getCartNo.get("CART_NO")+1;
				 
				 String menuId = (String)drinkDetail.get("MENU_ID");
				 System.out.println("수량을 입력해주세요.");
				 int qty = ScanUtil.nextInt();
				 String memId = (String)userService.LoginMember.get("MEM_ID");
				 int result = menuDao.intoCart(cartNo, menuId, qty, memId);
				 if(result == 1) {
					 System.out.println("예약 바구니에 담겼어요!\n[1.다시 메뉴 고르기 2.예약 바구니로 이동하기 0.홈으로]");
					 input = ScanUtil.nextInt();
					 
					 switch(input) {
					 case 1:
						 return View.MENU;
					 case 2:
						 return View.RSV_HOME;
					 case 0:
						 return View.MEM_HOME;
					 }
					 }else if(result == 0) {
						 System.out.println("다시 시도해주세요.");
						 breadDetail();
					 }
			 }}else {
				 System.out.println("로그인 후 이용 가능합니다.");
				 System.out.println("[1.로그인하기 2.회원가입하기 0.홈으로]");
				 input = ScanUtil.nextInt();
				 switch(input) {
				 case 1:
					 return View.LOGIN;
				 case 2:
					 return View.JOIN_AGREE;
				 case 0:
					 return View.HOME;
				 }
			 }
		case 9:
			 return View.MENU_DRINK;
		case 0:
			 if(userService.LoginMember.size() > 0) {
				if (userService.LoginMember.get("MEM_ID").equals("admin")){ //관리자 홈
					return View.ADMIN_HOME;
				}else if(!userService.LoginMember.get("MEM_ID").equals(null)){ //회원 홈
					return View.MEM_HOME;
				}}else { //비회원 홈
					return View.HOME;
				}
		}
		return View.MENU_DRINK;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
}

