package bstmap;

import java.util.Set;
import java.util.Iterator;
import java.util.Map;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left;
        private Node right;

        public Node(K k, V v) {
            key = k;
            val = v;
        }
    }

    public BSTMap() {
        clear();
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean containsKey(K key) {
        return getNode(root, key) != null;
    }

    private Node getNode(Node curr, K key) {
        if (curr == null) {
            return null;
        }
            int cmp = key.compareTo(curr.key);
            if (cmp > 0) {
                return getNode(curr.right, key);
            } else if (cmp < 0) {
                return getNode(curr.left, key);
            } else {
                return curr;
            }
        }

        public V get (K key){
            Node n = getNode(root, key);
            if (n == null) {
                return null;
            }
            return n.val;
        }

        public int size () {
            return size;
        }

        public void put (K key, V val){
            root = putHelper(root, key, val);
        }

        private Node putHelper (Node curr, K key, V val){
            if (curr == null) {
                size++;
                return new Node(key, val);
            }
            int cmp = key.compareTo(curr.key);
            if (cmp > 0) {
                curr.right = putHelper(curr.right, key, val);
            } else if (cmp < 0) {
                curr.left = putHelper(curr.left, key, val);
            } else {
                curr.val = val;
            }
            return curr;
        }
        public Set<K> keySet() {
            throw new UnsupportedOperationException();
        }
        public V remove(K key) {
            throw new UnsupportedOperationException();
        }
        public V remove(K key, V val) {
            throw new UnsupportedOperationException();
        }
        public Iterator<K> iterator() {
            throw new UnsupportedOperationException();
        }
        public void printInOrder() {
        }
    }
