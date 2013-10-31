package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.mockobject.Printer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftIterable.foreach;
import static com.github.agiledon.swift.collect.SwiftList.newArrayList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class SwiftIterableTest {
    private List<String> stringList;

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

    }
    @Test
    public void should_iterate_list_and_do_some_action() {
        final Printer printer = mock(Printer.class);

        foreach(stringList, new Actions<String>() {
            @Override
            public void apply(String element) {
                printer.print(element);
            }
        });

        verify(printer, times(9)).print(anyString());
    }
}
