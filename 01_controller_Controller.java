package controller;

import service.BoardService;
import service.ManagementService;
import service.MenuService;
import service.MypageService;
import service.RSVService;
import service.UserService;
import util.ScanUtil;
import util.View;

public class Controller {

	public static void main(String[] args) {

		new Controller().start();
		
	}

/////////////////////////////////////////////////////////////////////////////////
	
	//전역변수로 불러오기
	private static UserService userService = UserService.getInstance();
	private static MenuService menuService = MenuService.getInstance();
	private static BoardService boardService = BoardService.getInstance();
	private static ManagementService mngService = ManagementService.getInstance();
	private static RSVService rsvService = RSVService.getInstance();
	private static MypageService mpService = MypageService.getInstance();
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	private void start() {
		int view = View.HOME;
		
		while(true) {
			System.out.println("\n\n\n\n\n\n\t\t\t∞ 무 한 루 프 ∞ 베이커리 카페\n\n\n\n");
			switch(view) {
			case View.HOME: view = home(); break; //로그인 이전(비회원) 홈
			case View.MEM_HOME: view = memHome(); break; //로그인(회원) 홈
			case View.ADMIN_HOME: view = adminHome(); break; //로그인(관리자) 홈
			
/////////////////////////////////////////////////////////////////////////////////
			
			case View.JOIN_AGREE: view = userService.policy(); break;
			case View.JOIN: view =userService. join(); break;
			case View.CHECK: view = userService.ask(); break; //  아이디 유무 확인
			case View.LOGIN : view = userService.login(); break;
			case View.LOGOUT : view = userService.logout(); break;
			
/////////////////////////////////////////////////////////////////////////////////

			case View.MYPAGE: view = mpService.myPage(); break;
			case View.MYPAGE_INFO: view = mpService.myPageInfo(); break;
			case View.MYPAGE_RESERVATION: view = mpService.myReserv() ; break; //예약 현황
			case View.MYPAGE_REVIEW: view = mpService.myReview(); break;
			
/////////////////////////////////////////////////////////////////////////////////
		
			case View.MENU: view = menuService.menu(); break;
			case View.MENU_BREAD: view = menuService.breadList(); break;
			case View.MENU_COOKIE: view = menuService.cookieList(); break;
			case View.MENU_CAKE: view = menuService.cakeList(); break;
			case View.MENU_DRINK: view = menuService.drinkList(); break;
			
/////////////////////////////////////////////////////////////////////////////////
			
			case View.MENU_BREAD_DETAIL: view = menuService.breadDetail(); break;
			case View.MENU_COOKIE_DETAIL: view = menuService.cookieDetail(); break;
			case View.MENU_CAKE_DETAIL: view = menuService.cakeDetail(); break;
			case View.MENU_DRINK_DETAIL: view = menuService.drinkDetail(); break;
			
/////////////////////////////////////////////////////////////////////////////////
		
			case View.BOARD_LIST: view = boardService.boardList(); break; //게시판 홈
			case View.BOARD_NOTICE: view = boardService.noticeList(); break; //공지 모음
			case View.BOARD_EVENT: view = boardService.eventList(); break; //이벤트 모음
			
/////////////////////////////////////////////////////////////////////////////////
		
			case View.BOARD_READ: view = boardService.readPost(); break; //글 읽기
			case View.BOARD_READ_NOTICE: view = boardService.readPostNotice(); break; //글 읽기
			case View.BOARD_READ_EVENT: view = boardService.readPostEvent(); break; //글 읽기
			case View.BOARD_POSTING: view = boardService.posting(); break; //글 등록
			
/////////////////////////////////////////////////////////////////////////////////
			
			case View.RSV_HOME: view = rsvService.rsvCart(); break; //예약 바구니
			case View.RSV_PURCHASE: view = rsvService.rsvBuy();  break; //결제
			case View.RSV_EDIT_PURCHASE: view = rsvService.editPch();  break; //예약 바구니 항목 수량 수정
			case View.RSV_DEL_PURCHASE: view = rsvService.delPch();  break; //예약 바구니 항목 삭제
			
/////////////////////////////////////////////////////////////////////////////////
		
			case View.MANAGEMENT: view = mngService.mngList(); break; //관리 페이지 홈
			case View.MNG_MENU: view = mngService.mngProd(); break; //메뉴 관리
			case View.MNG_LIST_MENU: view = mngService.listMenu(); break; //메뉴 항목 추가
			case View.MNG_READ_MENU: view = mngService.readMenu(); break; //메뉴 항목 추가
			case View.MNG_INSERT_MENU: view = mngService.insertMenu(); break; //메뉴 항목 추가
			case View.MNG_ACT_MENU: view = mngService.actMenu(); break; //메뉴 항목 활성화
			case View.MNG_ACTIVATED: view = mngService.activateMenu(); break; //메뉴 항목 활성화
			case View.MNG_DEACTIVATED: view = mngService.deactvMenu(); break; //메뉴 항목 활성화
			
/////////////////////////////////////////////////////////////////////////////////
		
			case View.MNG_ORDER: view = mngService.mngOrder(); break; //예약주문 관리
			case View.MNG_READ_ORDER: view = mngService.readOrder(); break; //예약주문 관리
			case View.MNG_ORDER_APRV: view = mngService.orderAprv(); break; //예약 승인 관리
			
/////////////////////////////////////////////////////////////////////////////////
		
			case View.MNG_REVIEW: view = mngService.mngReview(); break; //한줄평 관리
			
/////////////////////////////////////////////////////////////////////////////////
		
			}
		}
		
	}

/////////////////////////////////////////////////////////////////////////////////

