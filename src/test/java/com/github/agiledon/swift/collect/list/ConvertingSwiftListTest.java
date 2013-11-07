package com.github.agiledon.swift.collect.list;

import org.junit.Test;

import static com.github.agiledon.swift.collect.list.SwiftList.copyToArray;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConvertingSwiftListTest extends SwiftListTestFixture {

    @Test
    public void should_copy_to_array_with_specific_elements_begin_with_the_second() {
        String[] result = copyToArray(stringList, 2, 5);
        assertThat(result.length, is(5));
        assertThat(result[0], is("second line"));
        assertThat(result[result.length - 1], is("fifth line"));
    }

    @Test
    public void should_copy_to_array_with_all_elements_from_the_second() {
        String[] result = copyToArray(stringList, 2, 10);
        assertThat(result.length, is(8));
        assertThat(result[0], is("second line"));
        assertThat(result[result.length - 1], is("seventh line"));
    }

    @Test
    public void should_copy_to_empty_array_if_begin_index_greater_than_length() {
        String[] result = copyToArray(stringList, 10, 5);
        assertThat(result.length, is(0));
    }
}
