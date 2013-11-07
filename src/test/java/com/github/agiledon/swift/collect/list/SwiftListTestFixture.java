package com.github.agiledon.swift.collect.list;

import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static com.github.agiledon.swift.collect.list.SwiftList.arrayList;

public class SwiftListTestFixture {
    protected List<String> stringList;

    protected ArrayList<Integer> intList;

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

        intList = arrayList(
                0,
                1,
                2,
                3,
                4
        );
    }
}
