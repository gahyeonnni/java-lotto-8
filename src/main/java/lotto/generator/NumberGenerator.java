package lotto.generator;

import java.util.List;

@FunctionalInterface
public interface NumberGenerator {
    List<Integer> generate(int min, int max, int count);
}
