package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.dao.MemberDao;
import member.model.Member;
import mvc.util.ConnectionProvider;
import mvc.util.JdbcUtil;

public class MemberService {
	private static final MemberService instance = new MemberService();

	public static MemberService getInstance() {
		return instance;
	}

	private MemberService() {
	}

	public Member selectMember(String id) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			MemberDao dao = MemberDao.getInstance();

			Member getMember = dao.selectById(conn, id);
			return getMember;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return null;

	}

	public List<Member> selectList() {
		Connection conn = null;
		List<Member> memberList = new ArrayList<>();

		try {
			conn = ConnectionProvider.getConnection();
			MemberDao dao = MemberDao.getInstance();

			return memberList = dao.selectMember(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	// -2 : dupulicateId
	public int insertMember(Member member) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			MemberDao dao = MemberDao.getInstance();

			// id 중복 체크
			Member existMember = dao.selectById(conn, member.getId());
			if (existMember != null) {
				return -2;
			}

			return dao.insert(conn, member);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}

		return -1;
	}

	public int updateMember(Member member) {
		Connection conn = null;
		int result = 0;
		try {
			conn = ConnectionProvider.getConnection();
			MemberDao dao = MemberDao.getInstance();

			return dao.update(conn, member);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return -1;

	}
}
