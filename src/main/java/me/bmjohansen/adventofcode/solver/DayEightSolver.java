package me.bmjohansen.adventofcode.solver;

import java.util.Arrays;

/**
 * @author Bendik Mathias Johansen
 */
public class DayEightSolver implements Solver<Integer, Integer> {

    class Node {
        private final Node[] children;
        private final int[] metadata;
        private final int descendants;
        private final int levelSpan;

        public Node(
                Node[] children,
                int[] metadata,
                int descendants,
                int levelSpan) {
            this.children = children;
            this.metadata = metadata;
            this.descendants = descendants;
            this.levelSpan = levelSpan;
        }
    }

    @Override
    public Integer solveFirst(String input) {
        final var numbers = Arrays
                .stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        final var root = createTree(numbers);

        return findMetadataSum(root);
    }

    @Override
    public Integer solveSecond(String input) {
        final var numbers = Arrays
                .stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        final var root = createTree(numbers);

        return findNodeWorth(root);
    }

    private int findNodeWorth(Node node) {
        if (node.children.length == 0) {
            return findMetadataSum(node);
        }

        var worth = 0;
        for (var index : node.metadata) {
            if (index - 1 < node.children.length && index > 0) {
                worth += findNodeWorth(node.children[index - 1]);
            }
        }

        return worth;
    }

    private int findMetadataSum(Node node) {
        var metadataSum = 0;
        for (var child : node.children) {
            metadataSum += findMetadataSum(child);
        }
        for (var metaDatum : node.metadata) {
            metadataSum += metaDatum;
        }

        return metadataSum;
    }

    private Node createTree(int[] input) {
        final var numberOfChildren = input[0];
        final var metadata = new int[input[1]];
        final var children = new Node[numberOfChildren];

        var numbersToSkip = 2;
        for (var i = 0; i < numberOfChildren; i++) {
            var subArray = Arrays.copyOfRange(
                    input,
                    numbersToSkip,
                    input.length
            );
            children[i] = createTree(subArray);
            numbersToSkip += children[i].levelSpan;
        }
        final var levelSpan = numbersToSkip + metadata.length;

        for (var i = numbersToSkip; i < levelSpan; i++) {
            metadata[i - numbersToSkip] = input[i];
        }

        return new Node(children, metadata, numberOfChildren, levelSpan);
    }

}
