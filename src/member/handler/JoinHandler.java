package member.handler;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import member.model.Member;
import member.service.MemberService;
import mvc.controller.CommandHandler;

public class JoinHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equalsIgnoreCase("get")){
			return "/WEB-INF/view/20180126_02(JoinForm).jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")){
			Member member = new Member(req.getParameter("id"), req.getParameter("name"), req.getParameter("pass"), new Date());
			
			MemberService service = MemberService.getInstance();
			
			// insert가 안될 경우 처리 필요
			int result = service.insertMember(member);
			if(result == -2){
				req.setAttribute("duplicateId", "이미 사용 중인 아이디입니다.");
				return "/WEB-INF/view/20180126_02(JoinForm).jsp";
			}
			
			if(result <= 0){
				req.setAttribute("notInsert", "회원가입에 실패하였습니다.");
				return "/WEB-INF/view/20180126_02(JoinForm).jsp";
			}
			
			
			return "/WEB-INF/view/20180126_03(JoinSuccess).jsp";
		}
		
		
		return null;
	}

}
