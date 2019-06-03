package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.util.IntendedLottoGenerator;
import com.woowacourse.lotto.util.LottoGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoBuyListTest {
    @Test
    void 로또_당첨_결과_반환() {
        // given
        LottoGenerator lottoGenerator1 = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 7));
        LottoGenerator lottoGenerator2 = new IntendedLottoGenerator(Arrays.asList(4, 5, 6, 7, 8, 9));
        LottoGenerator winningGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 6));

        Lotto lastWeekLotto = new Lotto(winningGenerator);
        List<Lotto> lottoList = Arrays.asList(new Lotto(lottoGenerator1), new Lotto(lottoGenerator2));

        LottoBuyList lottoBuyList = new LottoBuyList(lottoList);
        WinningLotto winningLotto = new WinningLotto(lastWeekLotto, new LottoNumber(7));

        Map<Rank, Integer> result = new HashMap<>();
        result.put(Rank.FIRST, 0);
        result.put(Rank.SECOND, 1);
        result.put(Rank.THIRD, 0);
        result.put(Rank.FOURTH, 0);
        result.put(Rank.FIFTH, 1);
        result.put(Rank.NONE, 0);
        LottoResult expectedResult = new LottoResult(result);

        // when
        LottoResult lottoResult = winningLotto.getResult(lottoBuyList);

        // then
        assertThat(lottoResult).isEqualTo(expectedResult);
    }
}