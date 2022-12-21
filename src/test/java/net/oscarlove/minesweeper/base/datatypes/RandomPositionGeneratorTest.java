package net.oscarlove.minesweeper.base.datatypes;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class RandomPositionGeneratorTest {

    @Test
    public void returns_nonNull() {
        assertThat(build(new Dimension(1, 2)).generate()).isNotNull();
    }

    @Test
    public void returns_correct_number_of_positions() {
        for (int i = 0; i < 1000; i++) {
            assertThat(build(new Dimension(20, 20)).withMinimum(2).withMaximum(5).generate().size()).isBetween(2, 5);
            assertThat(build(new Dimension(20, 20)).withMinimum(100).withMaximum(200).generate().size()).isBetween(100, 200);
        }
    }

    @Test
    public void returns_valid_positions() {
        for (int i = 0; i < 1000; i++) {
            build(new Dimension(20, 20)).withMinimum(100).withMaximum(200).generate().forEach(position -> {
                assertThat(position.row()).isBetween(0, 19);
                assertThat(position.column()).isBetween(0, 19);
            });
        }
    }


    private RandomPositionGenerator build(Dimension dimension) {
        return RandomPositionGenerator.create(dimension);
    }
}