package com.github.agiledon.swift.collect;

import org.junit.Before;

public class SwiftArrayFixture {
    protected String[] firstStringArray;
    protected String[] secondStringArray;

    @Before
    public void setUp() throws Exception {
        firstStringArray = new String[]{
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

        secondStringArray = new String[]{
                "eight line",
                "nine line"
        };
    }
}
