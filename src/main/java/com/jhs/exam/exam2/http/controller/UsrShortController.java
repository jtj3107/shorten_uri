package com.jhs.exam.exam2.http.controller;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.dto.ResultData;
import com.jhs.exam.exam2.http.Rq;
import com.jhs.exam.exam2.service.ShortService;

public class UsrShortController extends Controller {
	private ShortService shortService;

	@Override
	public void init() {
		shortService = Container.shortService;
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
		int memberId = rq.getLoginedMemberId();
		String originUri = rq.getParam("originUri", "");
		String text = rq.getParam("text", "내용없음");
		long shortNum = getLastShortUriNum();

		String redirectUri = rq.getParam("redirectUri", "../home/main");

		if (originUri.length() == 0) {
			rq.historyBack("title을 입력해주세요.");
			return;
		}
		
		if (text.length() == 0) {
			rq.historyBack("text을 입력해주세요.");
			return;
		}
		
		ResultData ShortUri = shortService.ShortUri(memberId, originUri, text, shortNum);

		if(ShortUri == null) {
			rq.historyBack("잘못된 접근입니다.");
			return;
		}

		rq.replace(ShortUri.getMsg(), redirectUri);
	}

	private long getLastShortUriNum() {
		return shortService.getLastShortUriNum();
	}

}
