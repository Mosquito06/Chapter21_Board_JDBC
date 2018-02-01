package member.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.dao.MemberDao;
import member.model.Member;
import mvc.controller.CommandHandler;
import mvc.util.ConnectionProvider;

public class UpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if (req.getMethod().equalsIgnoreCase("get")) {
			
			return "/WEB-INF/view/20180130_01(updateForm).jsp";
		
		} else if (req.getMethod().equalsIgnoreCase("post")) {
		
			String id = (String) req.getSession().getAttribute("auth");
			String pw = req.getParameter("pw");
			String newPw = req.getParameter("newPw");
			
			Member member = new Member(id, pw);
			
			Connection conn = ConnectionProvider.getConnection();
			MemberDao dao = MemberDao.getInstance();

			int error = dao.update(conn, member, newPw);
			
			if (error > 0) {
				req.setAttribute("updateSuccess", "비밀번호를 변경하였습니다.");
				return "index.jsp";
			} else if (error <= 0) {
				req.setAttribute("updateFail", "현재 비밀번호가 일치하지 않습니다.");
				return "/WEB-INF/view/updateForm.jsp";
			}

			req.getSession().setAttribute("auth", id);
			res.sendRedirect("index.jsp");
			return null;

		}
		return null;
	}

}
