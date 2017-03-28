package exercise.java.datastructure.b_tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hanbing on 2017/3/27.
 */
public class BTree<K extends Comparable<K>> {
    private BNode<K> root;
    private int hight;
    private int minDegree;

    public static class BNode<K extends Comparable<K>> {
        private List<K> keys;
        private List<BNode<K>> children;
        private boolean leaf;
        private int size;

        public BNode() {
        }

        public BNode(List<K> keys, List<BNode<K>> children, boolean leaf) {
            this.keys = keys;
            this.children = children;
            this.leaf = leaf;
        }

        public List<K> getKeys() {
            return keys;
        }

        public void setKeys(List<K> keys) {
            this.keys = keys;
        }

        public List<BNode<K>> getChildren() {
            return children;
        }

        public void setChildren(List<BNode<K>> children) {
            this.children = children;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public BNode getRoot() {
        return root.children.get(0);
    }

    //search key k in B-Tree
    public SearchResult search(BNode<K> current, K k) {
        int i = 0;
        while (i < current.size && k.compareTo(current.keys.get(i)) > 0) {
            i++;
        }
        if (i < current.size && 0 == k.compareTo(current.keys.get(i))) {
            return new SearchResult(i, current);
        }
        if (current.isLeaf()) {
            return null;
        }
        return search(current.getChildren().get(i), k);
    }

    public BNode<K> CreateTree() {
        root = new BNode();
        return getRoot();
    }

    public static class SearchResult<K extends Comparable<K>> {
        private int index;
        private BNode node;

        public SearchResult(int index, BNode node) {
            this.index = index;
            this.node = node;
        }
    }

    /**
     * 拆分节点
     *
     * @param node  未满的拆分节点的父节点
     * @param index 拆分节点在父节点中的位置
     */
    public void splitNode(BNode<K> node, int index) {
        BNode<K> current = node.getChildren().get(index);
        //待拆分节点的键值数量
        int splitNodeKeysCount = current.getSize();
        //待拆分节点的子节点个数
        int splitNodeChildrenCount = current.getChildren().size();

        LinkedList<K> rightNodeKeys = new LinkedList<K>(current.getKeys().subList(splitNodeKeysCount / 2 + 1, splitNodeKeysCount));

        LinkedList<BNode<K>> rightNodeChildren = current.getChildren().isEmpty() ?
                new LinkedList<>() : new LinkedList<>(current.getChildren().subList((splitNodeChildrenCount + 1) / 2, splitNodeChildrenCount));

        BNode<K> rightNode = new BNode<K>(rightNodeKeys, rightNodeChildren, current.isLeaf());

        LinkedList<K> leftNodeKeys = new LinkedList<K>(current.getKeys().subList(0, splitNodeKeysCount / 2));
        LinkedList<BNode<K>> leftNodeChildren = current.getChildren().isEmpty() ?
                new LinkedList<>() : new LinkedList<>(current.getChildren().subList(0, (splitNodeChildrenCount + 1) / 2));

        BNode<K> leftNode = new BNode<K>(leftNodeKeys, leftNodeChildren, current.isLeaf());

        current.getChildren().set(index, leftNode);

        
    }

}
