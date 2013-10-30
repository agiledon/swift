package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.base.BinaryPredicates;
import com.github.agiledon.swift.mockobject.Printer;
import org.junit.Before;
import org.junit.Test;

import static com.github.agiledon.swift.collect.SwiftArray.corresponds;
import static com.github.agiledon.swift.collect.SwiftArray.foreach;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SwiftArrayTest {
    private String[] stringArray;

    @Before
    public void setUp() throws Exception {
        stringArray = new String[]{
                "first line",
                "second line",
                "///",
                "third line",
                "fourth line",
                "fifth line",
                "///",
                "sixth line",
                "seventh line"
        };
    }

    @Test
    public void should_iterate_list_and_do_some_action() {
        final Printer printer = mock(Printer.class);

        foreach(stringArray, new Actions<String>() {
            @Override
            public void apply(String element) {
                printer.print(element);
            }
        });

        verify(printer, times(9)).print(anyString());
    }

    @Test
    public void should_correspond_two_arrays() {
        String[] first = new String[]{"Hello", "World"};
        String[] second = new String[]{"hello", "world"};

        boolean result = corresponds(first, second, new BinaryPredicates<String>() {
            @Override
            public boolean apply(String first, String second) {
                return first.equalsIgnoreCase(second);
            }
        });

        assertThat(result, is(true));
    }
}
