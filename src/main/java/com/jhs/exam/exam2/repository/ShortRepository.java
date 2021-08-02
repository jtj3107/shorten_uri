package com.jhs.exam.exam2.repository;

import com.jhs.exam.exam2.container.ContainerComponent;
import com.jhs.exam.exam2.dto.ShortUri;
import com.jhs.mysqliutil.MysqlUtil;
import com.jhs.mysqliutil.SecSql;

public class ShortRepository implements ContainerComponent{
	@Override
	public void init() {
	}

	public int ShortUri(int memberId, String shortUri, String originUri, String text, String blanklessText) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO shortUri");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", memberId = ?", memberId);
		sql.append(", shortCode = ?", shortUri);
		sql.append(", originUri = ?", originUri);
		sql.append(", `text` = ?", text);
		sql.append(", blanklessText = ?", blanklessText);
		sql.append(", accessCount = ?", 0);

		int id = MysqlUtil.insert(sql);
		
		return id;
	}

	public int insertShortUri(String shortCode, int id) {
		SecSql sql = new SecSql();
		sql.append("UPDATE shortUri");
		sql.append("SET shortCode = ?", shortCode);
		sql.append("WHERE id = ?", id);
		
		return MysqlUtil.update(sql);
	}

	public ShortUri getLastShortUri() {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM shortUri");
		sql.append("ORDER BY");
		sql.append("id DESC LIMIT 1");

		return MysqlUtil.selectRow(sql, ShortUri.class);
	}

	public com.jhs.exam.exam2.dto.ShortUri getShortByShortCode(String shortCode) {
		SecSql sql = new SecSql();
		sql.append("SELECT S.*");
		sql.append("FROM shortUri AS S");
		sql.append("WHERE S.shortCode = ?", shortCode);

		return MysqlUtil.selectRow(sql, ShortUri.class);
	}

}
