package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.mockobject.Printer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftIterable.foreach;
import static com.github.agiledon.swift.collect.list.SwiftList.arrayList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class SwiftIterableTest {
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
    public void should_apply_an_action_to_all_elements() {
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
