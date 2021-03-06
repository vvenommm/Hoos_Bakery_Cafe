package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ManagementDao;
import util.ScanUtil;
import util.View;

public class ManagementService {
	
	//싱글톤 패턴
		private ManagementService() {
				
		}
		private static ManagementService instance; 
		public static ManagementService getInstance() { 
			if(instance == null) {
				instance = new ManagementService();
			}
			return instance;
		}
		
		
		/////////////////////////////////////////////////////////////////////////////////
		

		private static ManagementDao mngDao = ManagementDao.getInstance();
		
		
		/////////////////////////////////////////////////////////////////////////////////
		
		
		//MANAGEMENT 관리자 홈
		public int mngList() {
			System.out.println("[1.메뉴 관리 2.예약 관리 3.한줄평 관리 9.뒤로가기 0.로그아웃]");
			int input =ScanUtil.nextInt();
			switch(input) {
			case 1:return View.MNG_MENU; //메뉴 관리
			case 2:return View.MNG_ORDER; //예약주문 관리
			case 3:return View.MNG_REVIEW; //한줄평 관리
//			case 4:return View.MNG_BAKING; //생산량 관리
//			case 5:return View.MNG_SALES; //매출 관리
			case 9:return View.ADMIN_HOME;
			case 0:
				return View.LOGOUT;
			}
			return View.HOME;
		}


