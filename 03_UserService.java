package service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.UserDao;
import util.ScanUtil;
import util.View;

public class UserService {
	
	//싱글톤 패턴
	private UserService() {
			
	}
	private static UserService instance; 
	public static UserService getInstance() { 
		if(instance == null) {
			instance = new UserService();
		}
		return instance;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
	
private static UserDao userDao = UserDao.getInstance();
	
/////////////////////////////////////////////////////////////////////////////////


	//로그인 멤버 담아두는 주머니
	public static Map<String, Object>LoginMember = new HashMap<String, Object>();
	

/////////////////////////////////////////////////////////////////////////////////
	
	
	//회원인지 물어보기
	public int ask() {
//		putOne();
		System.out.print("\n\t∞무한루프∞ 베이커리 카페의 회원이신가요?\n\n\n\n\t[1.네 2.회원가입 0.홈으로]\n->");
	    String check = ScanUtil.nextLine();
	    if(check.equals("1")) {
	    	return View.LOGIN;
	    }else if(check.equals("2")){
	    	return View.JOIN_AGREE;
		}else {
			return View.HOME;
	    }
	}

	   
/////////////////////////////////////////////////////////////////////////////////
	
	
	//개인정보 수집
	public int policy() {
		System.out.println("\t무한루프 베이커리 카페에 오신 것을 환영합니다!\n\t"
				+ "무한루프 베이커리 카페(이하 '무한루프') 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다.\n\t본 약관은 다양한 무한루프 서비스의 이용과 관련하여 \n\t무한루프 서비스를 제공하는 무한루프와 이를 이용하는 무한루프 서비스 회원(이하 ‘회원’) 또... 더보기");
		System.out.println("\n\n\t[1.동의합니다. 2.동의하지 않습니다.]\n\t->");
		int check = ScanUtil.nextInt(); // 동의& 비동의를 받기 위한 변수
		
		switch (check) {
		case 1 :  // 1일경우 회원가입 창으로
			return View.JOIN; 
		case 2 : // 2일경우 비회원 홈화면으로
			return View.HOME;
		default : // 다른걸 입력했을 경우 다시 처음으로 돌아가기
			System.out.println("\n\t잘못 입력되었습니다.\n\t다시 입력해주세요.");
			return View.JOIN_AGREE;
		}
	}
	
	   
/////////////////////////////////////////////////////////////////////////////////

	
	//회원가입
	public int join() {
		// 저장 변수들
		String idsv = null;
		String pwsv = null;
		String nmsv = null;
		String nnmsv = null;
		String phsv = null;
		System.out.println("\t======================회 원 가 입======================");
		
		//아이디 입력받는 부분 + 유효성 검사
		while (true) { 
			System.out.println("\t아이디는 영어로 시작하고 영어와 숫자로만 만들 수 있습니다.\n\t5 - 15자 이하");
			System.out.print("\t아이디 : ");
			String memID = ScanUtil.nextLine();
			String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,14}$"; //첫번째 글자는 무조건 영어, 그 후 영어와 숫자로만 입력 가능하게 하는 정규식
			boolean check = Pattern.matches(regex, memID); // 정규식 만족을 확인하는 boolean
			Map<String, Object> id = userDao.BringId(memID); // 곂치는 아이디를 가져오는 map
			String cp = null; // id를 저장할 String변수
			try {
			 cp = (String) id.get("MEM_ID"); // map형태를 string으로 변환해서 저장
			} catch(NullPointerException e) { // id에 null값이 오면 안되는 경고가 출력되기에 예외처리
			}
			if (cp == null && check) { // 처음에 입력받으면 테이블에 아무것도 없기에 null이 출력 그것을 해결하기 위한 if문
				idsv = memID;
				break;
			} else { //나머지 경우
				boolean check2 = memID.equals(cp); // 아이디가 중복되는지 확인하는 boolean 만약 중복이 되면 true로 바뀜
				if (check && (check2 == false)) { // 아이디가 중복되지 않고 정규식을 만족한다면 아이디를 저장
					idsv = memID;
					break;
				} else { // 아이디가 중복되거나 정규식을 만족 못한다면 출력
					System.out.println("\t중복되거나 형식이 잘못된 아이디 입니다.");
				}
			}
		}
		while (true) { // 비밀번호 입력받는 부분 + 유효성 검사
			System.out.println("\t비밀번호는 영어와 숫자의 조합으로만 가능합니다.\n\t8~15자 이하");
			System.out.print("\t비밀번호 : "); // 비밀번호 입력 받는 부분
			String memPW = ScanUtil.nextLine();
			String regex = "^[A-Za-z[0-9]]{8,15}$"; // 유효성 검사 영어와숫자로만 8~15자만 가능
			boolean check = Pattern.matches(regex, memPW); // 유효성 검사에 성공하면 true값을 가짐 나머지는 false
			if (check) { //정규식을 통과하면 비밀번호를 저장
				pwsv = memPW; 
				break;
			} else { // 정규식 통과를 못하면 다시 입력받게 함.
				System.out.println("\t비밀번호 형식이 안맞습니다 다시 입력해 주세요."); //비밀번호는 곂쳐도 상관이 없기에 중복확인은 할 필요 X
			}
		}
		while (true) { // 이름 입력받는 부분 + 유효성 검사
			System.out.print("\t이름 : "); //이름을 입력 받는 부분
			String memNAME = ScanUtil.nextLine();
			String regex = "^[가-힣]{3,4}$"; // 한글 한글자만 입력가능 모음자음 분리되면 정규식 성립못함. 3~4글자 
			boolean check = Pattern.matches(regex, memNAME); // 유효성 검사에 성공하면 true값을 가짐 나머지는 false
			if (check) { // 정규식을 통과하면 이름을 저장
				nmsv = memNAME;
				break;
			} else { // 정규식을 통과하지 못하면 다시 입력을 받음.
				System.out.println("\t이름을 잘못 입력하셨습니다 다시 입력해주세요."); // 이름도 동명이인이 존재하기에 비밀번호와 같은 이유로 중복확인 X
			}
		}
		while (true) { // 닉네임 입력받기 + 유효성 검사
			System.out.println("\t닉네임은 영어, 한글 그리고 숫자의 조합으로만 가능합니다. \n\t2~8자 이하"); //닉네임을 받는 부분
			System.out.print("\t닉네임 : ");
			String memNNAME = ScanUtil.nextLine();
			String regex = "^[가-힣ㄱ-ㅎa-zA-Z0-9._-]{2,8}$"; // 닉네임은 글자와 한글의 자음, 모음, 알파벳 그리고 숫자로 만들기가 가능함 2~8글자
			boolean check = Pattern.matches(regex, memNNAME); // 정규식을 만족한다면 true 값을 가지게 됨
			Map<String, Object> Nname = userDao.BringNname(memNNAME); //  중복된 닉네임을 확인하기 위한 부분
			String cp = null; // 중복된 닉네임을 String으로 저장할 변수
			try {
				 cp = (String) Nname.get("MEM_NNAME"); // map을 string으로 변환하는 부분
				} catch(NullPointerException e) { // map에는 값이 null일 수 없다는 오류가 뜨기에 예외처리
				}
			if (cp == null && check) { // 만약 첫번째로 가입하는 사람이고 정규식을 만족한다면 닉네임을 저장
				nnmsv = memNNAME;
				break;
			} else { // 첫번째가 아니라면 닉네임이 중복되는 지를 확인
				boolean check2 = memNNAME.equals(cp); // 만약 닉네임이 중복된다면 true값을 가지게 됨 
				if (check && (check2 == false)) { // 닉네임이 중복되지않고 정규식을 만족한다면 저장
					nnmsv = memNNAME;
					break;
				} else { // 정규식을 만족하지 못하거나 닉네임이 중복이 되었을경우 출력
					System.out.println("\t중복되거나 형식이 잘못된 닉네임 입니다.");
				}
			}
		}
		while (true) {
			System.out.println("\t연락처는 - 없이 작성해 주세요."); // 연락처를 입력받는 부분
			System.out.print("\t연락처 : ");
			String memNUMBER = ScanUtil.nextLine();
			String regex = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})(\\d{4})$"; // 핸드폰 번호만 받을 수 있게 설정  010, 016~019 앞자리 만 가능하고  중간번호는 3~4자리 끝번호는 4자리만 가능
			boolean check = Pattern.matches(regex, memNUMBER); // 정규식을 만족하였을 경우 true값을 받음
			Map<String, Object> Number = userDao.BringNumber(memNUMBER);  // 중복된 전화번호를 확인하는 부분
			String cp = null; // 중복된 전화번호를 String으로 저장할 변수
			try {
				 cp = (String)Number.get("MEM_NUMBER"); //map을 string으로 변환 하여 저장
				} catch(NullPointerException e) { // map에는 null값이 있으면 오류가 뜨기에 예외처리
				} 
			if (cp == null && check) { // 첫번째로 등록한 회원은 비교할 번호가 없기에 정규식만 통과하면 저장하게 만듦
				phsv = memNUMBER;
				break;
			} else { // 첫번째 다음으로 등록한 회원들은 번호를 비교
				boolean check2 = memNUMBER.equals(cp); // 중복되는 번호가 있으면 값이 true로 바뀜
				if (check && check2 == false) { // 중복되지 않고 정규식을 통과하게 된다면 번호를 저장
					phsv = memNUMBER;
					break;
				} else { //중복되거나 정규식을 만족하지 못하면 출력
					System.out.println("\t중복되거나 형식이 잘못된 전화번호 입니다.");
				}
			}
		}
		int result = userDao.insertUser(idsv, pwsv, nmsv, nnmsv, phsv); // 위의 조건들을 다 만족하면 테이블에 저장
		if (0 < result) {
			System.out.println("\r\n" + 
					"⠀⠀⠀⠠⣤⣤⣤⡄⠀⠀⣤⣤⣤⡄⠀⠀⣤⣤⣤⡄⠀⣤⣤⣤⣤⣤⣤⣤⣤⣤⡄⠀⠀⣤⣤⣤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣤⣤⣀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣤⣤⣤⣄⡀⠀⠀   ⠀⠀⣤⣤⣤⣤⣤⠀⠀⠀⢠⣤⣤⣤⣤⡄⠀⢠⣤⣤⣤⣤⣤⣤⣤⣤⣤⡀⠀⠀⠀⠀\r\n" + 
					"⠀⠀⠀⠀⢿⣿⣿⣇⠀⢰⣿⣿⣿⣷⠀⢀⣿⣿⣿⠃⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⣿⣿⣿⣿⣿⡆⠀⠀⣾⣿⣿⣿⣿⡇⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀\r\n" + 
					"⠀⠀⠀⠀⢸⣿⣿⣿⠀⣸⣿⣿⣿⣿⡄⢸⣿⣿⡿⠀⠀⣿⣿⣿⣏⠀⠀⠀⠀⠀⠀⠀  ⠀⣿⣿⣿⡇⠀⠀⠀   ⠀   ⣿⣿⣿⠋⠀⠀⠈⣿⣿⣿⡆⠀⢸⣿⣿⣿⠋⠀⠀⠈⢿⣿⣿⣧⠀⠀⣿⣿⣿⣿⣿⣷⠀⢰⣿⣿⣿⣿⣿⡇⠀⢸⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
					"⠀⠀⠀⠀⠀⣿⣿⣿⡇⣿⣿⡏⣿⣿⣇⣺⣿⣿⡇⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⠀⠀⠈⠉⠉⠁⠀⣿⣿⣿⡏⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⣿⣿⣿⢹⣿⣿⡇⣾⣿⡇⣿⣿⣿⡇⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀\r\n" + 
					"⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⠀⢹⣿⣿⣿⣿⣿⠁⠀⠀⣿⣿⣿⡟⠛⠛⠛⠛⠛⠀⠀⠀⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⠀⠀⣤⣤⣤⡄⠀⢻⣿⣿⣧⠀⠀⠀⠀⢸⣿⣿⣿⠀⠀⣿⣿⣿⠀⣿⣿⣿⣿⣿⠀⣿⣿⣿⡇⠀⢸⣿⣿⣿⠛⠛⠛⠛⠛⠃⠀⠀⠀⠀⠀\r\n" + 
					"⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⡏⠀⠘⣿⣿⣿⣿⡏⠀⠀⠀⣿⣿⣿⣧⣤⣤⣤⣤⣤⣤⠀⠀⣿⣿⣿⣷⣶⣶⣶⣶⣶ ⣿⣿⣿⣷⣤⣤⣴⣿⣿⣿⠁⠀⠘⣿⣿⣿⣦⣤⣤⣴⣿⣿⣿⠏⠀⣿⣿⣿⠀⢸⣿⣿⣿⡇⠀⣿⣿⣿⡇⠀⢸⣿⣿⣿⣤⣤⣤⣤⣤⣤⡄⠀⠀⠀⠀\r\n" + 
					"⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⡇⠀⠀⣿⣿⣿⣿⠃⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠈⠻⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⠈⠻⢿⣿⣿⣿⣿⣿⠿⠋⠀⠀⣿⣿⣿⠀⠀⣿⣿⣿⠃⠀⣿⣿⣿⡇⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀\r\n" + 
					"");
			System.out.println("\n\t회원가입 성공");  // 정상적으로 들어갈 경우 출력
		} else {
			System.out.println("\t회원가입 실패하셨습니다.\n\t다시 시도해주세요."); // 비정상적일 경우 출력
			return View.HOME;
		}
		return View.HOME; // 비회원 홈화면으로 이동
	}

	
	/////////////////////////////////////////////////////////////////////////////////
	
	
	public int login() {  
		System.out.println("\t====================== 로 그 인 ======================");
		System.out.print("\t아이디 : ");
		String memId = ScanUtil.nextLine();  //아이디 입력
		System.out.print("\t비밀번호  : ");
		String password = ScanUtil.nextLine();  // 비밀번호 입력
		Map<String, Object> member = userDao.selectMember(memId, password); // 아이디와 비밀번호가 존재하는지 확인 하기 위한 부분
		Map<String, Object> adcp = userDao.BringId(memId); // 관리자를 확인하기 위한 부분
		String cp = null;  // 관리자 아이디를 저장
		try {
			 cp = (String)adcp.get("MEM_ID"); //map을 string으로 변환 하여 저장
			} catch(NullPointerException e) { // map에는 null값이 있으면 오류가 뜨기에 예외처리
			}
		if(member == null) {  // 아이디와 비밀번호가 존재하지 않을 경우
			System.out.println("\t아이디 혹은 비밀번호를 잘못 입력하셨습니다.\n\t다시입력해주세요");
		}else if(cp.equals("admin")){ // 관리자로 로기인 했을 경우
			System.out.println("\t관리자 로그인 성공");
			
			LoginMember = member;
			return View.ADMIN_HOME;
		}
		else { // 일반 회원으로 로그인 했을 경우
			System.out.println("\n\n\n\t어서오세요 (^_^)/\n\n\t∞ 무 한 루 프 ∞ 베이커리 카페입니다~");
			
			LoginMember = member;
			
			return View.MEM_HOME;
		}
		return View.LOGIN;
	}

	
	/////////////////////////////////////////////////////////////////////////////////
	
	
	public int logout() {
		System.out.println("\t로그아웃 완료!");
		LoginMember = null; // member에 저장되어 있는 값을 null처리 
		LoginMember = new HashMap<String, Object>();
		return View.HOME;  // null 처리 후 비회원 홈 화면으로 이동
	}

	
	/////////////////////////////////////////////////////////////////////////////////
	
	
	
	
}
