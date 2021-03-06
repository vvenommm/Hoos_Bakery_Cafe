package util;

public interface View {
	//출력 화면
	
	//홈
	int HOME = 1; //로그인 이전(비회원) 홈
	int MEM_HOME = 11;//로그인(회원) 홈
	int ADMIN_HOME = 12; //로그인(관리자) 홈
	
	//은태 & 피피티
	//회원가입, 로그인, 로그아웃
	int JOIN_AGREE = 2; //회원가입 시 개인정보 수집 및 동의 화면
	int JOIN = 21; //회원가입
	int CHECK = 22; // 아이디 유무 확인
	int LOGIN = 23; //로그인 화면
	int LOGOUT = 24; //로그아웃
	
	//은실
	//음식 메뉴
	int MENU = 3; //메뉴 화면
	int MENU_BREAD = 31; //빵 메뉴 목록
	int MENU_COOKIE = 32; //쿠키 메뉴 목록
	int MENU_CAKE = 33; //케이크 메뉴 목록
	int MENU_DRINK = 34; //음료 메뉴 목록
	int MENU_BREAD_DETAIL =35; //선택한 빵 상세 화면
	int MENU_COOKIE_DETAIL =36; //선택한 쿠키 상세 화면
	int MENU_CAKE_DETAIL =37; //선택한 케이크 상세 화면
	int MENU_DRINK_DETAIL =38; //선택한 음료 상세 화면
	
	//정후
	//게시판
	int BOARD_LIST = 4; //게시판 목록
	int BOARD_READ = 41; //특정 글 조회 화면
	int BOARD_READ_NOTICE = 411; //특정 글 조회 화면
	int BOARD_READ_EVENT = 412; //특정 글 조회 화면
	int BOARD_NOTICE = 42; //공지사항 글목록
	int BOARD_EVENT = 43; //이벤트 글목록
	int BOARD_POSTING = 44; //게시판에 글 등록
	
	//은실, 은태
	//마이페이지
	int MYPAGE = 5; //로그인(회원) 후 마이페이지 화면
	int MYPAGE_INFO = 51; //내정보 보기 화면
	int MYPAGE_RESERVATION = 52; //내 예약 주문 현황
	int MYPAGE_REVIEW = 53; //내 한줄평 화면
	
	//정후, 은실
	//예약 및 구매
	int RSV_HOME= 6; //예약하는 화면
	int RSV_PURCHASE = 61; //예약바구니에서 결제하기
	int RSV_EDIT_PURCHASE = 62; //예약바구니에서 결제하기
	int RSV_DEL_PURCHASE = 63; //예약바구니에서 결제하기
	int RSV_RECIEPT = 64; //결제 후 영수증
	
	//정후, 은실, 은태
	//관리자
	//메뉴 관리
	int MANAGEMENT = 7; //로그인(관리자) 후 관리자 페이지
	int MNG_MENU = 71; //메뉴 관리
	int MNG_LIST_MENU = 711;
	int MNG_READ_MENU = 712;
	int MNG_INSERT_MENU = 713;
	int MNG_ACT_MENU = 714;
	int MNG_ACTIVATED = 715;
	int MNG_DEACTIVATED = 716;
	
	//예약 주문 관리
	int MNG_ORDER = 72; //예약 관리
	int MNG_READ_ORDER = 721; //예약 관리
	int MNG_ORDER_APRV = 722; //예약 신청 승인
	int MNG_ORDER_DENY = 723; //예약 신청 승인
	
	//한줄평 관리
	int MNG_REVIEW = 73; //한줄평 관리
	
}
