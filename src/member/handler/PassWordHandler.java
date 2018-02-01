package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.model.User;
import member.model.Member;
import member.service.MemberService;
import mvc.controller.CommandHandler;

public class PassWordHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("get")) {

			return "/WEB-INF/view/20180129_02(passwordForm).jsp";
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			
			String currentPass = req.getParameter("currentPass");
			User user = (User) req.getSession().getAttribute("auth");
			Member checkMember = MemberService.getInstance().selectMember(user.getId());
			
			if (checkMember.getPassword().equals(currentPass)) {
				String changePass = req.getParameter("changePass");
				Member member = new Member(user.getId(), changePass);

				MemberService.getInstance().updateMember(member);
				req.setAttribute("correct", "비밀번호변경이 완료되었습니다.");
				
				return "20180129_01(index).jsp";
			} else {
				req.setAttribute("notCorrect", "비밀번호가 일치하지 않습니다.");
				return "/WEB-INF/view/20180129_02(passwordForm).jsp";
			}
		}

		return null;
	}

}
