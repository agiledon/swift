package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.base.Predicates;
import com.github.agiledon.swift.mockobject.Printer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.foreach;
import static com.github.agiledon.swift.collect.SwiftList.partition;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SwiftListTest {

    private List<String> target;

    @Before
    public void setUp() throws Exception {
        target = newArrayList(
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
    public void should_split_to_three_partitions_by_given_predicates() {

        List<List<String>> partitions = partition(target, new Predicates<String>() {
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

        foreach(target, new Actions<String>() {
            @Override
            public void apply(String element) {
                printer.print(element);
            }
        });

        verify(printer, times(9)).print(anyString());
    }
}
