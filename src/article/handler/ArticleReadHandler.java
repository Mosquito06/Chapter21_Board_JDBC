package article.handler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Article;
import article.model.ArticleContent;
import article.service.ArticleService;
import mvc.controller.CommandHandler;

public class ArticleReadHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String no = req.getParameter("no");
		int articleNum = Integer.parseInt(no);
		
		ArticleService service = ArticleService.getInstance();
		HashMap<String,Object> map = service.readArticle(articleNum);
		Article article=(Article) map.get("article");
		ArticleContent content = (ArticleContent) map.get("content");
		
		req.setAttribute("article", article);
		req.setAttribute("content", content);
		return "/WEB-INF/view/article/20180130_05(read).jsp";
	}

}
