package auth.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import member.dao.MemberDao;
import member.model.Member;
import mvc.util.ConnectionProvider;
import mvc.util.JdbcUtil;

public class AuthService {
	private static final AuthService INSTANCE = new AuthService();

	public static AuthService getInstance() {
		return INSTANCE;
	}

	private AuthService() {

	}

	/*public int checkLoginMember(String id, String password) {
		Connection conn = null;
		MemberDao dao = MemberDao.getInstance();
		try {
			conn = ConnectionProvider.getConnection();

			Member member = dao.selectById(conn, id);
			if (member == null) {
				return -2; // 가입된 회원이 존재하지 않을경우
			}

			if (member.getPassword().equals(password) == false) {
				return -3; // 비밀번호 불일치
			}

			return 1;

		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.close(conn);
		}

		return -1;
	}*/
	
	//-2: notJoin 회원 없음
	//-3: 비밀번호 불일치
	public HashMap checkLoginMember(String id, String pw){
		Connection conn=null;
		MemberDao dao = MemberDao.getInstance();
		HashMap<String, Object> map = new HashMap<>();//member와 error숫자 같이 넣으려고 사용, map은 객체 넣어 보내줄 수있어서 사용.
		
		try {
			conn=ConnectionProvider.getConnection();
			Member member=dao.selectById(conn, id);
			if(member == null){
				map.put("error", -2);
				return map;//회원이 없을 경우 2를 return
			}
			
			if(member.getPassword().equals(pw)==false){
				map.put("error", -3);
				return map;//비밀번호 불일지
			}
			
			map.put("error", 0);
			map.put("member", member);
			
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			map.put("error", -1);
		}finally {
			JdbcUtil.close(conn);
		}
		
		return map;
	}

}
