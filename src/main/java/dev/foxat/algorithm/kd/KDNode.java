package dev.foxat.algorithm.kd;

public class KDNode<T> {

    private final double[] coords;
    
    private KDNode<T> left;
    private KDNode<T> right;

    private final T extraData;

    public KDNode(T extraData, double... coords) {
        this.coords = coords;
        this.left = null;
        this.right = null;
        this.extraData = extraData;
    }

    public KDNode(double... coords) {
        this(null, coords);
    }
    
    public double get(int index) {
        return coords[index];
    }

    public KDNode<T> getLeft() {
        return left;
    }

    public void setLeft(KDNode<T> left) {
        this.left = left;
    }

    public KDNode<T> getRight() {
        return right;
    }

    public void setRight(KDNode<T> right) {
        this.right = right;
    }

    public T getExtraData() {
        return extraData;
    }

    public double distance(KDNode<T> node) {
        double dist = 0;
        for (int i = 0; i < coords.length; ++i) {
            double d = coords[i] - node.coords[i];
            dist += d * d;
        }
        return dist;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder("(");
        for (int i = 0; i < coords.length; ++i) {
            if (i > 0)
                s.append(", ");
            s.append(coords[i]);
        }
        s.append(')');
        return s.toString();
    }
}