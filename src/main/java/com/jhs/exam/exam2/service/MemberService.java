package com.jhs.exam.exam2.service;

import com.jhs.exam.exam2.container.Container;
import com.jhs.exam.exam2.container.ContainerComponent;
import com.jhs.exam.exam2.dto.Member;
import com.jhs.exam.exam2.repository.MemberRepository;

public class MemberService implements ContainerComponent{

	private MemberRepository memberRepository;

	@Override
	public void init() {
		memberRepository = Container.memberRepository;
	}
	
	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

}
