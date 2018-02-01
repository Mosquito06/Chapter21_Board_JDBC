package auth.handler;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import auth.service.AuthService;
import member.model.Member;
import mvc.controller.CommandHandler;

public class LoginHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/20180129_01(LoginForm).jsp";
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			String id = req.getParameter("id");
			String password = req.getParameter("password");

			AuthService service = AuthService.getInstance();
			HashMap<String, Object> map = service.checkLoginMember(id, password);

			int error = (int) map.get("error");

			if (error == -2) {// 회원없음
				req.setAttribute("notJoin", "회원가입을 한적 없는 회원입니다.");
				return FORM_VIEW;
			} else if (error == -3) {// 비밀번호 불일치
				req.setAttribute("passNotMatch", "비밀번호가 일치하지 않습니다.");
				return FORM_VIEW;
			}

			Member member = (Member) map.get("member");
			User user = new User(member.getId(), member.getName());
			req.getSession().setAttribute("auth", user);
			res.sendRedirect("20180129_01(index).jsp");
			return null;
		}

		return null;
	}

}
