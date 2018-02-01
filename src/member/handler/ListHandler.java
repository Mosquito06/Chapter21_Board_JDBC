package member.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Member;
import member.service.MemberService;
import mvc.controller.CommandHandler;

public class ListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("get")) {

			List<Member> memberList = MemberService.getInstance().selectList();
			
			if (memberList != null) {
				req.setAttribute("memberList", memberList);
			}

			return "/WEB-INF/view/20180129_03(listForm).jsp";
		}
		return null;
	}

}
