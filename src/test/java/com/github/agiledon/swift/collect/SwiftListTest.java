package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.base.Functions;
import com.github.agiledon.swift.base.Predicates;
import com.github.agiledon.swift.mockobject.Printer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.foreach;
import static com.github.agiledon.swift.collect.SwiftList.map;
import static com.github.agiledon.swift.collect.SwiftList.partition;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SwiftListTest {

    private List<String> stringList;
    private ArrayList<Integer> intList;

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

        intList = newArrayList(
                0,
                1,
                2,
                3,
                4
        );
    }

    @Test
    public void should_split_to_three_partitions_by_given_predicates() {

        List<List<String>> partitions = partition(stringList, new Predicates<String>() {
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

    @Test
    public void should_iterate_list_and_do_some_action() {
        final Printer printer = mock(Printer.class);

        foreach(stringList, new Actions<String>() {
            @Override
            public void apply(String element) {
                printer.print(element);
            }
        });

        verify(printer, times(9)).print(anyString());
    }

    @Test
    public void should_map_to_another_int_list() {
        List<Integer> multiByTwo = map(intList, new Functions<Integer, Integer>() {
            @Override
            public Integer apply(Integer target) {
                return target * 2;
            }
        });

        assertThat(multiByTwo.size(), is(5));

        for (int index = 0; index < multiByTwo.size(); index++) {
            assertThat(multiByTwo.get(index), is(index * 2));
        }
    }

    @Test
    public void should_map_to_string_list() {
        List<String> multiByTwo = map(intList, new Functions<Integer, String>() {
            @Override
            public String apply(Integer target) {
                return target.toString();
            }
        });

        assertThat(multiByTwo.size(), is(5));

        for (int index = 0; index < multiByTwo.size(); index++) {
            assertThat(multiByTwo.get(index), is(String.valueOf(index)));
        }
    }
}
