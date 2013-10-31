package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Predicates;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.newArrayList;
import static com.github.agiledon.swift.collect.SwiftList.split;
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
        List<List<String>> partitions = split(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.trim().startsWith("///");
            }
        });

        assertThat(partitions.size(), is(3));
        assertThat(partitions.get(0).get(0), is("first line"));
        assertThat(partitions.get(1).get(0), is("third line"));
        assertThat(partitions.get(2).get(0), is("sixth line"));
    }
}
