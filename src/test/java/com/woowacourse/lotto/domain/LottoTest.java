package com.woowacourse.lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LottoTest {
    @Test
    void 정상_로또_초기화() {
        IntendedLottoGenerator intendedLottoGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThatCode(() -> new Lotto(intendedLottoGenerator)).doesNotThrowAnyException();
    }

    @Test
    void 비정상_로또_중복숫자() {
        IntendedLottoGenerator intendedLottoGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 5));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(intendedLottoGenerator))
                .withMessage("로또 생성 에러");

    }

    @Test
    void 비정상_로또_6개가아닌_숫자() {
        IntendedLottoGenerator intendedLottoGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(intendedLottoGenerator))
                .withMessage("로또 생성 에러");
    }
}
