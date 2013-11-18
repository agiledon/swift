package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.base.BinaryActions;
import com.github.agiledon.swift.base.BinaryPredicates;

public final class SwiftArray {
    private SwiftArray() {
    }

    public static <E> void each(E[] array, Actions<E> action) {
        for (E element : array) {
            action.apply(element);
        }
    }

    public static <E> void reverseEach(E[] array, Actions<E> action) {
        for (int i = array.length - 1; i >= 0; i--) {
            action.apply(array[i]);
        }
    }

    public static <E> void eachWithIndex(E[] array, BinaryActions<E> action) {
        for (int i = array.length - 1; i >= 0; i--) {
            action.apply(array[i], i);
        }
    }

    public static <E> void replace(final E[] array, final E from, final E to) {
        eachWithIndex(array, new BinaryActions<E>() {
            @Override
            public void apply(E element, int index) {
                if (element == from) {
                    array[index] = to;
                }
            }
        });
    }

    //use head and tail in the future
    public static <E> void rotate(final E[] array) {
        E temp = array[0];
        for (int i = 0; i < array.length; i++) {
            if (i < array.length - 1) {
                array[i] = array[i + 1];
            } else {
                array[i] = temp;
            }
        }
    }

    public static <E> E head(E[] array) {
        return array[0];
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] concat(E[] firstArray, E[] secondArray) {
        E[] resultArray = (E[]) java.lang.reflect.Array.newInstance(
                firstArray[0].getClass(), firstArray.length + secondArray.length);

        for (int i = 0; i < firstArray.length; i++) {
            resultArray[i] = firstArray[i];
        }

        for (int j = 0; j < secondArray.length; j++) {
            resultArray[firstArray.length + j] = secondArray[j];
        }
        return resultArray;
    }

    public static <E> boolean corresponds(E[] source, E[] target, BinaryPredicates<E> predicates) {
        if (source.length != target.length) {
            return false;
        }
        for (int index = 0; index < source.length; index++) {
            if (!predicates.apply(source[index], target[index])) {
                return false;
            }
        }
        return true;
    }
}
