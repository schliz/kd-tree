import dev.foxat.algorithm.kd.KDTree;
import dev.foxat.algorithm.kd.KDNode;

import java.util.*;

public class KDTreeTest {

    public static void main(String[] args) {
        testWikipedia();
        System.out.println();
        testRandom(1000);
        System.out.println();
        testRandom(1000000);
    }

    private static void testWikipedia() {
        double[][] coords = {
                { 2, 3 }, { 5, 4 }, { 9, 6 }, { 4, 7 }, { 8, 1 }, { 7, 2 }
        };

        List<KDNode<Void>> nodes = new ArrayList<>();

        for (double[] coord : coords) {
            nodes.add(new KDNode<>(coord));
        }

        KDTree<Void> tree = new KDTree<>(2, nodes);
        KDNode<Void> nearest = tree.findNearest(new KDNode<>(new double[]{9, 2}));

        System.out.println("Wikipedia example data:");
        System.out.println("nearest point: " + nearest);
        System.out.println("distance: " + tree.distance());
        System.out.println("nodes visited: " + tree.visited());
    }

    private static KDNode<Void> randomPoint(Random random) {
        double x = random.nextDouble();
        double y = random.nextDouble();
        double z = random.nextDouble();
        return new KDNode<>(new double[]{x, y, z});
    }

    private static void testRandom(int points) {
        Random random = new Random();
        List<KDNode<Void>> nodes = new ArrayList<>();

        for (int i = 0; i < points; ++i) {
            nodes.add(randomPoint(random));
        }

        KDTree<Void> tree = new KDTree<>(3, nodes);
        KDNode<Void> target = randomPoint(random);
        KDNode<Void> nearest = tree.findNearest(target);

        System.out.println("Random data (" + points + " points):");
        System.out.println("target: " + target);
        System.out.println("nearest point: " + nearest);
        System.out.println("distance: " + tree.distance());
        System.out.println("nodes visited: " + tree.visited());
    }
}