	//관리자용 홈
	private int adminHome() {
		System.out.println("\n\n\n\t[1.마이페이지 2.메뉴 3.게시판 4.예약 바구니 5.관리자 페이지 9.로그아웃 0.프로그램 종료]\n\n\n");
		int input =ScanUtil.nextInt();
		switch(input) {
		case 1:return View.MYPAGE;
		case 2:return View.MENU;
		case 3:return View.BOARD_LIST;
		case 4:return View.RSV_HOME;
		case 5:return View.MANAGEMENT;
		case 9:return View.LOGOUT;
		case 0:
			System.out.println("\t프로그램을 종료합니다.");
			System.exit(0);
		default :
			System.out.println("\t없는 항목입니다.\n\t다시 입력해주세요.");
			return View.ADMIN_HOME;
		}
	}

	//회원용 홈
	private int memHome() {
		System.out.println("\n\n\n\t[1.마이페이지 2.메뉴 3.게시판 4.예약 바구니 9.로그아웃 0.프로그램 종료]\n\n\n");
		int input =ScanUtil.nextInt();
		switch(input) {
		case 1:return View.MYPAGE;
		case 2:return View.MENU;
		case 3:return View.BOARD_LIST;
		case 4:return View.RSV_HOME;
		case 9:return View.LOGOUT;
		case 0:
			System.out.println("\t프로그램을 종료합니다.");
			System.exit(0);
		default :
			System.out.println("\t없는 항목입니다.\n다시 입력해주세요.");
			return View.MEM_HOME;
		}
	}

	//비회원용 홈
	private int home() {
		System.out.println("\n\n\n\t[1.로그인 2.메뉴 3.게시판 4.예약 바구니 9.회원가입 0.프로그램 종료]\n\n\n");
		int input =ScanUtil.nextInt();
		switch(input) {
		case 1:return View.CHECK;
		case 2:return View.MENU;
		case 3:return View.BOARD_LIST;
		case 4:return View.CHECK;
		case 9:return View.JOIN_AGREE;
		case 0:
			System.out.println("\t프로그램을 종료합니다.");
			System.exit(0);
		default :
			System.out.println("\t없는 항목입니다.\n\t다시 입력해주세요.");
			return View.HOME;
		}
	}

}
