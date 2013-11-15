package com.github.agiledon.swift.collect.list;

import org.junit.Test;

import static com.github.agiledon.swift.collect.list.SwiftList.mkString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OutputSwiftListTest extends SwiftListTestFixture{

    @Test
    public void should_displays_all_elements_in_a_string_using_before_between_and_after_strings() {
        String result = mkString(stringList, "before ", " and ", " end.");
        assertThat(result, is("before first line and second line and " +
                "/// and third line and fourth line and fifth line and " +
                "/// and sixth line and seventh line end."));
    }

    @Test
    public void should_displays_all_elements_in_a_string_for_int_list_using_before_between_and_after_strings() {
        String result = mkString(intList, "before ", " and ", " end.");
        assertThat(result, is("before 0 and 1 and 2 and 3 and 4 end."));
    }

    @Test
    public void should_displays_all_elements_in_a_string() {
        String result = SwiftList.mkString(stringList);
        assertThat(result, is("first linesecond line///" +
                "third linefourth linefifth line///" +
                "sixth lineseventh line"));
    }


    @Test
    public void should_join_elements_into_a_string() {
        String result = SwiftList.join(stringList);
        assertThat(result, is("first linesecond line///" +
                "third linefourth linefifth line///" +
                "sixth lineseventh line"));
    }
}
