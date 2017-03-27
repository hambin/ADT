package exercise.java.datastructure.b_tree;

/**
 * Created by hanbing on 2017/3/23.
 */
public class BTreeOption {

    public static class SearchResult<V>{
        private boolean exist;
        private int index;
        private V value;

        public SearchResult(boolean exist, int index){
            this.index = index;
            this.exist = exist;
        }
        public SearchResult(boolean exist, int index, V value){
            this(exist, index);
            this.value = value;
        }

        public boolean isExist() {
            return exist;
        }

        public int getIndex() {
            return index;
        }

        public V getValue() {
            return value;
        }
    }



}
