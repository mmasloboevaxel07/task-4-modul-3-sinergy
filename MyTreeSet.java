package third_modul.fourth;

public class MyTreeSet<T> {

    /*
    С помощью бинарного дерева и своего класс TreeMap напишите реализацию TreeSet.
    У пользователя должны быть доступны методы add(), remove() и contains().
     */

    private int size = 0;
    private Node root = null;
    private class Node {
        public T value;

        public Node left = null;

        public Node right = null;

        public Node (T value) {
            this.value = value;
        }
    }

    public T add(T value) {
        if (!contains(value)) {
            if (root == null) {
                root = new Node(value);
                size++;
                return null;
            } else {
                return addHelper(root, value);
            }
        }
        return null;
    }
    private T addHelper(Node node, T value) {
        Comparable<? super T> k = (Comparable<? super T>)value;
        int cmp = k.compareTo(node.value);
        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node(value);
                size++;
                return null;
            }
            return addHelper(node.left, value);
        }
        if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node(value);
                size++;
                return null;
            }
            return addHelper(node.right, value);
        }
        T oldValue = node.value;
        node.value = value;
        return oldValue;
    }

    public T get(Object key) {
        Node node = findNode(key);
        if (node == null) return null;
        return node.value;
    }
    private Node findNode(Object target) {
        Comparable<? super T> k = (Comparable<? super T>) target;
        Node node = root;
        while (node != null) {
            int cmp = k.compareTo(node.value);
            if (cmp < 0) {
                node = node.left;
            }
            if (cmp > 0) {
                node = node.right;
            }
            if (cmp == 0) return node;
        }
        return null;
    }

    private Node findParent(Object target) {
        Comparable<? super T> k = (Comparable<? super T>) target;
        Node node = root;
        Node parent = root;
        while (node != null) {
            int cmp = k.compareTo(node.value);
            if (cmp < 0) {
                parent = node;
                node = node.left;
            }
            if (cmp > 0) {
                parent = node;
                node = node.right;
            }
            if (cmp == 0) return parent;
        }
        return null;
    }

    public T remove(Object key) {
        T oldValue = get(key);
        if (key == root.value) root = delRecursive(key);
        else delRecursive(key);
        System.out.println("Root = " + root.value);
        return oldValue;
    }
    private Node delRecursive(Object key) {
        Node node = findNode(key);
        Node parent = findParent(key);
        if (node.left == null && node.right == null) {
            if (node == parent.left) parent.left = null;
            if (node == parent.right) parent.right = null;
            size--;
            return parent;
        }
        if (node.right == null) {
            if (node == parent.left) parent.left = node.left;
            if (node == parent.right) parent.right = node.left;
            size--;
            return parent;
        }
        if (node.left == null) {
            if (node == parent.left) parent.left = node.right;
            if (node == parent.right) parent.right = node.right;
            size--;
            return parent;
        }
        Node tempNode = findSmallest(node.right);
        delRecursive(tempNode.value);
        node.value = tempNode.value;
        return parent;
    }

    private Node findSmallest(Node node) {
        if (node.left == null) return node;
        else {
            return findSmallest(node.left);
        }
    }

    public boolean contains(T value) {
        return findNode(value) != null;
    }

    public void printTree() {
        LER(root);
        System.out.println("Size = " + size +  "\n__________");
    }
    private void LER(Node node) {
        if (node.left != null) LER(node.left);
        System.out.println(node.value);
        if (node.right != null) LER(node.right);
    }
}
