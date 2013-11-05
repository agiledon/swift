package com.github.agiledon.swift.collect;

import org.junit.Test;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.arrayList;
import static com.github.agiledon.swift.collect.SwiftList.average;
import static com.github.agiledon.swift.collect.SwiftList.sum;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CountableSwiftListTest {
    @Test
    public void should_sum_for_integer_list() {
        List<Integer> intList = arrayList(
                0,
                1,
                2,
                3,
                4
        );

        assertThat(sum(intList), is(10.0));
    }

    @Test
    public void should_sum_for_double_list() {
        List<Double> doubleList = arrayList(
                0d,
                1.0,
                2.0,
                3.0,
                4.0
        );

        assertThat(sum(doubleList), is(10.0));
    }

    @Test
    public void should_average_for_double_list() {
        List<Double> doubleList = arrayList(
                0d,
                1.0,
                2.0,
                3.0,
                4.0
        );

        assertThat(average(doubleList), is(2.0));
    }
}
