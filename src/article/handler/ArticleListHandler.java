package article.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Article;
import article.service.ArticleService;
import mvc.controller.CommandHandler;

public class ArticleListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ArticleService service = ArticleService.getInstance();
		List<Article> list = service.listArticle();
		req.setAttribute("list", list);
		return "/WEB-INF/view/article/20180130_02(list).jsp";
	}

}
