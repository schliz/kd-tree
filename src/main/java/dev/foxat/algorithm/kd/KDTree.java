package dev.foxat.algorithm.kd;

import java.util.*;

/**
 * @ https://rosettacode.org/wiki/K-d_tree
 * @param <T>
 */
public class KDTree<T> {

    private final int dimension;
    private final KDNode<T> root;

    private KDNode<T> best = null;
    private double bestDistance = 0;
    private int visited = 0;

    public KDTree(int dimension, List<KDNode<T>> nodes) {
        this.dimension = dimension;
        root = makeTree(nodes, 0, nodes.size(), 0);
    }

    public synchronized KDNode<T> findNearest(KDNode<T> target) {
        if (root == null) throw new IllegalStateException("Tree is empty!");

        best = null;
        visited = 0;
        bestDistance = 0;
        nearest(root, target, 0);
        return best;
    }

    public int visited() {
        return visited;
    }

    public double distance() {
        return Math.sqrt(bestDistance);
    }

    private void nearest(KDNode<T> root, KDNode<T> target, int index) {
        if (root == null) return;

        ++visited;
        double d = root.distance(target);

        if (best == null || d < bestDistance) {
            bestDistance = d;
            best = root;
        }

        if (bestDistance == 0) {
            return;
        }

        double dx = root.get(index) - target.get(index);
        index = (index + 1) % dimension;

        nearest(dx > 0 ? root.getLeft() : root.getRight(), target, index);

        if (dx * dx >= bestDistance) {
            return;
        }

        nearest(dx > 0 ? root.getRight() : root.getLeft(), target, index);
    }

    private KDNode<T> makeTree(List<KDNode<T>> nodes, int begin, int end, int index) {
        if (end <= begin) {
            return null;
        }

        int n = begin + (end - begin) / 2;
        KDNode<T> node = QuickSelect.select(nodes, begin, end - 1, n, new NodeComparator<>(index));
        index = (index + 1) % dimension;
        node.setLeft(makeTree(nodes, begin, n, index));
        node.setRight(makeTree(nodes, n + 1, end, index));
        return node;
    }

    private static class NodeComparator<T> implements Comparator<KDNode<T>> {

        private final int index;

        private NodeComparator(int index) {
            this.index = index;
        }

        public int compare(KDNode n1, KDNode n2) {
            return Double.compare(n1.get(index), n2.get(index));
        }
    }
}
