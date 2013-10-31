package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Predicates;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PartitionSwiftListTest {
    private List<String> stringList;

    @Before
    public void setUp() throws Exception {
        stringList = newArrayList(
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
    public void should_split_list_to_multi_parts_according_to_a_predicate() {
        List<List<String>> result = split(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.trim().startsWith("///");
            }
        });

        assertThat(result.size(), is(3));
        assertThat(head(head(result)), is("first line"));
        assertThat(last(head(result)), is("second line"));
        assertThat(head(last(result)), is("sixth line"));
        assertThat(last(last(result)), is("seventh line"));
    }

    @Test
    public void should_partition_list_in_two_lists_according_to_a_predicate() {
        List<List<String>> result = partition(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.trim().startsWith("///");
            }
        });

        assertThat(result.size(), is(2));

        List<String> firstPart = head(result);
        assertThat(firstPart.size(), is(2));
        assertThat(head(firstPart), is("first line"));
        assertThat(last(firstPart), is("second line"));

        List<String> secondPart = last(result);
        assertThat(secondPart.size(), is(7));
        assertThat(head(secondPart), is("///"));
        assertThat(last(secondPart), is("seventh line"));
    }
}
