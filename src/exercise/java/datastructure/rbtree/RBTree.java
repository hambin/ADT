package exercise.java.datastructure.rbtree;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hanbing on 2017/3/8.
 */
public class RBTree<T extends Comparable<? super T>> {

    AtomicLong size = new AtomicLong(0);
    RBTreeNode<T> rootTree;

    public RBTree(){
        rootTree = new RBTreeNode<T>();
    }

    public RBTreeNode<T> getRoot(){
        return rootTree.getLeft();
    }

    public RBTreeNode<T> findParentNode(RBTreeNode<T> x){
        RBTreeNode<T> root = getRoot();
        RBTreeNode<T> child = root;
        while(child != null){
            int result = x.getData().compareTo(child.getData());
            if(result < 0){
                root = child;
                child = child.getLeft();
            }else if(result > 0){
                root = child;
                child = child.getRight();
            }else{
                return child;
            }
        }
        return root;
    }

    public RBTreeNode<T> findUncleNode(RBTreeNode<T> node){
        RBTreeNode<T> parent = findParentNode(node);
        RBTreeNode<T> ancestor = findParentNode(parent);
        if(null == ancestor){
            return null;
        }else if(ancestor.getLeft().equals(parent)){
            return ancestor.getRight();
        }else{
            return ancestor.getLeft();
        }
    }


    //插入
    public T insert(T t, RBTreeNode<T> node){
        node.setLeft(null);
        node.setRight(null);
        node.makeRed();
        node.setParent(null);

        if(null == getRoot()){
            rootTree.setLeft(node);
            node.makeBlack();
            size.incrementAndGet();
        }else{
            RBTreeNode<T> x = findParentNode(node);
            int result = node.getData().compareTo(x.getData());
            //如果值相等的话，不设置父指针，直接返回一个值
            if(0 == result){
                return node.getData();
            }
            node.setParent(x);
            if(result < 0){
                x.setLeft(node);
            }else {
                x.setRight(node);
            }
            size.incrementAndGet();
        }
        return null;
    }

    private void rotateLeft(RBTreeNode<T> node){
        RBTreeNode<T> right = node.getRight();
        if(right==null){
            throw new java.lang.IllegalStateException("right node is null");
        }
        RBTreeNode<T> parent = node.getParent();
        node.setRight(right.getLeft());
        setParent(right.getLeft(),node);

        right.setLeft(node);
        setParent(node,right);

        if(parent==null){//node pointer to root
            //right  raise to root node
            rootTree.setLeft(right);
            setParent(right,null);
        }else{
            if(parent.getLeft()==node){
                parent.setLeft(right);
            }else{
                parent.setRight(right);
            }
            //right.setParent(parent);
            setParent(right,parent);
        }
    }
    private void setParent(RBTreeNode<T> node,RBTreeNode<T> parent){
        if(node!=null){
            node.setParent(parent);
            if(parent==rootTree){
                node.setParent(null);
            }
        }
    }
}
