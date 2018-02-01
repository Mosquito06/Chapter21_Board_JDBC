package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import member.model.Member;
import mvc.util.JdbcUtil;

public class MemberDao {
	private static final MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		return instance;
	}

	private MemberDao() {
	}

	public int insert(Connection conn, Member member) {
		String sql = "insert into member values(?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setTimestamp(4, new Timestamp(member.getRegDate().getTime()));

			return pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}

		return -1;
	}

	public int update(Connection conn, Member member) {
		String sql = "update member set password = ? where memberid = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getId());
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}

		return -1;
	}

	// 재진이가 준 파일에 있는 update 함수를 오버로딩으로 호출할 수 있도록 추가
	public int update(Connection conn, Member mem, String uPw) {
		String sql = "update member set password=? where memberid=? and password=?";
		PreparedStatement pstmt = null;

		try {
			conn.prepareStatement(sql);
			pstmt.setString(1, uPw);
			pstmt.setString(2, mem.getId());
			pstmt.setString(3, mem.getPassword());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}

		return -1;
	}

	public Member selectById(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = "select * from member where memberId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Date date = new Date(rs.getTimestamp("regdate").getTime());
				Member member = new Member(rs.getString("memberid"), rs.getString("name"), rs.getString("password"),
						date);
				return member;
			}

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return null;
	}

	public List<Member> selectMember(Connection conn) {
		List<Member> memberList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Date date = new Date(rs.getTimestamp("regdate").getTime());
				Member member = new Member(rs.getString("memberid"), rs.getString("name"), rs.getString("password"),
						date);
				memberList.add(member);
			}

			return memberList;

		} catch (SQLException e) {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;

	}

}
