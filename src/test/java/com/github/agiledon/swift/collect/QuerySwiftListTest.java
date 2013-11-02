package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Predicates;
import com.github.agiledon.swift.exception.ElementNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QuerySwiftListTest {
    private List<String> stringList;

    @Before
    public void setUp() throws Exception {
        stringList = arrayList(
                "first line",
                "second line",
                "/// line",
                "third line",
                "fourth line",
                "fifth line",
                "/// line",
                "sixth line",
                "seventh line"
        );
    }

    @Test
    public void should_be_true_if_any_element_satisfy_a_predicate() {
        boolean existed = exists(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.contains("third");
            }
        });

        assertThat(existed, is(true));
    }

    @Test
    public void should_be_false_if_no_element_satisfy_a_predicate() {
        boolean existed = exists(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.contains("not existed");
            }
        });

        assertThat(existed, is(false));
    }

    @Test
    public void should_find_the_first_element_that_satisfies_a_predicate() {
        String result = find(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.contains("third");
            }
        });

        assertThat(result, is("third line"));
    }

    @Test(expected = ElementNotFoundException.class)
    public void should_throw_Element_Not_Found_Exception_if_not_satisfy_a_predicates() {
        find(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.contains("not existed");
            }
        });
    }

    @Test
    public void should_return_true_if_all_elements_satisfy_a_predicates() {
        boolean result = forall(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.contains("line");
            }
        });

        assertThat(result, is(true));
    }

    @Test
    public void should_return_false_if_any_element_not_satisfies_a_predicates() {
        boolean result = forall(stringList, new Predicates<String>() {
            @Override
            public boolean apply(String element) {
                return element.contains("///");
            }
        });

        assertThat(result, is(false));
    }
}
