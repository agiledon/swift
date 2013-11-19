package com.github.agiledon.swift.collect;

import com.github.agiledon.swift.base.Actions;
import com.github.agiledon.swift.base.BinaryActions;
import com.github.agiledon.swift.base.BinaryPredicates;

import java.lang.reflect.Array;

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

    @SuppressWarnings("unchecked")
    public static <E> E[] rotate(final E[] array) {
        return concat(tail(array), arrayWith(head(array)));
    }

    public static <E> E head(E[] array) {
        return array[0];
    }

    public static <E> E[] tail(E[] array) {
        if (array.length < 2) {
            return newArray(array, 0);
        }
        E[] result = newArray(array, array.length - 1);
        for (int i = 1; i < array.length; i++) {
            result[i - 1] = array[i];
        }
        return result;
    }

    public static <E> E[] reverse(E[] array) {
        E[] result = newArray(array, array.length);
        for (int i = array.length - 1; i >= 0; i--) {
            result[array.length -1 - i] = array[i];
        }
        return result;
    }

    public static <E> E[] concat(E[] firstArray, E[] secondArray) {
        E[] resultArray = newArray(firstArray, firstArray.length + secondArray.length);

        for (int i = 0; i < firstArray.length; i++) {
            resultArray[i] = firstArray[i];
        }

        for (int j = 0; j < secondArray.length; j++) {
            resultArray[firstArray.length + j] = secondArray[j];
        }
        return resultArray;
    }

    @SuppressWarnings("unchecked")
    private static <E> E[] newArray(E[] array, int length) {
        return (E[]) java.lang.reflect.Array.newInstance(
                array[0].getClass(), length);
    }

    private static <E> E[] arrayWith(E... elements) {
        E[] result = newArray(elements, elements.length);
        for (int i = 0; i < elements.length; i++) {
            result[i] = elements[i];
        }
        return result;
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
