package me.bmjohansen.adventofcode.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Bendik Mathias Johansen
 */
public class DaySevenSolver implements Solver<String, Integer> {
    @Override
    public String solveFirst(String input) {
        var charMap = getCharacterMap(input);
        var dependencies = getDependencies(charMap);

        return findOrderOfDependencies(charMap, dependencies);
    }

    @Override
    public Integer solveSecond(String input) {
        final var charMap = getCharacterMap(input);
        final var dependencies = getDependencies(charMap);
        final var stepCompletionTime = new HashMap<Character, Integer>();
        var independents = findIndependents(dependencies, new ArrayList<>());
        var vacantWorkers = 5;


        var timePassed = 0;
        while (!independents.isEmpty()) {
            var stepsComplete = new HashSet<Character>();
            for (var step : stepCompletionTime.keySet()) {
                if (stepCompletionTime.get(step) <= timePassed) {
                    vacantWorkers++;
                    stepsComplete.add(step);
                    for (var dependent : charMap.get(step)) {
                        dependencies.get(dependent).remove(step);
                    }
                    charMap.remove(step);
                    dependencies.remove(step);
                }
            }
            for (var step : stepsComplete) {
                independents.remove(step);
                stepCompletionTime.remove(step);
            }


            independents = findIndependents(dependencies, independents);
            for (var independent : independents) {
                if (vacantWorkers == 0) {
                    break;
                }
                if (!stepCompletionTime.containsKey(independent)) {
                    var completionTime = timePassed + (independent - 'A') + 61;
                    stepCompletionTime.put(independent, completionTime);
                    vacantWorkers--;
                }

            }

            timePassed = stepCompletionTime
                    .values()
                    .stream()
                    .min(Integer::compareTo)
                    .orElse(timePassed);
        }

        return timePassed;
    }

    private String findOrderOfDependencies(
            Map<Character, List<Character>> charMap,
            Map<Character, Set<Character>> dependencies) {
        return findOrderOfDependencies(charMap, dependencies, new ArrayList<>());
    }

    private String findOrderOfDependencies(
            Map<Character, List<Character>> charMap,
            Map<Character, Set<Character>> dependencies,
            List<Character> independentChars) {
        var independents = findIndependents(dependencies, independentChars);

        if (independents.isEmpty()) {
            return "";
        }

        final var nextChar = independents.get(0);
        independents.remove(0);

        for (var dependent : charMap.get(nextChar)) {
            dependencies.get(dependent).remove(nextChar);
        }
        dependencies.remove(nextChar);

        return nextChar + findOrderOfDependencies(charMap, dependencies, independents);
    }

    private List<Character> findIndependents(
            Map<Character, Set<Character>> dependencies,
            List<Character> independentChars) {
        final var independents = new ArrayList<>(independentChars);
        for (var dependent : dependencies.keySet()) {
            if (dependencies.get(dependent).isEmpty() && !independents.contains(dependent)) {
                independents.add(dependent);
            }
        }
        independents.sort(Character::compareTo);

        return independents;
    }

    private Map<Character, Set<Character>> getDependencies(Map<Character, List<Character>> map) {
        final var dependencyMap = new HashMap<Character, Set<Character>>();

        for (var dependency : map.keySet()) {
            dependencyMap.put(dependency, new HashSet<>());
        }

        for (var dependency : map.keySet()) {
            for (var dependent : map.get(dependency)) {
                dependencyMap.putIfAbsent(dependent, new HashSet<>());
                dependencyMap.get(dependent).add(dependency);
            }
        }

        return dependencyMap;
    }

    private Map<Character, List<Character>> getCharacterMap(String input) {
        final var characterMap = new HashMap<Character, List<Character>>();

        input.lines().forEach(line -> {
            final var dependency = line.charAt(5);
            final var dependent = line.charAt(36);
            var list = characterMap.getOrDefault(dependency, new ArrayList<>());
            list.add(dependent);
            characterMap.putIfAbsent(dependency, list);
            characterMap.putIfAbsent(dependent, new ArrayList<>());
        });

        return characterMap;
    }
}
