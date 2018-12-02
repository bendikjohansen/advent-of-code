package me.bmjohansen.adventofcode.solver;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Bendik Mathias Johansen
 */
public class DayTwoSolver implements Solver<Integer, String> {
    @Override
    public Integer solveFirst(String input) {
        int repeatedTwice = 0;
        int repeatedThrice = 0;

        var ids = input.split("\n");
        for (var id : ids) {
            var counter = count(id);

            if (counter.containsValue(2)) {
                repeatedTwice++;
            }
            if (counter.containsValue(3)) {
                repeatedThrice++;
            }
        }

        return repeatedTwice * repeatedThrice;
    }

    @Override
    public String solveSecond(String input) {
        var charIndex = new HashMap<
                AbstractMap.SimpleImmutableEntry<Character, Integer>,
                ArrayList<String>>();

        var ids = input.split("\n");

        for (var id : ids) {
            for (var i = 0; i < id.length(); i++) {
                var entry = new AbstractMap.SimpleImmutableEntry<>(id.charAt(i), i);
                charIndex.putIfAbsent(entry, new ArrayList<>());

                var matches = charIndex.get(entry);
                matches.add(id);
                charIndex.put(entry, matches);
            }
        }

        for (var id : ids) {
            var match = findMatch(charIndex, id);

            if (!match.isEmpty()) {
                for (var i = 0; i < match.length(); i++) {
                    if (id.charAt(i) != match.charAt(i)) {
                        return id.substring(0, i) + id.substring(i + 1);
                    }
                }
            }
        }

        return null;
    }

    private HashMap<Character, Integer> count(String word) {
        var charCounter = new HashMap<Character, Integer>();

        for (var c : word.toCharArray()) {
            charCounter.put(c, charCounter.getOrDefault(c, 0) + 1);
        }

        return charCounter;
    }

    private String findMatch(Map<AbstractMap.SimpleImmutableEntry<Character, Integer>, ArrayList<String>> charIndex, String id) {
        var matchCounter = new HashMap<String, Integer>();

        for (var i = 0; i < id.length(); i++) {
            var entry = new AbstractMap.SimpleImmutableEntry<>(id.charAt(i), i);
            var matches = charIndex.getOrDefault(entry, new ArrayList<>());

            for (var match : matches) {
                matchCounter.putIfAbsent(match, 0);
                matchCounter.put(match, matchCounter.get(match) + 1);
            }
        }

        for (var key : matchCounter.keySet()) {
            if (matchCounter.get(key) == id.length() - 1) {
                return key;
            }
        }
        return "";
    }
}
