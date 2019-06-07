package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.util.LottoGenerator;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    public static final int PRICE = 1000;
    public static final int MIN_NUM = 1;
    public static final int MAX_NUM = 45;
    public static final int COMPOSITE_NUM = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(LottoGenerator generator) {
        List<LottoNumber> lottoNumbers = generator.generateNumber();
        checkValidLotto(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
        Collections.sort(this.lottoNumbers);
    }

    private void checkValidLotto(List<LottoNumber> lottoNumbers) {
        checkNumber(lottoNumbers);
        checkDuplicate(lottoNumbers);
    }

    private void checkNumber(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != Lotto.COMPOSITE_NUM) {
            throw new IllegalArgumentException("로또 숫자는 6개여야 합니다");
        }
    }

    private void checkDuplicate(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> lottoNumberSet = new HashSet<>(lottoNumbers);
        if (lottoNumberSet.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("중복된 로또입니다");
        }
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumbers -> lottoNumbers.isMatch(lottoNumber));
    }

    public int matchCount(Lotto lotto) {
        return (int) lottoNumbers.stream()
                .filter(lotto::contains)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lotto lotto = (Lotto) o;

        return lottoNumbers != null ? lottoNumbers.equals(lotto.lottoNumbers) : lotto.lottoNumbers == null;
    }

    @Override
    public int hashCode() {
        return lottoNumbers != null ? lottoNumbers.hashCode() : 0;
    }

    @Override
    public String toString() {
        String lotto = lottoNumbers.stream()
                .map(LottoNumber::getLottoNum)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(lotto);
        sb.append("]");

        return sb.toString();
    }
}
