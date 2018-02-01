package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import article.model.ArticleContent;
import mvc.util.JdbcUtil;

public class ArticleContentDao {
	private static final ArticleContentDao INSTANCE = new ArticleContentDao();

	public static ArticleContentDao getInstance() {
		return INSTANCE;
	}

	private ArticleContentDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int insert(Connection conn, ArticleContent content) {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into article_content(article_no,content) values(?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				return content.getNumber();
			}
		} catch (SQLException e) {

		} finally {
			JdbcUtil.close(pstmt);
		}
		return -1;
	}
	
	public int deleteArticleContent(Connection conn, int articleNum){
		PreparedStatement pstmt = null;
		
		String sql = "delete from article_content where article_no = ?";
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
	
	public int updateArticleContent(Connection conn, ArticleContent articleContent){
		PreparedStatement pstmt = null;
		
		String sql = "update article_content set content = ? where article_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, articleContent.getContent());
			pstmt.setInt(2, articleContent.getNumber());
			return pstmt.executeUpdate();			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
		}
		
		
		return -1;
	}

	public ArticleContent selectById(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from article_content where article_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			ArticleContent articleContent = null;

			if (rs.next()) {
				articleContent = new ArticleContent(rs.getInt("article_no"), rs.getString("content"));

			}
			return articleContent;
		} catch (SQLException e) {

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

		return null;

	}

}
