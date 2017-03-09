package exercise.java.datastructure.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hanbing on 2017/3/7.
 */
public class BinarySortTree<T extends Comparable<? super T>> {
    static class BinaryNode<T> {
        T data;
        BinaryNode<T> leftNode;
        BinaryNode<T> rightNode;

        public BinaryNode(T data) {
            this(data, null, null);
        }

        public BinaryNode(T data, BinaryNode leftNode, BinaryNode rightNode) {
            this.data = data;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public BinaryNode() {
            this.data = null;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }

    private BinaryNode<T> rootTree;

    public boolean isEmpty() {
        return rootTree == null;
    }

    //查询
    public boolean contains(T t) {
        return contains(t, rootTree);
    }

    public boolean contains(T t, BinaryNode<T> node) {
        if (null == node) {
            return false;
        }
        int result = t.compareTo(node.data);
        if (result < 0) {
            return contains(t, node.leftNode);
        } else if (result > 0) {
            return contains(t, node.rightNode);
        } else {
            return true;
        }
    }

    //查找最大最小值
    public T findMin() {
        if (isEmpty()) {
            System.out.println("tree is null");
            return null;
        } else
            return findMin(rootTree).data;
    }

    public T findMax() {
        if (isEmpty()) {
            System.out.println("tree is null");
            return null;
        } else {
            return findMax(rootTree).data;
        }
    }

    public BinaryNode<T> findMax(BinaryNode<T> node) {
        if (null == node) {
            return null;
        } else if (null == node.rightNode)
            return node;
        return findMax(node.rightNode);
    }

    public BinaryNode<T> findMin(BinaryNode<T> node) {
        if (null == node) {
            return null;
        } else if (null == node.leftNode) {
            return node;
        }
        return findMin(node.leftNode);
    }

    //插入
    public void insert(T t) {
        rootTree = insert(t, rootTree);
    }

    public BinaryNode<T> insert(T t, BinaryNode<T> node) {
        if (node == null) {
            return new BinaryNode<T>(t, null, null);
        }
        int result = t.compareTo(node.data);
        if (result > 0) {
            //等号左边必须有，要不然插不进去
            node.rightNode = insert(t, node.rightNode);
        } else if (result < 0) {
            node.leftNode = insert(t, node.leftNode);
        } else {

        }
        return node;
    }

    public void proOrderPrint(BinaryNode<T> node) {
        if (node != null) {
            proOrderPrint(node.leftNode);
            System.out.print(node.data + " ");
            proOrderPrint(node.rightNode);

        }
    }

    //删除
    public void delete(T t){
        if(rootTree == null){
            System.out.println("tree is null");
        }else if(!contains(t, rootTree)){
            System.out.println("t not exsit");
        }else{
            delete(t, rootTree);
        }
    }
    public BinaryNode<T> delete(T t, BinaryNode<T> node) {
        int result = t.compareTo(node.data);
        //因为删除后要返回新的节点，所以要用子树的根节点接收
        if (result < 0) {
            node.leftNode = delete(t, node.leftNode);
        } else if (result > 0) {
            node.rightNode = delete(t, node.rightNode);
        } else if (node.leftNode != null && node.rightNode != null) {
            node.data = findMin(node.rightNode).data;
            node.rightNode = delete(node.data, node.rightNode);
        } else {
            node = (node.leftNode != null) ? node.leftNode : node.rightNode;
        }
        return node;
    }

    public static void main(String[] args) {
        List<Integer> inventory = Arrays.asList(1, 2, 30, 4, 5);
        BinarySortTree<Integer> bst = new BinarySortTree<>();
        for (Integer e : inventory) {
            bst.insert(e);
        }
        System.out.println(bst.findMin() + " " + bst.findMax());
        System.out.println(bst.contains(3));
        bst.proOrderPrint(bst.rootTree);
//        bst.delete(1, bst.rootTree);
        //因为删除根节点也就是意味着之前的rootTree已经失效，但是指向关系并没有删除，所以要更新根节点输出
        bst.proOrderPrint(bst.delete(2, bst.rootTree));
    }
}
