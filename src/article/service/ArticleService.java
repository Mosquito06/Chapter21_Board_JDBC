package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import mvc.util.ConnectionProvider;
import mvc.util.JdbcUtil;

public class ArticleService {
	private static final String log = "[ArticleService]";
	private static final ArticleService INSTANCE = new ArticleService();

	public static ArticleService getInstance() {
		return INSTANCE;
	}

	private ArticleService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int insertArticle(String id, String name, String title, String content) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);// 트랜잭션

			ArticleDao articleDao = ArticleDao.getInstance();
			ArticleContentDao contentDao = ArticleContentDao.getInstance();

			Date date = new Date();
			Article article = new Article(0, id, name, title, date, date, 0);
			int newArticleNo = articleDao.insert(conn, article);
			if (newArticleNo < 0) {
				System.out.println(log + ": insert article fail");
				return -2;// insert article fail
			}
			ArticleContent articleContent = new ArticleContent(newArticleNo, content);
			int newContentNo = contentDao.insert(conn, articleContent);
			if (newContentNo < 0) {
				return -3;
			}

			conn.commit();
			return 0;
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return -1;
	}

	public int deleteArticleAndContent(int articleNum) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			ArticleDao articleDao = ArticleDao.getInstance();
			ArticleContentDao articleContentDao = ArticleContentDao.getInstance();

			int articleResult = articleDao.deleteArticle(conn, articleNum);
			int articleContentResult = articleContentDao.deleteArticleContent(conn, articleNum);

			if (articleResult > 0 && articleContentResult > 0) {
				return 0;
			} else {
				return -1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return -1;
	}

	public int updateArticleContent(ArticleContent articleContent, Article article) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();

			ArticleContentDao articleContentDao = ArticleContentDao.getInstance();
			ArticleDao articleDao = ArticleDao.getInstance();
			
			int articleResult = articleDao.updateArticle(conn, article);
			int contentResult = articleContentDao.updateArticleContent(conn, articleContent);

			if(articleResult > 0 && contentResult > 0){
				return 1;
			}else{
				return -1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}

		return -1;
	}

	public List<Article> listArticle() {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			ArticleDao dao = ArticleDao.getInstance();
			return dao.listArticle(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return null;
	}

	public HashMap<String, Object> readArticle(int articleNum) {
		Connection conn = null;
		HashMap<String, Object> map = new HashMap<>();

		try {
			conn = ConnectionProvider.getConnection();
			ArticleDao articleDao = ArticleDao.getInstance();
			ArticleContentDao articleContentDao = ArticleContentDao.getInstance();

			Article article = articleDao.selectById(conn, articleNum);
			if (article == null) {

				map.put("error", -2);
				return map;
			}

			ArticleContent content = articleContentDao.selectById(conn, articleNum);
			if (content == null) {

				map.put("error", -2);
				return map;
			}

			map.put("error", 0);
			map.put("article", article);
			map.put("content", content);

			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			map.put("error", -1);
		} finally {
			JdbcUtil.close(conn);

		}
		return map;
	}
}
