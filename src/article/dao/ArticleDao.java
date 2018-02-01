package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import article.model.Article;
import article.model.ArticleContent;
import mvc.util.JdbcUtil;

public class ArticleDao {
	private static final ArticleDao INSTANCE = new ArticleDao();

	public static ArticleDao getInstance() {
		return INSTANCE;
	}

	private ArticleDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int insert(Connection conn, Article article) {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "insert into article(writer_id,writer_name,title,regdate,moddate,read_cnt) "
				+ "values(?, ?, ?, ?, ?, 0)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getId());
			pstmt.setString(2, article.getName());
			pstmt.setString(3, article.getTitle());
			pstmt.setTimestamp(4, toTimeStamp(article.getRegDate()));
			pstmt.setTimestamp(5, toTimeStamp(article.getModifiedDate()));

			int result = pstmt.executeUpdate();

			if (result > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from article");
				// last_insert_id()는 now 함수와 같음 함수임. 맨 마지막에 insert 된 값의 id를 가져옴.
				if (rs.next()) {
					int newNo = rs.getInt(1);
					return newNo;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return -1;
	}

	private Timestamp toTimeStamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	public int deleteArticle(Connection conn, int articleNum){
		PreparedStatement pstmt = null;
		
		String sql = "delete from article where article_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, articleNum);
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
		
		return -1;
	}
	
	public int updateArticle(Connection conn, Article article){
		PreparedStatement pstmt = null;
		
		String sql = "update article set title = ? where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getTitle());
			pstmt.setInt(2, article.getNumber());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
		
		
		return -1;
	}
	

	public List<Article> listArticle(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from article order by article_no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Article> list = new ArrayList<Article>();
			while (rs.next()) {
				Article article = new Article(rs.getInt("article_no"), rs.getString("writer_id"),
						rs.getString("writer_name"), rs.getString("title"), rs.getTimestamp("regdate"),
						rs.getTimestamp("moddate"), rs.getInt("read_cnt"));
				list.add(article);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return null;
	}

	public Article selectById(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from article where article_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Article article = null;

			if (rs.next()) {
				article = new Article(rs.getInt("article_no"), rs.getString("writer_id"), rs.getString("writer_name"),
						rs.getString("title"), rs.getTimestamp("regdate"), rs.getTimestamp("moddate"),
						rs.getInt("read_cnt"));

			}
			return article;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);

		}

		return null;
	}

}
