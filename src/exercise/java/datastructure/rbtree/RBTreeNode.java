package exercise.java.datastructure.rbtree;

/**
 * Created by hanbing on 2017/3/8.
 */
public class RBTreeNode<T extends Comparable<? super T>> {
    private T data;
    private RBTreeNode<T> left;
    private RBTreeNode<T> right;
    private RBTreeNode<T> parent;
    private boolean red;

    public RBTreeNode() {

    }

    public RBTreeNode(T t) {
        this.data = t;
    }

    public RBTreeNode(T t, boolean isRed) {
        this.data = t;
        this.red = isRed;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RBTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(RBTreeNode<T> left) {
        this.left = left;
    }

    public RBTreeNode<T> getRight() {
        return right;
    }

    public void setRight(RBTreeNode<T> right) {
        this.right = right;
    }

    public RBTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(RBTreeNode<T> parent) {
        this.parent = parent;
    }

    public boolean isRed() {
        return red;
    }

    public boolean isBlack() {
        return !red;
    }

    public boolean isLeaf() {
        return left == null && null == right;
    }

    void setRed(boolean red) {
        this.red = red;
    }

    void makeRed() {
        red = true;
    }

    void makeBlack() {
        red = false;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
