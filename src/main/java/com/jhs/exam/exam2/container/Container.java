package com.jhs.exam.exam2.container;

import java.util.ArrayList;
import java.util.List;

import com.jhs.exam.exam2.http.controller.Controller;
import com.jhs.exam.exam2.http.controller.UsrHomeController;
import com.jhs.exam.exam2.http.controller.UsrMemberController;
import com.jhs.exam.exam2.http.controller.UsrMoveController;
import com.jhs.exam.exam2.http.controller.UsrShortController;
import com.jhs.exam.exam2.interceptor.BeforeActionInterceptor;
import com.jhs.exam.exam2.interceptor.NeedLoginInterceptor;
import com.jhs.exam.exam2.interceptor.NeedLogoutInterceptor;
import com.jhs.exam.exam2.repository.MemberRepository;
import com.jhs.exam.exam2.repository.ShortRepository;
import com.jhs.exam.exam2.service.MemberService;
import com.jhs.exam.exam2.service.ShortService;

public class Container {
	private static List<ContainerComponent> containerComponents;

	public static MemberService memberService;
	public static ShortService shortService;
	
	public static MemberRepository memberRepository;
	public static ShortRepository shortRepository;

	public static BeforeActionInterceptor beforeActionInterceptor;
	public static NeedLoginInterceptor needLoginInterceptor;
	public static NeedLogoutInterceptor needLogoutInterceptor;

	public static UsrHomeController usrHomeController;
	public static UsrMemberController usrMemberController;
	public static UsrShortController usrShortController;
	public static UsrMoveController usrMoveController;

	public static void init() {
		containerComponents = new ArrayList<>();

		memberService = addContainerComponent(new MemberService());
		shortService = addContainerComponent(new ShortService());

		memberRepository = addContainerComponent(new MemberRepository());
		shortRepository = addContainerComponent(new ShortRepository());

		beforeActionInterceptor = addContainerComponent(new BeforeActionInterceptor());
		needLoginInterceptor = addContainerComponent(new NeedLoginInterceptor());
		needLogoutInterceptor = addContainerComponent(new NeedLogoutInterceptor());

		usrHomeController = addContainerComponent(new UsrHomeController());
		usrMemberController = addContainerComponent(new UsrMemberController());
		usrShortController = addContainerComponent(new UsrShortController());
		usrMoveController = addContainerComponent(new UsrMoveController());

		// 객체 초기화
		for (ContainerComponent containerComponent : containerComponents) {
			containerComponent.init();
		}
	}

	private static <T> T addContainerComponent(ContainerComponent containerComponent) {
		containerComponents.add(containerComponent);

		return (T) containerComponent;
	}
}