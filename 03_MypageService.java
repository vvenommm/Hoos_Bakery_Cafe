package service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import dao.MypageDao;
import util.ScanUtil;
import util.View;

public class MypageService {
	
private MypageService() {
		
	}
	private static MypageService instance;
	public static MypageService getInstance() { 
		if(instance == null) {
			instance = new MypageService();
		}  
		return instance;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////

	private static UserService userService = UserService.getInstance();	
	private static MypageDao mypageDao = MypageDao.getInstance();	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//[1.마이페이지] 홈
	public int myPage() {
		System.out.println("[1.내정보 보기 2.내 예약 현황 3.내가 쓴 한줄평 0.홈으로]");
		System.out.print("선택하기: ");
		int input = ScanUtil.nextInt();
		switch(input) {
			case 1: 
				return View.MYPAGE_INFO; //1. 내 정보 보기
			case 2:
				return View.MYPAGE_RESERVATION; //2. 내 예약 현황
			case 3:
				return View.MYPAGE_REVIEW; //3. 내가 쓴 한줄평
			case 0:
				if(userService.LoginMember.size() > 0) {
					if (userService.LoginMember.get("MEM_ID").equals("admin")) { // 관리자 홈
						return View.ADMIN_HOME;
					} else { // 회원 홈
						return View.MEM_HOME;
					}} else { // 비회원 홈
						return View.HOME;
				}
		}
		return View.HOME;
	}

	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//[1.마이페이지] -> [1.내정보 보기]
	public int myPageInfo() {
	      System.out.print("비밀번호를 다시 입력해주세요.\n비밀번호 : ");
	      String password = ScanUtil.nextLine();
	      
	      if (userService.LoginMember.get("MEM_PW").equals(password)) {
	         System.out.println("===================");
	         System.out.println("ID: " + userService.LoginMember.get("MEM_ID"));
	         System.out.println("이름: " + userService.LoginMember.get("MEM_NAME"));
	         System.out.println("닉네임: " + userService.LoginMember.get("MEM_NNAME"));
	         System.out.println("연락처: " + userService.LoginMember.get("MEM_NUMBER"));
	         System.out.println("===================");
	         System.out.println(" [1.연락처 수정 2. 비밀번호 수정  0.뒤로가기] ");
	         System.out.print("선택하기: ");
	         int num = ScanUtil.nextInt();
	         switch (num) {
	         case 1:
	            while(true) {
	            Object memNumber = userService.LoginMember.get("MEM_NUMBER");
	            System.out.println("연락처는 - 없이 작성해 주세요.");
	            System.out.print("새로운 연락처를 입력해주세요: ");
	            String changeNumber = ScanUtil.nextLine();
	            String regex = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})(\\d{4})$"; // 핸드폰 번호만 받을 수 있게 설정 010, 016~019 앞자리 만 가능하고
	                                                         // 중간번호는 3~4자리 끝번호는 4자리만 가능
	            boolean check = Pattern.matches(regex, changeNumber); // 정규식을 만족하였을 경우 true값을 받음
	            Map<String, Object> Number = mypageDao.bringNumber(changeNumber);
	            String cp = null; // 중복된 전화번호를 String으로 저장할 변수
	            String phsv = null;
	            try {
	               cp = (String) Number.get("MEM_NUMBER"); // map을 string으로 변환 하여 저장
	            } catch (NullPointerException e) { // map에는 null값이 있으면 오류가 뜨기에 예외처리
	            }
	            if (cp == null && check) { // 첫번째로 등록한 회원은 비교할 번호가 없기에 정규식만 통과하면 저장하게 만듦
	               phsv = changeNumber;
	               int chnm = mypageDao.changeNumber(changeNumber);
	               System.out.println("연락처가 변경되었습니다.");
	               System.out.println("변경된 연락처는 재 로그인 후 반영됩니다.");
	                
	               break;
	            } else { // 첫번째 다음으로 등록한 회원들은 번호를 비교
	               boolean check2 = changeNumber.equals(cp); // 중복되는 번호가 있으면 값이 true로 바뀜
	               if (check && check2 == false) { // 중복되지 않고 정규식을 통과하게 된다면 번호를 저장
	                  phsv = changeNumber;
	                  int chnm = mypageDao.changeNumber(changeNumber);
	                  break;
	               } else { // 중복되거나 정규식을 만족하지 못하면 출력
	                  System.out.println("중복되거나 형식이 잘못된 전화번호 입니다.");
	               }
	            }
	            }
	            break;
	         case 2 : 
	            while(true) {
	            Object memPassWord = userService.LoginMember.get("MEM_PW"); // 비밀번호 받기
	            System.out.print("새로운 비밀번호를 입력해주세요 : "); //비밀번호 입력
	            String changePassWord = ScanUtil.nextLine();
	            String regex2 = "^[A-Za-z[0-9]]{8,15}$"; // 유효성 검사 영어와숫자로만 8~15자만 가능
	            boolean check2 = Pattern.matches(regex2, changePassWord); // 유효성 검사에 성공하면 true값을 가짐 나머지는 false
	            String pwsv = null;
	            if (check2) { //정규식을 통과하면 비밀번호를 저장
	               pwsv = changePassWord; 
	               int chpw = mypageDao.changePassWord(changePassWord); // 변경하는 메서드
	               System.out.println("비밀번호 변경에 성공했습니다.");
	               System.out.println("변경된 비밀번호는 재 로그인 후 반영됩니다.");
	                
	               break;
	            } else { // 정규식 통과를 못하면 다시 입력받게 함.
	               System.out.println("비밀번호 형식이 안맞습니다."); //비밀번호는 곂쳐도 상관이 없기에 중복확인은 할 필요 X
	            }
	            }
	            break;
	      case 0:
	         return View.MYPAGE;
	      }
	      }else {
	         System.out.println("비밀번호를 다시 입력하세요.");
	         
	      }
	      return View.MEM_HOME;
	   }
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	//[1.마이페이지] -> [2.예약 현황]
	   public int myReserv() {
	        System.out.println("\t      <예약 현황>");
	        System.out.println();
	        System.out.println("번호\t예약날짜\t예약번호\t수령일\t결제 금액\t상태");
	        System.out.println("---------------------------------------------------------");
	        List<Map<String, Object>> list = mypageDao.myReserv();
	        int idx = 0;
	           for (Map<String, Object> info : list) {
	        	   idx++;
	             System.out.print(idx + "\t");
	              System.out.print(info.get("BUY_DATE") + "\t");
	              System.out.print(info.get("BUY_NO") + "\t");
	              System.out.print(info.get("BUY_PICKUP") + "\t");
	              System.out.print(info.get("BUY_TP") + "\t");
	              System.out.println(info.get("BUY_APRV") + "\t");
	           }
	           System.out.println("---------------------------------------------------------");
	           System.out.println("[1.조회 0.뒤로가기]");
	           int result = ScanUtil.nextInt();
	           switch (result) {
	           case 1 :
	        	   System.out.print("조회할 번호를 선택해 주세요. : ");
	                 int input = ScanUtil.nextInt();
	                 Map<String, Object>map = list.get(input -1);
	                 int cartNo = (int)map.get("CART_NO");
	                 
	                 System.out.println("\t      <상세 페이지>");
	                 System.out.println("예약날짜\t주문번호\t수령일\t상태\t비고");
	                 System.out.println("------------------------------------------");
	                 System.out.print(map.get("BUY_DATE") + "\t");
	                 System.out.print(map.get("BUY_NO") + "\t");
	                 System.out.print(map.get("BUY_PICKUP") + "\t");
	                 System.out.print(map.get("BUY_APRV") + "\t");
	                 System.out.println(map.get("BUY_ETC") + "\t");
	                 System.out.println("------------------------------------------");
	                 
//	                 for(Map<String, Object> row : mypageDao.myReservDetail(cartNo)) {
//	                 System.out.println("메뉴명\t수량\t");
//	                 System.out.print(map.get("MENU_NAME") + "\t\t" + map.get("CART_PRICE") + "\t"+ map.get("CART_QTY") + "개");
//	                 if(map.get("MENU_NAME").equals("총합")) {
//	                	 System.out.println("------------------------------------------");
//	                	 System.out.print(map.get("MENU_NAME") + "\t\t" + map.get("CART_PRICE") + "\t"+ map.get("CART_QTY") + "개");
//	                 }
//	                 }
	                 System.out.println("예약을 취소하시겠습니까? ");
	                 System.out.print("[1.네 2.아니오]");
	                     int sel = ScanUtil.nextInt();
	                     if(sel == 1) {
	                        mypageDao.cancleReserv();
	                        System.out.println("취소 하였습니다.");
	                        return View.MYPAGE;
	                     }else if(sel ==2) {
	                        System.out.println("예약 메뉴로 돌아갑니다.");
	                        return View.MYPAGE_RESERVATION;
	                     }else {
	                        System.out.println("잘못입력했습니다. 다시입력해 주세요.");
	                        return View.MYPAGE_RESERVATION;
	                     }
	                case 0 : 
	                   return View.MYPAGE;
	                default : 
	                   System.out.println("잘못입력하였습니다. 다시입력해 주세요.");
	                   return View.MYPAGE_RESERVATION;
	                }
	        }

	
