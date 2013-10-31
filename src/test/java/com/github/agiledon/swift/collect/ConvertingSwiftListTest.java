package com.github.agiledon.swift.collect;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConvertingSwiftListTest {
    private List<String> stringList;

    @Before
    public void setUp() throws Exception {
        stringList = arrayList(
                "first line",
                "second line",
                "///",
                "third line",
                "fourth line",
                "fifth line",
                "///",
                "sixth line",
                "seventh line"
        );
    }

    @Test
    @Ignore
    public void should_copy_to_array_with_specific_elements_begin_with_second_element() {
        String[] result = copyToArray(stringList, 2, 5);
        assertThat(result.length, is(5));
        assertThat(result[0], is("second line"));
        assertThat(result[result.length - 1], is("fifth line"));
    }
}
