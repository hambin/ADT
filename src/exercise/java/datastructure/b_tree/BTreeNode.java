package exercise.java.datastructure.b_tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hanbing on 2017/3/23.
 */
public class BTreeNode<K, V> {

    private List<Entry<K, V>> entrys;
    private List<BTreeNode<K, V>> children;
    private boolean leaf;
    private Comparator<K> comparator;

    private BTreeNode(){
        entrys = new ArrayList<>();
        children = new ArrayList<>();
        leaf  = false;
    }

    public BTreeNode(Comparator<K> comparator){
        this();
        this.comparator = comparator;
    }

    public boolean isLeaf(){
        return leaf;
    }

    public void setLeaf(boolean isleaf){
        this.leaf = isleaf;
    }

    public int size(){
        return entrys.size();
    }

    public int compare(K key1, K key2){
        return (comparator == null ? ((Comparable<K>)key1).compareTo(key2) : comparator.compare(key1, key2));
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }
    }

}