		/////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////
		//[1.메뉴관리]
		
		
		//MNG_MENU [1.메뉴 관리] - 활성 상태도 넣기
		public int mngProd() {
			List<Map<String, Object>> list = mngDao.breadList();
			
			System.out.println("-------");
			System.out.println("이름\t가격\t상세설명");
			
			for(int i = 0; i < list.size(); i++) {//빵
				for(Map<String, Object> menu : list) {
					System.out.print(menu.get("MENU_NAME") + "\t");
					System.out.print(menu.get("MENU_PRICE") + "\t");
					System.out.print(menu.get("MENU_DETAIL"));
					System.out.print("\n");
//					System.out.println(menu.get("MENU_ACTIVE"));
				}
			}

			list = mngDao.cookieList();
			for(int i = 0; i < list.size(); i++) {//쿠키
				for(Map<String, Object> menu : list) {
					System.out.print(menu.get("MENU_NAME") + "\t");
					System.out.print(menu.get("MENU_PRICE") + "\t");
					System.out.print(menu.get("MENU_DETAIL"));
					System.out.print("\n");
//					System.out.println(menu.get("MENU_ACTIVE"));
				}
			}
			
			list = mngDao.cakeList();
			for(int i = 0; i < list.size(); i++) {//케이크
				for(Map<String, Object> menu : list) {
					System.out.print(menu.get("MENU_NAME") + "\t");
					System.out.print(menu.get("MENU_PRICE") + "\t");
					System.out.print(menu.get("MENU_DETAIL"));
					System.out.print("\n");
//					System.out.println(menu.get("MENU_ACTIVE"));
				}
			}
			
			list = mngDao.drinkList();
			for(int i = 0; i < list.size(); i++) {//음료
				for(Map<String, Object> menu : list) {
					System.out.print(menu.get("MENU_NAME") + "\t");
					System.out.print(menu.get("MENU_PRICE") + "\t");
					System.out.print(menu.get("MENU_DETAIL"));
					System.out.print("\n");
//					System.out.println(menu.get("MENU_ACTIVE"));
				}
			}
			
			
			System.out.println("[1.메뉴 리스트 보기 2.메뉴 추가 3.메뉴 활성화 9.뒤로가기 0.홈으로]");
			int input =ScanUtil.nextInt();
			switch(input) {
			case 1: //1.메뉴 리스트 보기
				return View.MNG_LIST_MENU;
			case 2: //2.메뉴 추가하기
				return View.MNG_INSERT_MENU; //메뉴 항목 관리
			case 3: //3.메뉴 활성화
				return View.MNG_ACT_MENU; 	//메뉴 항목 활성화
			case 9: //9. 관리 홈
				return View.MANAGEMENT;
			case 0: //0. 관리자 로그인 홈
				return View.ADMIN_HOME;
			}
			return View.ADMIN_HOME;
		}
		
		
		/////////////////////////////////////////////////////////////////////////////////

		
		//MNG_LIST_MENU [1.메뉴 리스트 보기]
		public int listMenu() {
			System.out.println("[조회할 목록의 메뉴 종류를 선택해주세요.\n[1.빵 2.쿠키 3.케이크 4.음료]\n->");
			int input = ScanUtil.nextInt();
			String type = "";
			if(input == 1) {
				type = "a";
				List<Map<String, Object>> menuList =mngDao.listMenu(type);
				
				System.out.println("┌─────────────────────┐");
				System.out.println("번호\t메뉴명\t가격\t상세 설명");
				System.out.println(" ──────────────────────");
				for(Map<String, Object> menu : menuList) {
					System.out.print(menu.get("MENU_ID") + "\t");
					System.out.print(menu.get("MENU_NAME") + "\t");
					System.out.print(menu.get("MENU_PRICE") + "\t");
					System.out.println(menu.get("MENU_DETAIL"));
				}
				System.out.println("└─────────────────────┘");
				
				System.out.println("\n\n[1.조회 9.뒤로가기 0.홈으로]");
				input = ScanUtil.nextInt();
				switch(input) {
				case 1:
					return View.MNG_READ_MENU; //[1.조회]
				case 9:
					return View.MNG_MENU; //[9.뒤로가기]
				case 0:
						return View.ADMIN_HOME; //[0.홈으로]
				}
				return View.ADMIN_HOME;
			}else if(input ==2) {
				type = "b";
				List<Map<String, Object>> menuList =mngDao.listMenu(type);

				System.out.println("┌─────────────────────┐");
				System.out.println("번호\t메뉴명\t가격\t상세 설명");
				System.out.println(" ──────────────────────");
				for(Map<String, Object> menu : menuList) {
					System.out.print(menu.get("MENU_ID") + "\t");
					System.out.print(menu.get("MENU_NAME") + "\t");
					System.out.print(menu.get("MENU_PRICE") + "\t");
					System.out.println(menu.get("MENU_DETAIL"));
				}
				System.out.println("└─────────────────────┘");
				
				System.out.println("\n\n[1.조회 9.뒤로가기 0.홈으로]");
				input = ScanUtil.nextInt();
				switch(input) {
				case 1:
					return View.MNG_READ_MENU; //[1.조회]
				case 9:
					return View.MNG_MENU; //[9.뒤로가기]
				case 0:
						return View.ADMIN_HOME; //[0.홈으로]
				}
				return View.ADMIN_HOME;
			}else if(input == 3) {
				type = "c";
				List<Map<String, Object>> menuList =mngDao.listMenu(type);

				System.out.println("┌─────────────────────┐");
				System.out.println("번호\t메뉴명\t가격\t상세 설명");
				System.out.println(" ──────────────────────");
				for(Map<String, Object> menu : menuList) {
					System.out.print(menu.get("MENU_ID") + "\t");
					System.out.print(menu.get("MENU_NAME") + "\t");
					System.out.print(menu.get("MENU_PRICE") + "\t");
					System.out.println(menu.get("MENU_DETAIL"));
				}
				System.out.println("└─────────────────────┘");
				
				System.out.println("\n\n[1.조회 9.뒤로가기 0.홈으로]");
				input = ScanUtil.nextInt();
				switch(input) {
				case 1:
					return View.MNG_READ_MENU; //[1.조회]
				case 9:
					return View.MNG_MENU; //[9.뒤로가기]
				case 0:
						return View.ADMIN_HOME; //[0.홈으로]
				}
				return View.ADMIN_HOME;
			}else if(input == 4) {
				type = "d";
				List<Map<String, Object>> menuList =mngDao.listMenu(type);

				System.out.println("┌─────────────────────┐");
				System.out.println("번호\t메뉴명\t가격\t상세 설명");
				System.out.println(" ──────────────────────");
				for(Map<String, Object> menu : menuList) {
					System.out.print(menu.get("MENU_ID") + "\t");
					System.out.print(menu.get("MENU_NAME") + "\t");
					System.out.print(menu.get("MENU_PRICE") + "\t");
					System.out.println(menu.get("MENU_DETAIL"));
				}
				System.out.println("└─────────────────────┘");
				
				System.out.println("\n\n[1.조회 9.뒤로가기 0.홈으로]");
				input = ScanUtil.nextInt();
				switch(input) {
				case 1:
					return View.MNG_READ_MENU; //[1.조회]
				case 9:
					return View.MNG_MENU; //[9.뒤로가기]
				case 0:
						return View.ADMIN_HOME; //[0.홈으로]
				}
				return View.ADMIN_HOME;
			}else {
				System.out.println("보기에 없는 선택입니다. 다시 선택해주세요.");
				return listMenu();
			}
			
		}
		
		
		/////////////////////////////////////////////////////////////////////////////////
	
		
		//MNG_READ_MENU [1.조회]
		public int readMenu() {
			System.out.println("조회하려는 메뉴의 번호를 입력해주세요. : ");
			int menuNo = ScanUtil.nextInt();
			Map<String, Object> menu = mngDao.readMenu(menuNo);
			
			System.out.println("┌─────────────────────┐");
			System.out.println("    메뉴코드\t메뉴명\t가격\t활성");
			System.out.println(menu.get("MENU_ID") + "\t" + menu.get("MENU_NAME") + menu.get("MENU_PRICE") + "\t" + menu.get("MENU_ACTIVE"));
			System.out.println("    상세 설명────────────────\n" + menu.get("MENU_DETAIL"));
			System.out.println("└─────────────────────┘");
			
			System.out.println("\n\n\n[1.수정 2.삭제 9.뒤로가기 0.홈으로]");
			int input = ScanUtil.nextInt();
			switch(input) {
				case 1: //[1.수정]
					editMenu(menu);
				case 2: //[2.삭제]
					delMenu(menu);
				case 9: //9.뒤로가기 선택 시 -> 전체 게시판
					return View.MNG_LIST_MENU;
				case 0: //0.관리자 홈으로 선택 시
					return View.ADMIN_HOME;
			}
				return View.ADMIN_HOME;
		}
		
