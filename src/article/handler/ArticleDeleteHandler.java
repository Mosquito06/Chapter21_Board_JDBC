package article.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleService;
import mvc.controller.CommandHandler;

public class ArticleDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int ArticleNum = Integer.parseInt(req.getParameter("no"));
		ArticleService articelService = ArticleService.getInstance();
		
		int result = articelService.deleteArticleAndContent(ArticleNum);
		
		req.setCharacterEncoding("UTF-8");
				
		if (result == 0) {	
			res.sendRedirect("list.do?deleteMessage=" + "1");
			return null;
		} else {
			res.sendRedirect("list.do?deleteMessage=" + "2");
			return null;
		}

	}

}
