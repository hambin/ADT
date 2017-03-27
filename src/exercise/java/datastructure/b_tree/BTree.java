package exercise.java.datastructure.b_tree;

import java.util.List;

/**
 * Created by hanbing on 2017/3/27.
 */
public class BTree <K extends Comparable<K>> {
    private BNode<K> root;
    private int hight;
    private int minDegree;

    public static class BNode<K extends Comparable<K>>{
        private List<K> keys;
        private List<BNode> children;
        private boolean leaf;
        private int size;

        public List<K> getKeys() {
            return keys;
        }

        public void setKeys(List<K> keys) {
            this.keys = keys;
        }

        public List<BNode> getChildren() {
            return children;
        }

        public void setChildren(List<BNode> children) {
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

    public BNode getRoot(){
        return root.children.get(0);
    }

    //search key k in B-Tree
    public SearchResult search(BNode<K> current, K k){
        int i = 0;
        while(i < current.size && k.compareTo(current.keys.get(i)) > 0){
            i++;
        }
        if(i < current.size && 0 == k.compareTo(current.keys.get(i))){
            return new SearchResult(i, current);
        }
        if(current.isLeaf()){
            return null;
        }
        return search(current.getChildren().get(i), k);
    }

    public BNode<K> CreateTree(){
        root = new BNode();
        return root;
    }

    public static class SearchResult<K extends Comparable<K>>{
        private int index;
        private BNode node;

        public SearchResult(int index, BNode node) {
            this.index = index;
            this.node = node;
        }
    }
}