		/////////////////////////////////////////////////////////////////////////////////

		
		//MNG_EDIT_MENU [2.메뉴 수정]
		public int editMenu(Map<String, Object> menu) {
			
			System.out.println("새로운 이름을 입력해주세요.\n->");
			String name = ScanUtil.nextLine();
			System.out.println("새로운 가격을 입력해주세요.\n->");
			int price = ScanUtil.nextInt();
			System.out.println("새로운 설명을 입력해주세요.\n->");
			String detail = ScanUtil.nextLine();
			
			String menuId = (String)menu.get("MENU_ID");
			
			int result = mngDao.editMenu(name, price, detail, menuId);
			
			if(result == 1) {
				System.out.println("수정 성공!");
				return View.MNG_MENU; //성공하면 [1.메뉴 관리]로 이동
			}else {
				System.out.println("메뉴 수정 실패하였습니다.");
				return View.MNG_READ_MENU; //실패하면 [1.메뉴 추가하기]로 이동
			}
		}
		
		
		/////////////////////////////////////////////////////////////////////////////////

		
		//MNG_DEL_MENU [3.메뉴 항목 삭제]
		public int delMenu(Map<String, Object> menu) {
			
			String menuId = (String)menu.get("MENU_ID");
						
			System.out.println("정말 삭제하시겠습니까?\n[1.네 2.아니오]");
			int input = ScanUtil.nextInt();
			if(input == 1) {
				int result = mngDao.delMenu(menuId);
				if(result == 1) {
					System.out.println("삭제 성공!");
					return View.MNG_LIST_MENU;
				}else {
					System.out.println("메뉴 삭제 실패하였습니다.");
					return View.MNG_READ_MENU;
				}
			}else {
				return View.MNG_LIST_MENU;
			}
		}

		
		/////////////////////////////////////////////////////////////////////////////////
		
		
		//MNG_INSERT_MENU [2.메뉴 추가]
		public int insertMenu() {
			System.out.println("[추가할 메뉴의 종류를 선택해주세요.\n[1.빵 2.쿠키 3.케이크 4.음료]\n->");
			int input = ScanUtil.nextInt();
			String type = "";
			if(input == 1) {
				type = "a";
			}else if(input ==2) {
				type = "b";
			}else if(input == 3) {
				type = "c";
			}else if(input == 4) {
				type = "d";
			}else {
				System.out.println("보기에 없는 선택입니다. 다시 선택해주세요.");
				return View.MNG_INSERT_MENU;
			}
			
			System.out.println("추가할 메뉴의 이름을 입력해주세요.\n->");
			String name = ScanUtil.nextLine();
			
			System.out.println("추가할 메뉴의 가격을 입력해주세요.\n->");
			int price = ScanUtil.nextInt();
			
			System.out.println("추가할 메뉴에 대한 설명을 입력해주세요.\n->");
			String detail = ScanUtil.nextLine();
					
			int result = mngDao.insertMenu(type, name, price, detail);
					
			if(result == 1) {
				System.out.println("추가 성공!");
				return View.MNG_MENU; //성공하면 [1.메뉴 관리]로 이동
			}else {
				System.out.println("메뉴 추가 실패하였습니다.");
				return View.MNG_INSERT_MENU; //실패하면 [1.메뉴 추가하기]로 이동
			}
		}


