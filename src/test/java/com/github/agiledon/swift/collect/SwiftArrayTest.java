package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.base.BinaryActions;
import com.github.agiledon.swift.base.BinaryPredicates;
import com.github.agiledon.swift.mockobject.Printer;
import org.junit.Test;

import static com.github.agiledon.swift.collect.SwiftArray.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SwiftArrayTest extends SwiftArrayFixture {

    @Test
    public void should_apply_an_action_to_all_elements() {
        final Printer printer = mock(Printer.class);

        each(firstStringArray, new Actions<String>() {
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

        eachWithIndex(firstStringArray, new BinaryActions<String>() {
            @Override
            public void apply(String element, int index) {
                if (firstStringArray[index] == element) {
                    printer.print(String.valueOf(index));
                }
            }
        });

        for (int i = 0; i < firstStringArray.length; i++) {
            verify(printer, times(1)).print(String.valueOf(i));
        }
    }

    @Test
    public void should_replace_specific_element_with_given_value() {
        assertThat(firstStringArray[0], is("first line"));
        replace(firstStringArray, "first line", "new line");
        assertThat(firstStringArray[0], is("new line"));
    }

    @Test
    public void should_rotate_first_element_to_last_one() {
        assertThat(firstStringArray[0], is("first line"));
        rotate(firstStringArray);
        assertThat(firstStringArray[0], is("second line"));
        assertThat(firstStringArray[firstStringArray.length - 1], is("first line"));
    }

    @Test
    public void should_apply_an_action_to_all_elements_reversely() {
        final Printer printer = mock(Printer.class);

        reverseEach(firstStringArray, new Actions<String>() {
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

    @Test
    public void should_concat_two_arrays_to_one() {
        String[] result = concat(firstStringArray, secondStringArray);
        assertThat(result.length, is(11));
        assertThat(result[0], is("first line"));
        assertThat(result[8], is("seventh line"));
        assertThat(result[10], is("nine line"));
    }

    @Test
    public void should_return_first_element() {
        assertThat(head(firstStringArray), is("first line"));
    }
}
