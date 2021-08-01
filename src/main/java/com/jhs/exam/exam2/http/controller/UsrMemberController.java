package com.jhs.exam.exam2.http.controller;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.dto.Member;
import com.jhs.exam.exam2.http.Rq;
import com.jhs.exam.exam2.service.MemberService;
import com.jhs.exam.exam2.util.Ut;

public class UsrMemberController extends Controller {

	private MemberService memberService;
	
	@Override
	public void init() {
		memberService = Container.memberService;
	}
	
	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "login":
			actionShowLogin(rq);
			break;
		case "doLogin":
			actionDoLogin(rq);
			break;
		case "doLogout":
			actionDoLogout(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	private void actionDoLogout(Rq rq) {
		rq.removeSessionAttr("loginedMemberJson");
		rq.replace(null, "../../");
	}

	private void actionDoLogin(Rq rq) {
		String loginId = rq.getParam("loginId", "");
		String loginPw = rq.getParam("loginPw", "");

		if (loginId.length() == 0) {
			rq.historyBack("loginId를 입력해주세요.");
			return;
		}

		if (loginPw.length() == 0) {
			rq.historyBack("loginPw를 입력해주세요.");
			return;
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			rq.historyBack("해당 회원은 존재하지 않습니다.");
			return;
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			rq.historyBack("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		rq.setSessionAttr("loginedMemberJson", Ut.toJson(member, ""));

		String redirectUri = rq.getParam("redirectUri", "../home/main");

		rq.replace(member.getNickname() + "님 환영 합니다.", redirectUri);
	}

	private void actionShowLogin(Rq rq) {
		rq.jsp("master/m/login");
	}

}
