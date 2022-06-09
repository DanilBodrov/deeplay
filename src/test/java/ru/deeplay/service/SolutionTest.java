package ru.deeplay.service;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void whenPersonNotFound() {
        int[][] field = Solution.conversionToField("sstpstwwtptwwstp", "dwarf");
        int[][] expectation = {
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1},
        };
        assertArrayEquals(expectation, field);
    }

    @Test
    public void whenPersonFoundIsHuman() {
        int[][] field = Solution.conversionToField("sstpstwwtptwwstp", "human");
        int[][] expectation = {
                {5,5,3,1},
                {5,3,2,2},
                {3,1,3,2},
                {2,5,3,1},
        };
        assertArrayEquals(expectation, field);
    }

    @Test
    public void whenHumanAndResultIs14() {
        int[][] field = Solution.conversionToField("sstpstwwtptwwstp", "human");
        assertThat(Solution.getResult(field), is(14));
    }

    @Test
    public void whenPersonFoundIsSwamper() {
        int[][] field = Solution.conversionToField("swtpswtpswtpswtp", "Swamper");
        int[][] expectation = {
                {2,2,5,2},
                {2,2,5,2},
                {2,2,5,2},
                {2,2,5,2},
        };
        assertArrayEquals(expectation, field);
    }

    @Test
    public void whenSwamperAndResultIs() {
        int[][] field = Solution.conversionToField("swtpswtpswtpswtp", "swamper");
        assertThat(Solution.getResult(field), is(15));
    }

    @Test
    public void whenPersonFoundIsWoodman() {
        int[][] field = Solution.conversionToField("sssswwwwttttpppp", "woodman");
        int[][] expectation = {
                {3,3,3,3},
                {3,3,3,3},
                {2,2,2,2},
                {2,2,2,2},
        };
        assertArrayEquals(expectation, field);
    }

    @Test
    public void whenWoodmanAndResultIs() {
        int[][] field = Solution.conversionToField("sssswwwwttttpppp", "woodman");
        assertThat(Solution.getResult(field), is(13));
    }

    @Test
    public void whenPersonFoundButFieldNotFound() {
        int[][] field = Solution.conversionToField("srsswwuwtqttpppm", "human");
        int[][] expectation = {
                {5,1,5,5},
                {2,2,1,2},
                {3,1,3,3},
                {1,1,1,1},
        };
        assertArrayEquals(expectation, field);
    }
}