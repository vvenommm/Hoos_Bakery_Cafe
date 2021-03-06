package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.BoardDao;
import util.PrintBlankUtil;
import util.ScanUtil;
import util.View;

public class BoardService {
	
	//싱글톤 패턴 : 메모리에 딱 한 번 생성. 메모리를 아껴서 쓸 수 있다. 전역변수처럼 사용
	private BoardService() {
			
	}
	private static BoardService instance;
	public static BoardService getInstance() { 
		if(instance == null) {
			instance = new BoardService();
		}  
		return instance;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	private static BoardDao boardDao = BoardDao.getInstance();
	private static UserService userService = UserService.getInstance();
	
	
/////////////////////////////////////////////////////////////////////////////////
	
	
	//모든 게시글 출력
	public int boardList() {
		List<Map<String, Object>> boardList = boardDao.allBoardList();
		
		String num = " 글번호";
		String title = "제목";
		String writer = "작성자";
		String dated = "작성일";
		System.out.println("\t┌────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│" + PrintBlankUtil.printBlank(num, 15) + PrintBlankUtil.printBlank(title, 45) + PrintBlankUtil.printBlank(writer, 10) + PrintBlankUtil.printBlank(writer, 10) + "│");
		System.out.println("\t├────────────────────────────────────────────────────────────────────────────────┤");
		
		for(Map<String, Object> board : boardList) {
			int number = (int)board.get("BOARD_NO");
			title = (String) board.get("BOARD_TITLE");
			writer = (String) board.get("MEM_NNAME");
			dated = (String) board.get("BOARD_DATE");
			System.out.println("\t│  " + number + "\t" + PrintBlankUtil.printBlank(title, 49) + PrintBlankUtil.printBlank(writer, 12) + PrintBlankUtil.printBlank(dated, 12) + "│");
					
			System.out.print("\t│ - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - │\n");
		}
		System.out.println("\t└────────────────────────────────────────────────────────────────────────────────┘");
		if(userService.LoginMember.size() > 0) {
			if(userService.LoginMember.get("MEM_ID").equals("admin")) {
				System.out.println("\n\n\n[1.조회 2.공지사항만 보기 3.이벤트만 보기 4.글쓰기 0.홈으로]");
				int input = ScanUtil.nextInt();
				switch(input) {
				case 1:
					return View.BOARD_READ; //글 읽기
				case 2:
					return View.BOARD_NOTICE; //공지사항만 보기
				case 3:
					return View.BOARD_EVENT; //이벤트만 보기
				case 4:
					return View.BOARD_POSTING; //[4.글쓰기]
				case 0:
					return View.ADMIN_HOME; //[0.관리자 홈으로]
				}
			}else{ //회원 홈
				System.out.println("\n\n\n[1.조회 2.공지사항만 보기 3.이벤트만 보기 0.홈으로]");
				int input = ScanUtil.nextInt();
				switch(input) {
					case 1:
						return View.BOARD_READ; //글 읽기
					case 2:
						return View.BOARD_NOTICE; //공지사항만 보기
					case 3:
						return View.BOARD_EVENT; //이벤트만 보기
					case 0:
						return View.MEM_HOME;
				}
			}
		}else { //비회원 홈
			System.out.println("\n\n\n[1.조회 2.공지사항만 보기 3.이벤트만 보기 0.홈으로]");
			int input = ScanUtil.nextInt();
				
			switch(input) {
				case 1:
					return View.BOARD_READ; //글 읽기
				case 2:
					return View.BOARD_NOTICE; //공지사항만 보기
				case 3:
					return View.BOARD_EVENT; //이벤트만 보기
				case 0:
				return View.HOME;
			}
		}
		return View.HOME;
		}
	
	
/////////////////////////////////////////////////////////////////////////////////


	public int noticeList() {
		List<Map<String, Object>> noticeList = boardDao.noticeList();
		
		String num = " 글번호";
		String title = "제목";
		String writer = "작성자";
		String dated = "작성일";
		String content = "내용";
		
		System.out.println("\t┌────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│" + PrintBlankUtil.printBlank(num, 15) + PrintBlankUtil.printBlank(title, 45) + PrintBlankUtil.printBlank(writer, 10) + PrintBlankUtil.printBlank(writer, 10) + "│");
		System.out.println("\t├────────────────────────────────────────────────────────────────────────────────┤");
		
		for(Map<String, Object> board : noticeList) {
			int number = (int)board.get("BOARD_NO");
			title = (String) board.get("BOARD_TITLE");
			writer = (String) board.get("MEM_NNAME");
			dated = (String) board.get("BOARD_DATE");
			System.out.println("\t│  " + number + "\t" + PrintBlankUtil.printBlank(title, 49) + PrintBlankUtil.printBlank(writer, 12) + PrintBlankUtil.printBlank(dated, 12) + "│");
					
			System.out.print("\t│ - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - │\n");
		}
		System.out.println("\t└────────────────────────────────────────────────────────────────────────────────┘");
		
		System.out.println("\n\n[1.조회 9.뒤로가기 0.홈으로]");
		int input = ScanUtil.nextInt();
		switch(input) {
		case 1:
			return View.BOARD_READ_NOTICE; //글 읽기
		case 9:
			return View.BOARD_LIST; //전체 게시글 보기
		case 0:
			if(userService.LoginMember.size() > 0) {
			if (userService.LoginMember.get("MEM_ID").equals("admin")){ //관리자 홈
				return View.ADMIN_HOME;
			}else { //회원 홈
				return View.MEM_HOME;
			}}else { //비회원 홈
				return View.HOME;
			}
		}
		return View.BOARD_LIST;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////


	public int eventList() {
		List<Map<String, Object>> eventList = boardDao.eventList();
		
		String num = " 글번호";
		String title = "제목";
		String writer = "작성자";
		String dated = "작성일";
		System.out.println("\t┌────────────────────────────────────────────────────────────────────────────────┐");
		System.out.println("\t│" + PrintBlankUtil.printBlank(num, 15) + PrintBlankUtil.printBlank(title, 45) + PrintBlankUtil.printBlank(writer, 10) + PrintBlankUtil.printBlank(writer, 10) + "│");
		System.out.println("\t├────────────────────────────────────────────────────────────────────────────────┤");
		
		for(Map<String, Object> board : eventList) {
			int number = (int)board.get("BOARD_NO");
			title = (String) board.get("BOARD_TITLE");
			writer = (String) board.get("MEM_NNAME");
			dated = (String) board.get("BOARD_DATE");
			System.out.println("\t│  " + number + "\t" + PrintBlankUtil.printBlank(title, 49) + PrintBlankUtil.printBlank(writer, 12) + PrintBlankUtil.printBlank(dated, 12) + "│");
					
			System.out.print("\t│ - - - - - - - - - - - - - - - - - - - - - - -  - - - - - - - - - - - - - - - - │\n");
		}
		System.out.println("\t└────────────────────────────────────────────────────────────────────────────────┘");
		
		System.out.println("\n\n[1.조회 9.뒤로가기 0.홈으로]");
		int input = ScanUtil.nextInt();
		switch(input) {
		case 1:
			return View.BOARD_READ_EVENT; //글 읽기
		case 9:
			return View.BOARD_LIST; //전체 게시글 보기
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
		return View.BOARD_LIST;
	}
	
	
/////////////////////////////////////////////////////////////////////////////////


	public int readPost() {
		System.out.println("조회하려는 글 번호를 입력해주세요. : ");
		int boardNo = ScanUtil.nextInt();
		Map<String, Object> board = boardDao.readPost(boardNo);
		
		String title = "제목";
		String writer = "작성자";
		String dated = "작성일";
		title = (String) board.get("BOARD_TITLE");
		writer = (String) board.get("MEM_NNAME");
		dated = (String) board.get("BOARD_DATE");
		String contents = (String) board.get("BOARD_CONTENT");
		
		System.out.println("\t┌────────────────────────────────────────────────────────────────────────────────┐"); //80짜리
		System.out.println("\t│\t" + PrintBlankUtil.printBlank(title, 72) + " │");
		System.out.println("\t└────────────────────────────────────────────────────────────────────────────────┘");
		
		System.out.println("\t    " + "\t\t\t\t\t\t\t" + PrintBlankUtil.printBlank(writer, 12) + PrintBlankUtil.printBlank(dated, 12) + "\n");
		System.out.println("\t   " + "\t" + contents);
					
		System.out.println("\t──────────────────────────────────────────────────────────────────────────────────");
		
		
		
		
		
//		System.out.println("┌─────────────────────┐");
//		System.out.println("글번호 : " + board.get("BOARD_NO"));
//		System.out.println(board.get("BOARD_TITLE"));
//		System.out.println(" ──────────────────────");
//		System.out.print("작성자 : " + board.get("MEM_NNAME") + "\t작성일 : " + board.get("BOARD_DATE") + "\n\n");
//		System.out.println(board.get("BOARD_CONTENT"));
//		System.out.println("└─────────────────────┘");
		
		if (userService.LoginMember.size() > 0) {
			if (userService.LoginMember.get("MEM_ID").equals("admin")){
			//관리자 로그인
			System.out.println("\n\n\n[1.수정 2.삭제 9.뒤로가기 0.홈으로]");
			int input = ScanUtil.nextInt();

			switch(input) {
			case 1: //1.수정 선택 시
				int editResult = editPost(board); //수정 완료면 1, 실패면 0
				if(0 < editResult) {
					System.out.println("수정 완료!");
					return View.BOARD_LIST; //수정 완료 후 게시판 목록으로
				}else {
					System.out.println("수정 실패!");
					return View.BOARD_READ; //수정 실패 후 다시 글 읽기로
				}
			case 2: //2.삭제 선택 시
				int delResult = deletePost(board);
				if(delResult == 1) {
					System.out.println("삭제 완료!");
					return View.BOARD_LIST;
				}else if(delResult == 0) {
					System.out.println("삭제 실패!");
					return View.BOARD_READ;
				}else if(delResult == 2) {
					System.out.println("게시판 목록으로 돌아갑니다.");
					return  View.BOARD_LIST;
				}
			case 9: //9.뒤로가기 선택 시 -> 전체 게시판
				return View.BOARD_LIST;
			case 0: //0.관리자 홈으로 선택 시
				return View.ADMIN_HOME;
			}
		}else{
			//회원 로그인
			System.out.println("\n\n\n[9.뒤로가기 0.홈으로]");
			int input = ScanUtil.nextInt();
			switch(input) {
				case 9: //9.뒤로가기 선택 시
					return View.BOARD_LIST;
				case 0: //0.회원 홈으로 선택 시
					return View.MEM_HOME;
				}
			}
		}else{
				//비회원
				System.out.println("\n\n\n[9.뒤로가기 0.홈으로]");
				int input = ScanUtil.nextInt();
				switch(input) {
				case 9: //9.뒤로가기 선택 시
					return View.BOARD_LIST;
				case 0: //0.일반 홈으로 선택 시
					return View.HOME;
			}
		}
		return View.HOME;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////

	
	//공지사항 글 조회 시
	public int readPostNotice() {
		List<Map<String, Object>> noticeList = boardDao.noticeList();
		
		System.out.println("조회하려는 글 번호를 입력해주세요. : ");
		int noticeNo = ScanUtil.nextInt();
		Map<String, Object> board = noticeList.get(noticeList.size()-noticeNo);
		
		System.out.println("┌─────────────────────┐");
		System.out.println(board.get("BOARD_TITLE"));
		System.out.println(" ──────────────────────");
		System.out.print("작성자 : " + board.get("MEM_NNAME") + "\t작성일 : " + board.get("BOARD_DATE") + "\n\n");
		System.out.println(board.get("BOARD_CONTENT"));
		System.out.println("└─────────────────────┘");
		
		if(userService.LoginMember.size() > 0) {
		if (userService.LoginMember.get("MEM_ID").equals("admin")){
			//관리자 로그인
			System.out.println("\n\n\n[1.수정 2.삭제 9.뒤로가기 0.홈으로]");
			int input = ScanUtil.nextInt();

			switch(input) {
			case 1: //1.수정 선택 시
				int editResult = editPost(board); //수정 완료면 1, 실패면 0
				if(0 < editResult) {
					System.out.println("수정 완료!");
					return View.BOARD_LIST; //수정 완료 후 게시판 목록으로
				}else {
					System.out.println("수정 실패!");
					return View.BOARD_READ; //수정 실패 후 다시 글 읽기로
				}
			case 2: //2.삭제 선택 시
				int delResult = deletePost(board);
				if(delResult == 1) {
					System.out.println("삭제 완료!");
					return View.BOARD_LIST;
				}else if(delResult == 0) {
					System.out.println("삭제 실패!");
					return View.BOARD_READ;
				}else if(delResult == 2) {
					System.out.println("게시판 목록으로 돌아갑니다.");
					return  View.BOARD_LIST;
				}
			case 9: //9.뒤로가기 선택 시 -> 전체 게시판
				return View.BOARD_LIST;
			case 0: //0.관리자 홈으로 선택 시
				return View.ADMIN_HOME;
			}
		}else{
			//회원 로그인
			System.out.println("\n\n\n[9.뒤로가기 0.홈으로]");
			int input = ScanUtil.nextInt();
			switch(input) {
				case 9: //9.뒤로가기 선택 시
					return View.BOARD_LIST;
				case 0: //0.회원 홈으로 선택 시
					return View.MEM_HOME;
				}
		}}else {
				//비회원
				System.out.println("\n\n\n[9.뒤로가기 0.홈으로]");
				int input = ScanUtil.nextInt();
				switch(input) {
				case 9: //9.뒤로가기 선택 시
					return View.BOARD_LIST;
				case 0: //0.일반 홈으로 선택 시
					return View.HOME;
			}
		}
		return View.HOME;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////

	
	//이벤트 글 조회 시
	public int readPostEvent() {
		List<Map<String, Object>> eventList = boardDao.eventList();
		
		System.out.println("조회하려는 글 번호를 입력해주세요. : ");
		int eventNo = ScanUtil.nextInt();
		Map<String, Object> board = eventList.get(eventList.size()-eventNo);
		
		
		System.out.println("┌─────────────────────┐");
		System.out.println(board.get("BOARD_TITLE"));
		System.out.println(" ──────────────────────");
		System.out.print("작성자 : " + board.get("MEM_NNAME") + "\t작성일 : " + board.get("BOARD_DATE") + "\n\n");
		System.out.println(board.get("BOARD_CONTENT"));
		System.out.println("└─────────────────────┘");
		
		if(userService.LoginMember.size() > 0) {
		if (userService.LoginMember.get("MEM_ID").equals("admin")){
			//관리자 로그인
			System.out.println("\n\n\n[1.수정 2.삭제 9.뒤로가기 0.홈으로]");
			int input = ScanUtil.nextInt();

			switch(input) {
			case 1: //1.수정 선택 시
				int editResult = editPost(board); //수정 완료면 1, 실패면 0
				if(0 < editResult) {
					System.out.println("수정 완료!");
					return View.BOARD_LIST; //수정 완료 후 게시판 목록으로
				}else {
					System.out.println("수정 실패!");
					return View.BOARD_READ; //수정 실패 후 다시 글 읽기로
				}
			case 2: //2.삭제 선택 시
				int delResult = deletePost(board);
				if(delResult == 1) {
					System.out.println("삭제 완료!");
					return View.BOARD_LIST;
				}else if(delResult == 0) {
					System.out.println("삭제 실패!");
					return View.BOARD_READ;
				}else if(delResult == 2) {
					System.out.println("게시판 목록으로 돌아갑니다.");
					return  View.BOARD_LIST;
				}
			case 9: //9.뒤로가기 선택 시 -> 전체 게시판
				return View.BOARD_LIST;
			case 0: //0.관리자 홈으로 선택 시
				return View.ADMIN_HOME;
			}
		}else{
			//회원 로그인
			System.out.println("\n\n\n[9.뒤로가기 0.홈으로]");
			int input = ScanUtil.nextInt();
			switch(input) {
				case 9: //9.뒤로가기 선택 시
					return View.BOARD_LIST;
				case 0: //0.회원 홈으로 선택 시
					return View.MEM_HOME;
				}
		}}else {
				//비회원
				System.out.println("\n\n\n[9.뒤로가기 0.홈으로]");
				int input = ScanUtil.nextInt();
				switch(input) {
				case 9: //9.뒤로가기 선택 시
					return View.BOARD_LIST;
				case 0: //0.일반 홈으로 선택 시
					return View.HOME;
			}
		}
		return View.HOME;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////
	
	//게시판 관리 : 글 수정, 삭제, 작성


	//관리자 글 수정
	public int editPost(Map<String, Object> board) {
		System.out.println("제목을 새로 입력해주세요. : ");
		String title = ScanUtil.nextLine();
		System.out.println("내용을 새로 입력해주세요. : ");
		String content = ScanUtil.nextLine();

		int result = boardDao.editPost(title, content, board); //글 수정 메소드
		return result;
	}


	/////////////////////////////////////////////////////////////////////////////////


	//관리자 글 삭제
	public int deletePost(Map<String, Object> board) {
		int result = 0;
		System.out.println("정말 삭제하시겠습니까?\n[1.네 2.아니오]");
		int input = ScanUtil.nextInt();
		if(input == 1) {
			result = boardDao.deletePost(board);
		}else {
			return 2;
		}
		return result;
	}


	/////////////////////////////////////////////////////////////////////////////////


	//관리자 글 작성
	public int posting() {
		System.out.println("말머리를 선택해주세요.\n[1.공지사항 2.이벤트] : ");
		int code = ScanUtil.nextInt();
		System.out.println("제목을 입력해주세요. : ");
		String title = ScanUtil.nextLine();
		System.out.println("내용을 입력해주세요. : ");
		String content = ScanUtil.nextLine();

		List<Object> input = new ArrayList<Object>();

		input.add(code);
		input.add(title);
		input.add(content);

		int result = boardDao.insertPost(input);

		return View.BOARD_LIST;
	}



}