/////////////////////////////////////////////////////////////////////////////////

	
	 //[1.마이페이지] -> [3.내가 쓴 한줄평]
	   public int myReview() {
	      
	      String memId = (String)userService.LoginMember.get("MEM_ID");
	         List<Map<String, Object>> myReview = mypageDao.bringReview(memId);
	         int star = 0;
	         int blackStar = 0;
	         for(Map<String, Object> r : myReview) {
	            System.out.println("주문번호: " + r.get("BUY_NO") + "\t");
	            System.out.println("리뷰작성일: " + r.get("REVIEW_DATE") + "\t");
	            System.out.println("제품명: " + r.get("MENU_NAME") +"\t");
	            System.out.println("내용: " + r.get("REVIEW_COMMENT") + "\t");
	            star = (int) r.get("REVIEW_STAR");
	            for (int i = 0; i < star; i++) {
	                   System.out.print("★");
	                   blackStar = i;
	                }
	                   for(int j = 0; j < 4-blackStar; j++) {
	                      System.out.print("☆");
	                }
	                System.out.println();
	            System.out.println("======================================");
	         }

	      System.out.println("9.돌아가기 0.홈으로");
	      int input = ScanUtil.nextInt();
	      switch(input) {
	      case 9:
	         return View.MYPAGE;
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
	      return View.MYPAGE;
	      
	
	
/////////////////////////////////////////////////////////////////////////////////

}
	   }
