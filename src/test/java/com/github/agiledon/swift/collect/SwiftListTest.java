package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Functions;
import com.github.agiledon.swift.base.Predicates;
import com.github.agiledon.swift.exception.ElementNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

    @Test
    public void should_build_a_new_list_by_applying_a_function_to_all_elements() {
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
    public void should_build_a_new_string_list_by_applying_a_function_to_all_elements_of_int_list() {
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

    @Test
    public void should_select_first_three_elements() {
        List<String> result = take(stringList, 3);

        assertThat(result.size(), is(3));
        assertThat(result.get(0), is("first line"));
        assertThat(result.get(1), is("second line"));
        assertThat(result.get(2), is("///"));
    }

    @Test
    public void should_select_all_elements_if_given_length_greater_than_size() {
        List<String> result = take(stringList, 10);
        assertThat(result.size(), is(stringList.size()));
    }

    @Test
    public void should_select_last_five_elements() {
        List<String> result = takeRight(stringList, 5);
        assertThat(result.size(), is(5));
        assertThat(head(result), is("fourth line"));
        assertThat(last(result), is("seventh line"));
    }

    @Test
    public void should_select_zero_element() {
        List<String> result = takeRight(stringList, 0);
        assertThat(result.size(), is(0));
    }

    @Test
    public void should_select_all_elements_if_given_right_length_greater_than_size() {
        List<String> result = takeRight(stringList, 10);
        assertThat(result.size(), is(9));
    }

    @Test
    public void should_take_longest_prefix_of_elements_that_satisfy_a_predicate() {
        List<String> result = takeWhile(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.trim().equals("///");
            }
        });

        assertThat(result.size(), is(2));
        assertThat(head(result), is("first line"));
        assertThat(last(result), is("second line"));
    }

    @Test
    public void should_select_all_elements_except_first_three_ones() {
        List<String> result = drop(stringList, 3);

        assertThat(result.size(), is(6));
        assertThat(result.get(0), is("third line"));
        assertThat(result.get(1), is("fourth line"));
        assertThat(result.get(5), is("seventh line"));
    }

    @Test
    public void should_select_empty_elements_if_given_length_greater_than_size() {
        List<String> result = drop(stringList, 10);
        assertThat(result.size(), is(0));
    }

    @Test
    public void should_select_all_elements_except_last_five_ones() {
        List<String> result = dropRight(stringList, 5);
        assertThat(result.size(), is(4));
        assertThat(head(result), is("first line"));
        assertThat(last(result), is("third line"));
    }

    @Test
    public void should_drop_longest_prefix_of_elements_that_satisfy_a_predicate() {
        List<String> result = dropWhile(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.trim().equals("///");
            }
        });

        assertThat(result.size(), is(7));
        assertThat(head(result), is("///"));
        assertThat(last(result), is("seventh line"));
    }

    @Test
    public void should_drop_all_elements_that_not_satisfy_a_predicate() {
        List<String> result = dropWhile(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.trim().equals("bla bla");
            }
        });

        assertThat(result.size(), is(0));
    }

    @Test
    public void should_select_the_first_element() {
        String result = head(stringList);
        assertThat(result, is("first line"));
    }

    @Test(expected = ElementNotFoundException.class)
    public void should_throw_ElementNotFoundException_if_target_list_is_empty_for_head() {
        head(newArrayList());
    }

    @Test
    public void should_select_the_last_element() {
        String result = last(stringList);
        assertThat(result, is("seventh line"));
    }

    @Test(expected = ElementNotFoundException.class)
    public void should_throw_ElementNotFoundException_if_target_list_is_empty_for_last() {
        last(newArrayList());
    }

    @Test
    public void should_select_all_elements_except_the_first() {
        List<String> result = tail(stringList);
        assertThat(result.size(), is(8));
        assertThat(head(result), is("second line"));
        assertThat(last(result), is("seventh line"));
    }

    @Test
    public void should_select_empty_list_if_size_of_target_list_less_than_two_for_tail() {
        List<String> result = tail(newArrayList("first"));
        assertThat(result.size(), is(0));
    }

    @Test
    public void should_select_all_elements_except_the_last() {
        List<String> result = init(stringList);
        assertThat(result.size(), is(8));
        assertThat(head(result), is("first line"));
        assertThat(last(result), is("sixth line"));
    }

    @Test
    public void should_select_empty_list_if_size_of_target_list_less_than_two_for_init() {
        List<String> result = init(newArrayList("first"));
        assertThat(result.size(), is(0));
    }

    @Test
    public void should_select_all_elements_which_satisfy_a_predicate() {
        List<String> result = filter(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.contains("line");
            }
        });

        assertThat(result.size(), is(7));
        assertThat(head(result), is("first line"));
        assertThat(last(result), is("seventh line"));
    }

    @Test
    public void should_select_all_elements_which_do_not_satisfy_a_predicate() {
        List<String> result = filterNot(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.contains("line");
            }
        });

        assertThat(result.size(), is(2));
        assertThat(head(result), is("///"));
        assertThat(last(result), is("///"));
    }
}
