package dao;

import java.util.*;
import service.UserService;

import util.JDBCUtil;

public class UserDao {

	//싱글톤 패턴
	private UserDao() {
		
	}
	private static UserDao instance; 
	public static UserDao getInstance() { 
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////
	
	
	public int insertUser(String memId, String memPw, String memName, String memNname, String memNumber) { //정보등록메서드
		String sql = "INSERT INTO B_MEMBER"
				+ "	VALUES (?, ?, ?, ?, ?, 1000)";
		List<Object> param = new ArrayList<Object>();
		param.add(memId);
		param.add(memPw);
		param.add(memName);
		param.add(memNname);
		param.add(memNumber);
		return JDBCUtil.update(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////

	
	public Map<String, Object> BringId(String memId){ //아이디 중복 확인 매서드
		String sql ="SELECT MEM_ID"
				+ "		FROM B_MEMBER"  
				+ "		WHERE MEM_ID = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(memId);
		return JDBCUtil.selectOne(sql, param);
	}

	
/////////////////////////////////////////////////////////////////////////////////

	
	public Map<String, Object> BringNname(String memNname){ //닉네임 중복 확인 매서드
		String sql ="SELECT MEM_NNAME"
				+ "		FROM B_MEMBER"
				+ "		WHERE MEM_NNAME = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(memNname);
		return JDBCUtil.selectOne(sql, param);
	}

	
/////////////////////////////////////////////////////////////////////////////////

	
	public Map<String, Object> BringNumber(String memNumber){ //전화번호 중복 확인 매서드
		String sql ="SELECT MEM_NUMBER"
				+ "		FROM B_MEMBER"
				+ "		WHERE MEM_NUMBER = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(memNumber);
		return JDBCUtil.selectOne(sql, param);
	}

	
/////////////////////////////////////////////////////////////////////////////////

	
	public Map<String, Object>selectMember(String memId, String password){
		
		String sql = "SELECT MEM_ID, MEM_PW, MEM_NAME, MEM_NNAME, MEM_NUMBER, MEM_MILEAGE"
				+ "		FROM B_MEMBER"
				+ "		WHERE MEM_ID = ?"
				+ "		AND MEM_PW = ?";
		List<Object> param = new ArrayList<Object>();
		param.add(memId);
		param.add(password);
		return JDBCUtil.selectOne(sql, param);
	}
	
	
/////////////////////////////////////////////////////////////////////////////////
	
}
