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
        String[] result = rotate(firstStringArray);
        assertThat(result[0], is("second line"));
        assertThat(result[result.length - 1], is("first line"));
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
    public void should_select_the_first_element() {
        assertThat(head(firstStringArray), is("first line"));
    }

    @Test
    public void should_select_all_elements_except_the_first() {
        String[] result = tail(firstStringArray);
        assertThat(result.length, is(firstStringArray.length - 1));
        assertThat(result[0], is("second line"));
        assertThat(result[result.length - 1], is("seventh line"));
    }

    @Test
    public void should_reverse_all_elements() {
        String[] result = reverse(firstStringArray);
        assertThat(result.length, is(firstStringArray.length));
        assertThat(result[0], is("seventh line"));
        assertThat(result[8], is("first line"));
    }

    @Test
    public void should_de_duplication_for_array() {
        String[] result = distinct(firstStringArray);

        assertThat(result.length, is(8));
        assertThat(result[6], is("sixth line"));
    }

    @Test
    public void should_return_true_if_array_contains_given_element() {
        boolean result = contains(firstStringArray, "second line");
        assertThat(result, is(true));
    }

    @Test
    public void should_return_false_if_array_does_not_contains_given_element() {
        boolean result = contains(firstStringArray, "no line");
        assertThat(result, is(false));
    }
}
