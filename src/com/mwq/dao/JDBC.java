package com.mwq.dao;

import java.sql.*;

public class JDBC {

	//private static final String DRIVERCLASS = "com.microsoft.jdbc.sqlserver.SQLServerDriver";

	//private static final String URL = "jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=db_DrinkeryManage";

	//private static final String USERNAME = "sa";

	//private static final String PASSWORD = "";

	   // ����������
	private static final  String DRIVERCLASS = "com.mysql.jdbc.Driver";

    // URLָ��Ҫ���ʵ����ݿ���scutcs
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/db_drinkerymanage";

    // MySQL����ʱ���û���
	private static final  String USERNAME = "root"; 

    // MySQL����ʱ������
	private static final String PASSWORD = "root";
	
	
	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	static {// ͨ����̬�����������ݿ�����
		try {
			Class.forName(DRIVERCLASS).newInstance();// �������ݿ�����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {// �������ݿ����ӵķ���
		Connection conn = threadLocal.get();// ���߳��л�����ݿ�����
		if (conn == null) {// û�п��õ����ݿ�����
			try {
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);// �����µ����ݿ�����
				threadLocal.set(conn);// �����ݿ����ӱ��浽�߳���
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static boolean closeConnection() {// �ر����ݿ����ӵķ���
		boolean isClosed = true;
		Connection conn = threadLocal.get();// ���߳��л�����ݿ�����
		threadLocal.set(null);// ����߳��е����ݿ�����
		if (conn != null) {// ���ݿ����ӿ���
			try {
				conn.close();// �ر����ݿ�����
			} catch (SQLException e) {
				isClosed = false;
				e.printStackTrace();
			}
		}
		return isClosed;
	}

}