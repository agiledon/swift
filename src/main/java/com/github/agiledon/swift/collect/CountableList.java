package com.github.agiledon.swift.collect;

import java.util.List;

public class CountableList {
    static <E extends Number> double sum(List<E> list) {
        if (list.size() == 0) {
            return 0d;
        }
        if (list.size() == 1) {
            return list.get(0).doubleValue();
        }
        return SwiftList.head(list).doubleValue() + sum(SwiftList.tail(list));
    }

}