		/////////////////////////////////////////////////////////////////////////////////

		//MNG_ACT_MENU [4.메뉴 항목 활성화]
		public int actMenu() {
			System.out.println("[1.메뉴 부활시키기 2.메뉴 죽이기 9.뒤로가기 0.홈으로]");
			int input = ScanUtil.nextInt();
			
			switch(input) {
				case 1: //[1.활성화 하기]
					return View.MNG_ACTIVATED;
				case 2: //[2.비활성화 하기]
					return View.MNG_DEACTIVATED;
				case 9: //[9.뒤로가기]
					return View.MNG_MENU;
				case 0: //[0.홈으로]
					return View.ADMIN_HOME;
			}
			
			return 0;
		}

		
		/////////////////////////////////////////////////////////////////////////////////

		
		//MNG_ACTIVATED [1.메뉴 부활시키기]
		public int activateMenu() {
			System.out.println("<비활성 메뉴 목록>");
			List<Map<String, Object>> deactedList = mngDao.deactedList();
			
			System.out.println("┌─────────────────────┐");
			System.out.println("메뉴 고유 번호\t메뉴명\t가격\t상태");
			System.out.println(" ──────────────────────");
			for(Map<String, Object> menu : deactedList) {
				System.out.print(menu.get("MENU_ID") + "\t");
				System.out.print(menu.get("MENU_NAME") + "\t");
				System.out.print(menu.get("MENU_PRICE") + "\t");
				System.out.println(menu.get("MENU_ACTIVE"));
			}
			System.out.println("└─────────────────────┘");
			
			System.out.println("[1.활성화 하기 9.뒤로가기 0.홈으로]");
			int input = ScanUtil.nextInt();
			switch(input) {
				case 1: //[1활성화 하기]
					System.out.println("활성화 하려는 메뉴의 고유번호를 입력해주세요.\n->");
					String code = ScanUtil.nextLine();
					
					int result = mngDao.actvating(code);
					
					System.out.println(result);
					
					if(result == 1) {
						System.out.println("활성화 성공!");
					}else {
						System.out.println("활성화에 실패했습니다.");
					}
				case 9: //[9.뒤로가기] -> [4.메뉴 항목 활성화]
					return View.MNG_ACT_MENU;
				case 0: //[0.홈으로]
					return View.ADMIN_HOME;
			}
				return View.ADMIN_HOME;
		}


