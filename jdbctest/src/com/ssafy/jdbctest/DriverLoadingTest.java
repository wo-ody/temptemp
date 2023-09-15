package com.ssafy.jdbctest;

public class DriverLoadingTest {
	public DriverLoadingTest() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loading Success !!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new DriverLoadingTest();
	}
}
