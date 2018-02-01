package article.handler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Article;
import article.model.ArticleContent;
import article.service.ArticleService;
import mvc.controller.CommandHandler;

public class ArticleUpdateHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("get")){
			int articleNum = Integer.parseInt(req.getParameter("no"));
			
			ArticleService service = ArticleService.getInstance();
			HashMap<String,Object> map = service.readArticle(articleNum);
			Article article=(Article) map.get("article");
			ArticleContent content = (ArticleContent) map.get("content");
			
			req.setAttribute("article", article);
			req.setAttribute("content", content);
			return "/WEB-INF/view/article/20180131_01(updateForm).jsp";
		
		}else if(req.getMethod().equalsIgnoreCase("post")){
			res.setCharacterEncoding("UTF-8");
			int articleNum = Integer.parseInt(req.getParameter("no"));
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			Article updateArticle = null;
			ArticleContent updateContent = null;
			int result = 0;
			
			ArticleService service = ArticleService.getInstance();
			HashMap<String,Object> map = service.readArticle(articleNum);
			if(map.get("error").equals(0)){
				updateArticle = (Article) map.get("article");
				updateContent = (ArticleContent) map.get("content");
				updateArticle.setTitle(title);
				updateContent.setContent(content);
				
				result = service.updateArticleContent(updateContent, updateArticle);
			}
			
			if(result == 1){
				req.setAttribute("updateResult", "수정하였습니다.");
				res.sendRedirect("list.do");
			}else{
				req.setAttribute("updateResult", "수정하지 못했습니다..");
				res.sendRedirect("list.do");
			}
					
			return null;
		}
		
		return null;
	}

}
