package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Predicates;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.arrayList;
import static com.github.agiledon.swift.collect.SwiftList.exists;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QuerySwiftListTest {
    private List<String> stringList;

    @Before
    public void setUp() throws Exception {
        stringList = arrayList(
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
}
