package com.github.agiledon.swift.collect;

import java.util.List;

import static com.github.agiledon.swift.collect.SwiftList.take;

final class ConvertingList {
    private ConvertingList() {}

    @SuppressWarnings("unchecked")
    static <E> E[] copyToArray(List<E> list, int start, int length) {
        List<E> partList = take(list, start, length);
        E[] result = (E[]) java.lang.reflect.Array.newInstance(
                list.get(0).getClass(), partList.size());

        for (int i = 0; i < partList.size(); i++) {
            result[i] = partList.get(i);
        }

        return result;
    }
}
