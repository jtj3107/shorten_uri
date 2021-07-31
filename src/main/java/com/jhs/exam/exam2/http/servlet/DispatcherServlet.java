package com.jhs.exam.exam2.http.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.http.Rq;
import com.jhs.exam.exam2.http.controller.Controller;
import com.jhs.mysqliutil.MysqlUtil;

@WebServlet("/master/*")
public class DispatcherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		Rq rq = new Rq(req, resp);

		// 설정된 길이보다 짧으면 리턴
		if (rq.isInvalid()) {
			rq.print("올바른 요청이 아닙니다.");
			return;
		}

		// 인터셉터에서 false리턴 할경우 리턴
		if (runInterceptors(rq) == false) {
			return;
		}

		// 해당 함수에 맞지 않아 null을 반환 했을시 올바른 요청이 아닙니다 출력
		Controller controller = getControllerByRq(rq);

		// null이 아닐경우 해당 컨트롤러 performAction 실행
		if (controller != null) {
			controller.performAction(rq);

			MysqlUtil.closeConnection();
		} else {
			rq.print("올바른 요청이 아닙니다.");
		}

	}

	private Controller getControllerByRq(Rq rq) {
		switch (rq.getControllerTypeName()) {
		case "master":
			switch (rq.getControllerName()) {
//			case "a":
//				return Container.goAddController;
			case "home":
				return Container.goHomeController;
			}

			break;
		}

		// 없을시 null 리턴1
		return null;
	}

	private boolean runInterceptors(Rq rq) {

		// 로그인 했을시 세션을 저장(바로 사용할 수 있게 로그인여부 로그인아이디 로그인멤버 json 형식으로 굳힘)
		if (Container.beforeActionInterceptor.runBeforeAction(rq) == false) {
			return false;
		}

		// 이동하는곳이 로그인 유무 걸러주는 인터셉터(로그인 필요한곳 이동시 로그인 안하면 false리턴)
		if (Container.needLoginInterceptor.runBeforeAction(rq) == false) {
			return false;
		}

		// 이동하는곳이 로그아웃 유무 걸러주는 인터셉터(로그인후 이동시 로그인 되어있으면 false리턴)
		if (Container.needLogoutInterceptor.runBeforeAction(rq) == false) {
			return false;
		}

		return true;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}