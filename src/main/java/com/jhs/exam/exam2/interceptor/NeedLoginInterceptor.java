package com.jhs.exam.exam2.interceptor;

import com.jhs.exam.exam2.http.Rq;

public class NeedLoginInterceptor extends Interceptor {

	public void init() {

	}

	@Override
	public boolean runBeforeAction(Rq rq) {
		if (rq.getControllerTypeName().equals("usr") == false) {
			return true;
		}

		switch (rq.getActionPath()) {
		case "/usr/home/main":
			return true;
		}

		if (rq.isNotLogined()) {
			rq.replace("로그인 후 이용해주세요.", "../member/login?afterLoginUri=" + rq.getEncodedAfterLoginUri());

			return false;
		}

		return true;
	}

}