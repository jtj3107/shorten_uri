package com.jhs.exam.exam2.interceptor;

import com.jhs.exam.exam2.http.Rq;

public class NeedLoginInterceptor extends Interceptor {
	@Override
	public void init() {
	}

	@Override
	public boolean runBeforeAction(Rq rq) {
		if (rq.getControllerTypeName().equals("master") == false) {
			return true;
		}

		switch (rq.getActionPath()) {
		case "/master/home/main":
		case "/master/m/login":
		case "/master/m/doLogin":
			return true;
		}

		if (rq.isNotLogined()) {
			rq.replace("로그인 후 이용해주세요.", "../m/login?afterLoginUri=" + rq.getEncodedAfterLoginUri());

			return false;
		}

		return true;
	}

}