		/////////////////////////////////////////////////////////////////////////////////

		
		//MNG_DEACTIVATED [2. 메뉴 죽이기]
		public int deactvMenu() {
			System.out.println("<활성 메뉴 목록");
			List<Map<String, Object>> actedList = mngDao.actedList();
			
			System.out.println("┌─────────────────────┐");
			System.out.println("메뉴 고유 번호\t메뉴명\t가격\t상태");
			System.out.println(" ──────────────────────");
			for(Map<String, Object> menu : actedList) {
				System.out.print(menu.get("MENU_ID") + "\t");
				System.out.print(menu.get("MENU_NAME") + "\t");
				System.out.print(menu.get("MENU_PRICE") + "\t");
				System.out.println(menu.get("MENU_ACTIVE"));
			}
			System.out.println("└─────────────────────┘");
			
			System.out.println("[1.비활성화 하기 9.뒤로가기 0.홈으로]");
			int input = ScanUtil.nextInt();
			switch(input) {
				case 1: //[1활성화 하기]
					System.out.println("비활성화 하려는 메뉴의 고유번호를 입력해주세요.\n->");
					String code = ScanUtil.nextLine();
					
					int result = mngDao.deactvating(code);
					
					if(result == 1) {
						System.out.println("비활성화 성공!");
					}else {
						System.out.println("비활성화에 실패했습니다.");
					}
				case 9: //[9.뒤로가기] -> [4.메뉴 항목 활성화]
					return View.MNG_ACT_MENU;
				case 0: //[0.홈으로]
					return View.ADMIN_HOME;
			}
			return View.ADMIN_HOME;
		}


		
		/////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////
		//MNG_ORDER [2.예약 관리]
		
		
		//MNG_ORDER 예약 관리 홈
		public int mngOrder() {
			int num = 0;
			List<Map<String, Object>> list = mngDao.newOrder();
			for(Map<String, Object> order : list) {
				if(order.get("BUY_APRV").equals("신청")) {
					num++;
				}
			}
			if(num > 0) {
			System.out.println("NEW!\n새로운 예약 주문이 " + num + "개 들어왔습니다!!");
			}else {
				System.out.println("새로운 예약이 없습니다.");
			}
//			System.out.println("<예약 목록>");
//			System.out.println("예약일\t예약 번호\t수령일\t비고\t예약자\t상태");
//			System.out.println("--------------------------------");
//			List<Map<String, Object>> list = mngDao.aprvedOrder();
//			for(int i = 0; i < list.size(); i++) {//앞으로 가져갈 주문들
//				for(Map<String, Object> order : list) {
//					System.out.println(i);
//					System.out.print(order.get("RSV_PO") + "\t");
//					System.out.print(order.get("RSV_OD") + "\t");
//					System.out.print(order.get("RSV_PICKUP") + "\t");
//					System.out.print(order.get("RSV_ETC") + "\t");
//					System.out.println(order.get("MEM_ID") + "\t");
//				}
//			}
//			System.out.println("--------------------------------");
			System.out.println("[1.새로운 예약 확인하기 2.거절한 예약 목록 보기 9.뒤로가기 0.홈으로].");
			
			int input = ScanUtil.nextInt();
			switch (input) {
			case 1:
				return View.MNG_ORDER_APRV; //[1.예약 승인하기]
			case 2: //[2.거절한 예약 목록 보기]
				return View.MNG_ORDER_DENY;
			case 9: //[9.뒤로가기]
				return View.MANAGEMENT;
			case 0: //[0.홈으로]
				return View.ADMIN_HOME;
			}
			return View.ADMIN_HOME;
		}

		
		/////////////////////////////////////////////////////////////////////////////////
		
		
		//MNG_ORDER [2.예약 관리] - [1.조회]
		public int readOrder() {
			System.out.println("예약 목록에서 조회하려는 예약 번호를 입력해주세요.\n->");
			int rsvNum = ScanUtil.nextInt();
			Map<String, Object> order = mngDao.readOrder(rsvNum);
			
			System.out.println("┌─────────────────────┐");
			System.out.println("    예약 번호 : " + order.get("BUY_NO"));
			System.out.println("    수령일 : " + order.get("BUY_PICKUP"));
			System.out.println("    주문자 : " + order.get("MEM_ID"));
			System.out.println("    상태 : " + order.get("BUY_APRV"));
			System.out.println(" ──────────────────────");
			if(order.get("BUY_ETC") == null) {
				System.out.println("    비고 : 해당사항 없음");
			}else {
			System.out.println("    비고 : " + order.get("BUY_ETC"));
			}
			System.out.println("└─────────────────────┘");
			
			return View.MNG_ORDER; //예약주문 관리로 이동
		}

	
		/////////////////////////////////////////////////////////////////////////////////

		
		//MNG_ORDER [2.예약 관리] - [2. 승인하기]
	      public int orderAprv() {
	    	 int num = 1;
	         System.out.println("<NEW 예약 목록>");
	         System.out.println("번호\t주문일\t주문 번호\t수령일\t기타\t예약자\t상태");
	         System.out.println("--------------------------------");
	         List<Map<String, Object>> list = mngDao.newOrder();
	            for(Map<String, Object> order : list) {
	            	System.out.print(num + "\t");
	            	System.out.print(order.get("BUY_ORDER") + "\t");
	               System.out.print(order.get("BUY_NO") + "\t");
	               System.out.print(order.get("BUY_PICKUP") + "\t");
	               System.out.print(order.get("BUY_ETC") + "\t");
	               System.out.print(order.get("MEM_ID") + "\t");
	               System.out.println(order.get("BUY_APRV") + "\t");
	               num++;
	            }
	         System.out.println("--------------------------------");
	         System.out.println("[1.승인하기 2. 거절하기 9.뒤로가기 0.홈으로]");
	         int input = ScanUtil.nextInt();
	         
	         switch(input) {
	         case 1:
	               System.out.println("승인할 주문의 번호를 입력하세요.");
	               int orderNum = ScanUtil.nextInt();
	               
	               String buyNo = (String)list.get(orderNum-1).get("BUY_NO");
	               String memId = (String)list.get(orderNum-1).get("MEM_ID");
	               
	               System.out.println("예약 승인하시겠습니까?\n[1.네 2.아니오]");
	               int agree= ScanUtil.nextInt();
	               
	               if(agree == 1) {
	            	   int result = mngDao.chgAprv(buyNo, memId);
	            	   if(result == 1) {
	            		   System.out.println("승인 완료!\n[2.예약 관리] 홈으로 이동합니다.");
	            		   return View.MNG_ORDER;
	            	   }else {
	            		   System.out.println("승인 실패!\n이전 화면으로 이동합니다.");
	            		   return View.MNG_ORDER_APRV;
	            	   }
	                }else {
	                	System.out.println("이전 화면으로 이동합니다.");
	                    return View.MNG_ORDER_APRV;
	                       }
	         case 2:
	        		System.out.println("거절할 주문의 번호를 입력하세요.");
	                orderNum = ScanUtil.nextInt();
	                
	                buyNo = (String)list.get(orderNum-1).get("BUY_NO");
	                memId = (String)list.get(orderNum-1).get("MEM_ID");
	                
	    	   		System.out.println("예약 거절하시겠습니까? [1.네 2.아니오]");
	    	    	agree= ScanUtil.nextInt();
	    	           		
	    	    	if(agree == 1) {
	    	    		int result = mngDao.chgDeny(buyNo, memId);
	    	    		if(result == 1) {
	    	    		System.out.println("거절 완료!\n[2.예약 관리] 홈으로 이동합니다.");
	    	           	return View.MNG_ORDER;
	    	    		}else {
	    	    		System.out.println("승인 실패!\n이전 화면으로 이동합니다.");
	    	    		return View.MNG_ORDER_APRV;
	    	    		}
	    	        }else {
	    	           	System.out.println("이전 화면으로 이동합니다.");
	    	         	return View.MNG_ORDER_APRV;
	    	        }
	         case 9:
	        	 return View.MNG_ORDER;
	         case 0:
	        	 
            }
			return View.MNG_ORDER_APRV;
         }

	      
	      /////////////////////////////////////////////////////////////////////////////////
	      
	      
//	  	public int orderDenied() {
//	  		String cartNo = "";
//	  		List<Map<String, Object>> orderDenied = mngDao.orderDenied();
//	  		for(Map<String, Object> row : orderDenied) {
//	  			System.out.println("┌─────────────────────┐");
//				System.out.println(" 예약 번호\t수령일\t주문자\t상태");
//				System.out.println(" " + row.get("BUY_NO") + "\t" + row.get("BUY_PICKUP") + "\t" + row.get("MEM_ID") + "\t" + row.get("BUY_APRV"));
//				System.out.println(" ──────────────────────");
//				if(row.get("BUY_ETC") == null) {
//					System.out.println("    비고 : 해당사항 없음");
//				}else {
//				System.out.println("    비고 : " + row.get("BUY_ETC"));
//				}
//				System.out.println("└─────────────────────┘");
//	  		}
//	  		
//	  		System.out.println("조회하려는 주문 예약의 번호를 입력해주세요./\n->");
//	  		int input = ScanUtil.nextInt();
//	  		
//	  		
//            
//	   		System.out.println("예약 거절하시겠습니까? [1.네 2.아니오]");
//	    	int agree= ScanUtil.nextInt();
//	           		
//	    	if(agree == 1) {
//	    		mngDao.orderDenied(buyNo, memId);
//	           	return View.MNG_ORDER;
//	        }else {
//	           	System.out.println("이전 화면으로 이동합니다.");
//	         	return View.MNG_ORDER_APRV;
//	        }
//		}
	  	

