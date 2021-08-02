package com.jhs.exam.exam2.http.controller;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.dto.ShortUri;
import com.jhs.exam.exam2.http.Rq;
import com.jhs.exam.exam2.service.ShortService;

public class UsrMoveController extends Controller {

	private ShortService shortService;

	@Override
	public void init() {
		shortService = Container.shortService;
	}

	@Override
	public void performAction(Rq rq) {
		switch (rq.getActionMethodName()) {
		default:
			actionShowShortUri(rq, rq.getActionMethodName());
			break;
		}
		
	}

	private void actionShowShortUri(Rq rq, String shortCode) {
		ShortUri shortUri = shortService.getShortByShortCode(shortCode);
		
		if(shortUri == null) {
			rq.historyBack("존재하지 않는 페이지 입니다.");
			return;
		}
		
		String originUri = shortUri.getOriginUri();
		
		rq.setAttr("originUri", originUri);
		rq.jsp("master/s/move");
	}

}
