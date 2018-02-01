package article.handler;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import article.model.Article;
import article.model.ArticleContent;
import article.service.ArticleService;
import mvc.controller.CommandHandler;

public class ArticleJsonReadHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String no = req.getParameter("no");
		int articleNum = Integer.parseInt(no);
		
		ArticleService service = ArticleService.getInstance();
		HashMap<String,Object> map = service.readArticle(articleNum);
		int error = (int) map.get("error");
		
		// Json 파일을 생성해주는 클래스(라이브러리 클래스임)
		ObjectMapper om = new ObjectMapper();
		HashMap<String, Object> sendDate = new HashMap<>();
		
		
		if(error < 0){
			sendDate.put("result", "fail");
		}else{
			sendDate.put("result", "result");
			Article article = (Article) map.get("article");
			ArticleContent content = (ArticleContent) map.get("content");
			sendDate.put("article", article);
			sendDate.put("content", content);
		}
		
		// 매개변수로 넘겨준 값을 Json 형태로 변환(모든 객체를 넣을 수 있음)
		String json = om.writeValueAsString(sendDate);
		// Json 파일임을 명시함
		res.setContentType("application/json;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.print(json);
		pw.flush();

		return null;
	}

}
