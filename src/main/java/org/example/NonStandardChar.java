package org.example;

public record NonStandardChar(String stringRep, char charRep) implements Comparable<String> {

    @Override
    public int compareTo(String o) {
        return stringRep.compareTo(o);
    }
}
