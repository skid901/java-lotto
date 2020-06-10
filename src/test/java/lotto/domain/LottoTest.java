package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @DisplayName("중복되지 않은 숫자 6개로 Lotto 생성하는데 성공")
    @ParameterizedTest
    @MethodSource("source_generateByManual_notDuplicatedNumbers_shouldSucceed")
    public void generateByManual_notDuplicatedNumbers_shouldSucceed(List<Integer> param, Lotto expected) {
        Lotto result = new Lotto(param);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> source_generateByManual_notDuplicatedNumbers_shouldSucceed() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6}), new Lotto(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6}))),
                Arguments.of(Arrays.asList(new Integer[]{40, 41, 42, 43, 44, 45}), new Lotto(Arrays.asList(new Integer[]{40, 41, 42, 43, 44, 45}))));
    }

    @DisplayName("중복된 숫자 6개로 Lotto 생성 시에 throws IllegalArgumentException")
    @ParameterizedTest
    @MethodSource("source_generateByManual_duplicatedNumbers_shouldFail")
    public void create_generateByManual_create_duplicatedNumbers_shouldFail(List<Integer> param) {
        assertThatThrownBy(() -> {
            new Lotto(param);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> source_generateByManual_duplicatedNumbers_shouldFail() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Integer[]{1, 1, 3, 4, 5, 6})),
                Arguments.of(Arrays.asList(new Integer[]{40, 41, 42, 43, 45, 45})));
    }

    @DisplayName("1 ~ 45 바깥의 수로 Lotto 생성시에 throw IllegalArgumentException")
    @ParameterizedTest
    @MethodSource("source_generateByManual_invalidParameters_shouldFail")
    public void generateByManual_invalidParameters_shouldFail(List<Integer> param) {
        assertThatThrownBy(() -> {
            new Lotto(param);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> source_generateByManual_invalidParameters_shouldFail() {
        return Stream.of(
                Arguments.of(Arrays.asList(0, 1, 2, 3, 4, 5)),
                Arguments.of(Arrays.asList(41, 42, 43, 44, 45, 46)));
    }

    @DisplayName("같은 번호를 가진 Lotto 객체들은 같다")
    @ParameterizedTest
    @MethodSource("source_equals_sameNumbers_shouldTrue")
    public void equals_sameNumbers_shouldTrue(List<Integer> lottoNumbers1, List<Integer> lottoNumbers2) {
        Lotto lotto1 = new Lotto(lottoNumbers1);
        Lotto lotto2 = new Lotto(lottoNumbers2);
        assertThat(lotto1.equals(lotto2)).isTrue();
    }

    public static Stream<Arguments> source_equals_sameNumbers_shouldTrue() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6}), Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6})),
                Arguments.of(Arrays.asList(new Integer[]{40, 41, 42, 43, 44, 45}), Arrays.asList(new Integer[]{40, 41, 42, 43, 44, 45})));
    }
}