		/////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////
		//MNG_REVIEW [3.한줄평 관리]
		
		
		//MNG_REVIEW 한줄평 관리
		public int mngReview() {
			List<Map<String, Object>> list = mngDao.reviewList();
			int star = 0;
			int blackStar = 0;
	         for (Map<String, Object> review : list) {
	            System.out.print(review.get("REVIEW_DATE") + "\t");
	            System.out.print(review.get("BUY_NO") + "\t");
	            System.out.print(review.get("MEM_NNAME") + "\t");
	            System.out.print(review.get("MENU_NAME") + "\t");
	            star = (int) review.get("REVIEW_STAR");
	            for (int i = 0; i < star; i++) {
	               System.out.print("★");
	               blackStar = i;
	            }
	               for(int j = 0; j < 4-blackStar; j++) {
	            	   System.out.print("☆");
	            }
//	               System.out.println();
	               System.out.println("\t" + review.get("REVIEW_COMMENT") + "\t");
	         }
	         System.out.println("\n\n[1.한줄평 삭제 9.돌아가기 0.홈으로 ]");
	            int input = ScanUtil.nextInt();
	            switch (input) {
	            case 1:
	               System.out.println("삭제할 한줄평의 번호를 입력하세요.");
	               String reviewNum = ScanUtil.nextLine();
	               for (Map<String, Object> review : list) {
	                  if (reviewNum.contentEquals((String) review.get("BUY_NO"))) {
	                     System.out.println("삭제하시겠습니까? [1.네 2.아니오]");
	                     int agree = ScanUtil.nextInt();
	                     if (agree == 1) {
	                        mngDao.reviewDelete(reviewNum);
	                        System.out.println("삭제 성공!");
	                        return View.MNG_REVIEW;
	                     } else {
	                        System.out.println("이전 화면으로 돌아갑니다.");
	                        
	                     }

	                  }
	               }
	            case 9:
	               return View.MANAGEMENT;
	            
	            case 0:
	               return View.ADMIN_HOME;

	            }

	            return View.ADMIN_HOME;
	         }


		/////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////
		
}
