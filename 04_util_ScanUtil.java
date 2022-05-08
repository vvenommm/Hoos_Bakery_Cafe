package util;

import java.util.Scanner;

public class ScanUtil {

	private static Scanner s = new Scanner (System.in);
	
	public static String nextLine() {
		return s.nextLine();
	}
	
	
	public static int nextInt1() {
		return Integer.parseInt(s.nextLine());
	}
	
	
	public static int nextInt2() {
		int result = 0;
		
		try {
			result = Integer.parseInt(s.nextLine());
		}catch(Exception e) {
			System.out.println("다시 입력해주세요.\n->");
			result = nextInt2(); //재귀호출. catch -> nextInt로 다시 가서 return result까지 수행 후 호출했던 곳으로 돌아와서 result에 값을 넣고 return result 다시 수행하며 끝
		}
		return result;
	}
	
}
