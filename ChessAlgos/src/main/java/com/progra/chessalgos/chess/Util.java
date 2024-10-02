package com.progra.chessalgos.chess;

import java.util.Set;
import java.util.function.Predicate;

public class Util {
    public static <T>  T findObject(Set<T> set, Predicate<T> condition) {
        for(T element : set) {
            if(condition.test(element)) {
                return element;
            }
        }
        return null;
    }
}
