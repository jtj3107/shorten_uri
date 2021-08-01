package com.jhs.exam.exam2.http.controller;

import com.jhs.exam.exam2.http.Rq;

public class UsrShortController extends Controller {

	@Override
	public void init() {

	}

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		case "doShortUri":
			actionDoShortUri(rq);
			break;
		default:
			rq.println("존재하지 않는 페이지 입니다.");
			break;
		}
	}

	private void actionDoShortUri(Rq rq) {
		
	}

}
