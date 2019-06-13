package com.woowacourse.lotto.db.dao;

import com.woowacourse.lotto.db.ConnectionFactory;

import java.sql.*;

public class RoundDao {
    public static int selectRound() throws SQLException {
        String query = "SELECT MAX(id) FROM ROUND;";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        if (!resultSet.next()) {
            throw new RuntimeException("no round");
        }

        String lastRound = resultSet.getString("max(id)");
        return Integer.valueOf(lastRound);
    }

    public static void addRound(int round) throws SQLException {
        String query = "INSERT INTO ROUND(id) VALUES (?)";
        Connection con = ConnectionFactory.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, String.valueOf(round + 1));
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("RoundDao rollback!" + e.getMessage());
            con.rollback();
        }
    }
}
