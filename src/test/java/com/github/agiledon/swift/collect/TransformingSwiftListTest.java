package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Functions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.map;
import static com.github.agiledon.swift.collect.SwiftList.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TransformingSwiftListTest {

    private ArrayList<Integer> intList;

    @Before
    public void setUp() throws Exception {
        intList = newArrayList(
                0,
                1,
                2,
                3,
                4
        );
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
}
