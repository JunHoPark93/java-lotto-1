package com.woowacourse.lotto.service;

import com.woowacourse.lotto.db.dao.RoundDao;

import java.sql.SQLException;

public class PlayWebService {
    public void addRound(int round) throws SQLException {
        RoundDao.addRound(round);
    }

    public int round() throws SQLException {
        return RoundDao.selectRound();
    }
}
