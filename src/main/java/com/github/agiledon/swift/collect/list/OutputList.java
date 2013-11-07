package com.github.agiledon.swift.collect.list;

import java.util.List;

class OutputList {
    private OutputList() {}

    public static <E> String mkString(List<E> list, String before, String between, String after) {
        StringBuilder result = new StringBuilder();
        result.append(before);
        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i));
            if (i < list.size() - 1) {
                result.append(between);
            }
        }
        result.append(after);
        return result.toString();
    }
}
