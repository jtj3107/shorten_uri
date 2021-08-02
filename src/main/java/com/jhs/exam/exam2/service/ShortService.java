package com.jhs.exam.exam2.service;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.container.ContainerComponent;
import com.jhs.exam.exam2.dto.ResultData;
import com.jhs.exam.exam2.dto.ShortUri;
import com.jhs.exam.exam2.repository.ShortRepository;
import com.jhs.exam.exam2.util.Base62;

public class ShortService implements ContainerComponent{
	private ShortRepository shortRepository;

	@Override
	public void init() {
		shortRepository = Container.shortRepository;
	}

	public ResultData ShortUri(int memberId, String originUri, String text, long shortNum) {
		String blanklessText = text.trim();
		String shortUri = Base62.encoding(shortNum);

		int id = shortRepository.ShortUri(memberId, shortUri, originUri, text, blanklessText);

		return ResultData.from("S-1", "단축 완료", "shortUri", shortUri);
	}

	public long getLastShortUriNum() {
		ShortUri lastShortUri = shortRepository.getLastShortUri();
		
		if(lastShortUri == null) {
			return 106545;
		}
		
		String lastShortUriCode = lastShortUri.getShortCode();
		
		long shortUriLastNum = Base62.decoding(lastShortUriCode);
		return shortUriLastNum + 1;
	}

	public ShortUri getShortByShortCode(String shortCode) {
		return shortRepository.getShortByShortCode(shortCode);
	}

}
