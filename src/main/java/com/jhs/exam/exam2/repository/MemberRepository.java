package com.jhs.exam.exam2.repository;

import com.jhs.exam.exam2.container.ContainerComponent;
import com.jhs.exam.exam2.dto.Member;
import com.jhs.mysqliutil.MysqlUtil;
import com.jhs.mysqliutil.SecSql;

public class MemberRepository implements ContainerComponent{
	@Override
	public void init() {
	}

	public Member getMemberByLoginId(String loginId) {
		// 로그인아이디로 해당 member가 DB에 존재하는지 확인
		SecSql sql = new SecSql();
		sql.append("SELECT M.*");
		sql.append("FROM member AS M");
		sql.append("WHERE M.loginId = ?", loginId);

		return MysqlUtil.selectRow(sql, Member.class);
	}

}