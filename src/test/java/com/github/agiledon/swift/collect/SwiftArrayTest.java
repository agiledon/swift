package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.base.BinaryActions;
import com.github.agiledon.swift.base.BinaryPredicates;
import com.github.agiledon.swift.mockobject.Printer;
import org.junit.Before;
import org.junit.Test;

import static com.github.agiledon.swift.collect.SwiftArray.*;
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
    public void should_apply_an_action_to_all_elements() {
        final Printer printer = mock(Printer.class);

        each(stringArray, new Actions<String>() {
            @Override
            public void apply(String element) {
                printer.print(element);
            }
        });

        verify(printer, times(9)).print(anyString());
    }

    @Test
    public void should_apply_an_action_with_index_to_all_elements() {
        final Printer printer = mock(Printer.class);

        eachWithIndex(stringArray, new BinaryActions<String>() {
            @Override
            public void apply(String element, int index) {
                if (stringArray[index] == element) {
                    printer.print(String.valueOf(index));
                }
            }
        });

        for (int i = 0; i < stringArray.length; i++) {
            verify(printer, times(1)).print(String.valueOf(i));
        }
    }

    @Test
    public void should_replace_specific_element_with_given_value() {
        assertThat(stringArray[0], is("first line"));
        replace(stringArray, "first line", "new line");
        assertThat(stringArray[0], is("new line"));
    }

    @Test
    public void should_rotate_first_element_to_last_one() {
        assertThat(stringArray[0], is("first line"));
        rotate(stringArray);
        assertThat(stringArray[0], is("second line"));
        assertThat(stringArray[stringArray.length - 1], is("first line"));
    }

    @Test
    public void should_apply_an_action_to_all_elements_reversely() {
        final Printer printer = mock(Printer.class);

        reverseEach(stringArray, new Actions<String>() {
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
