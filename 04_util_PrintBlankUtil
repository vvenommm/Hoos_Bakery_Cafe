package util;

public class PrintBlankUtil {
	
	public static String printBlank(String str, int length) {
		String res = "";
		for(int i = 0; i < str.length(); i++) {
			int c = str.charAt(i);
			if(c >= 12593 && c<= 55175) {
				length -= 2;
			}else {length --;
			}
			if(length < 4) {
				length = 1;
				res += "...";
				break;
			}
			else res += str.substring(i, i+1);
		}
		for(int i = 0; i < length; i++){
			res += " ";
		}
		return res;
	}

}
