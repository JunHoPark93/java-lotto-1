package com.woowacourse.lotto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String server = "54.180.121.70"; // MySQL 서버 주소
    private static final String database = "mydb"; // MySQL DATABASE 이름
    private static final String userName = "jay"; //  MySQL 서버 아이디
    private static final String password = "O/pnjhp2%c!c"; // MySQL 서버 비밀번호
    private static Connection con;

    public static Connection getConnection() throws SQLException {
        // 로또 구매 ~ 결과 까지를 한 트랜잭션으로 간주했을 때 connection이 close 되는 경우를 정상적 종료, 에러 상황 두 가지로 한다.
        if (con == null || con.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
                e.printStackTrace();
            }

            try {
                con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", userName, password);
                System.out.println("정상적으로 연결되었습니다.");
            } catch (SQLException e) {
                System.err.println("연결 오류:" + e.getMessage());
                e.printStackTrace();
            }

            con.setAutoCommit(false);
            return con;
        }

        return con;
    }
}
