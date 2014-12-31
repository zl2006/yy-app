package org.yy.user.dao;

/*
 * 文 件 名:  InitData.java
 * 版    权:  segi Technologies Co., Ltd. Copyright 1993-2012,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  agan
 * 修改时间:  2012-9-4
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;


/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author agan
 * @version [版本号, 2012-9-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class InitData {

	private SqlSession sqlSession;

	// 用户表
	private static final String CREATE_TABLE_USER = "create table USER  ("
			+ "USER_ID      NUMERIC   not null," + "USERNAME     VARCHAR2(32),"
			+ "PASSWORD     VARCHAR2(32)," + "EMAIL        VARCHAR2(64),"
			+ "TEL          VARCHAR2(16)," + "LOGIN_ID     VARCHAR2(32),"
			+ "SEX          VARCHAR2(8)," + "STATUS       VARCHAR2(8),"
			+ "ADDRESS      VARCHAR2(256)," + "BIRTHDAY     TIMESTAMP,"
			+ "LATNNO       VARCHAR2(16)," + "LAST_LOGIN_TIME       TIMESTAMP,"
			+ "CREATE_TIME  TIMESTAMP," + "UPDATE_TIME  TIMESTAMP,"
			+ "constraint PK_USER primary key (USER_ID)" + ")";
	private static final String CREATE_SEQ_USER = "create sequence SEQ_USER";
	private static final String INSERT_USER = "INSERT INTO USER(    USER_ID,    USERNAME,    PASSWORD,    EMAIL,    TEL,    LOGIN_ID, SEX, STATUS, ADDRESS, BIRTHDAY, LATNNO, LAST_LOGIN_TIME, CREATE_TIME,    UPDATE_TIME)   VALUES(    ?,    ?,    ?,    ?,    ?,    ?,  ?, ?, ?,? , ?, ?, sysdate,    sysdate)";


	public InitData(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public void init() {
		initSeq();
		initTable();
		initData();
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param sqlmapClient
	 * @see [类、类#方法、类#成员]
	 */
	public void initTable() {
		try {
			Connection conn = sqlSession.getConnection();
			conn.setAutoCommit(true);
			Statement stm = conn.createStatement();
			stm.execute(CREATE_TABLE_USER);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param sqlmapClient
	 * @see [类、类#方法、类#成员]
	 */
	public void initSeq() {
		try {
			Connection conn = sqlSession.getConnection();
			conn.setAutoCommit(true);
			Statement stm = conn.createStatement();
			stm.execute(CREATE_SEQ_USER);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param sqlmapClient
	 * @see [类、类#方法、类#成员]
	 */
	public void initView() {

	}

	/**
	 * <一句话功能简述> <功能详细描述>
	 * 
	 * @param sqlmapClient
	 * @see [类、类#方法、类#成员]
	 */
	public void initData() {
		Connection conn = null;
		PreparedStatement stm = null;

		try {
			conn = sqlSession.getConnection();
			conn.setAutoCommit(true);
			stm = conn.prepareStatement(INSERT_USER);
			stm.setLong(1, 10000L);
			stm.setString(2, "zhouliang");
			stm.setString(3, "password");
			stm.setString(4, "zhouliang1@segi.com");
			stm.setString(5, "15818774641");
			stm.setString(6, "D3349");
			stm.setString(7, "男");
			stm.setString(8, "enable");
			stm.setString(9, "湖南岳阳AAAAAAAAAA");
			stm.setDate(10, new java.sql.Date(new java.util.Date().getTime()));
			stm.setString(11, "湖南岳阳");
			stm.setString(12, null);
			stm.execute();

			stm = conn.prepareStatement(INSERT_USER);
			stm.setLong(1, 10001L);
			stm.setString(2, "zhouyang");
			stm.setString(3, "password");
			stm.setString(4, "zhouyang@segi.com");
			stm.setString(5, "1302342342342");
			stm.setString(6, "D3300");
			stm.setString(7, "男");
			stm.setString(8, "enable");
			stm.setString(9, "AAAAAAAAAA");
			stm.setDate(10, new java.sql.Date(new java.util.Date().getTime()));
			stm.setString(11, "湖南岳阳天油压");
			stm.setString(12, null);
			stm.execute();

			stm = conn.prepareStatement(INSERT_USER);
			stm.setLong(1, 10101L);
			stm.setString(2, "zhouyang");
			stm.setString(3, "password");
			stm.setString(4, "zhouyang@segi.com");
			stm.setString(5, "1302342342342");
			stm.setString(6, "login1");
			stm.setString(7, "男");
			stm.setString(8, "enable");
			stm.setString(9, "AAAAAAAAAA");
			stm.setDate(10, new java.sql.Date(new java.util.Date().getTime()));
			stm.setString(11, "湖南岳阳天油压");
			stm.setString(12, null);
			stm.execute();

			stm = conn.prepareStatement(INSERT_USER);
			stm.setLong(1, 10102L);
			stm.setString(2, "zhouyang");
			stm.setString(3, "password");
			stm.setString(4, "zhouyang@segi.com");
			stm.setString(5, "1302342342342");
			stm.setString(6, "login2");
			stm.setString(7, "男");
			stm.setString(8, "enable");
			stm.setString(9, "AAAAAAAAAA");
			stm.setDate(10, new java.sql.Date(new java.util.Date().getTime()));
			stm.setString(11, "湖南岳阳天油压");
			stm.setString(12, null);
			stm.execute();

			stm = conn.prepareStatement(INSERT_USER);
			stm.setLong(1, 10202L);
			stm.setString(2, "zhouyang");
			stm.setString(3, "password");
			stm.setString(4, "zhouyang@segi.com");
			stm.setString(5, "1302342342342");
			stm.setString(6, "login21");
			stm.setString(7, "男");
			stm.setString(8, "enable");
			stm.setString(9, "AAAAAAAAAA");
			stm.setDate(10, new java.sql.Date(new java.util.Date().getTime()));
			stm.setString(11, "湖南岳阳天油压");
			stm.setString(12, null);
			stm.execute();

			stm = conn.prepareStatement(INSERT_USER);
			stm.setLong(1, 10110L);
			stm.setString(2, "zhouyang");
			stm.setString(3, "password");
			stm.setString(4, "zhouyang@segi.com");
			stm.setString(5, "1302342342342");
			stm.setString(6, "login10");
			stm.setString(7, "男");
			stm.setString(8, "enable");
			stm.setString(9, "AAAAAAAAAA");
			stm.setDate(10, new java.sql.Date(new java.util.Date().getTime()));
			stm.setString(11, "湖南岳阳天油压");
			stm.setString(12, null);
			stm.execute();
			stm = conn.prepareStatement(INSERT_USER);
			stm.setLong(1, 10112L);
			stm.setString(2, "zhouyang");
			stm.setString(3, "password");
			stm.setString(4, "zhouyang@segi.com");
			stm.setString(5, "1302342342342");
			stm.setString(6, "login11");
			stm.setString(7, "男");
			stm.setString(8, "enable");
			stm.setString(9, "AAAAAAAAAA");
			stm.setDate(10, new java.sql.Date(new java.util.Date().getTime()));
			stm.setString(11, "湖南岳阳天油压");
			stm.setString(12, null);
			stm.execute();
			conn.commit();
			stm.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
