package com.github.agiledon.swift.collect;

import java.util.List;

public class CountableList {
    static Integer sumForInt(List<Integer> list) {
        if (list.size() == 0) {
            return 0;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        return SwiftList.head(list) + SwiftList.sum(SwiftList.tail(list));
    }
}