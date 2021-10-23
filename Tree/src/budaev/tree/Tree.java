package budaev.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private final Comparator<T> comparator;
    private int size;

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public Tree() {
        comparator = (o1, o2) -> {
            if (o1 == null) {
                if (o2 == null) {
                    return 0;
                }

                return -1;
            }

            if (o2 == null) {
                return 1;
            }

            //noinspection unchecked
            return ((Comparable<T>) o1).compareTo(o2);
        };
    }

    public int getSize() {
        return size;
    }

    public void add(T data) {
        if (root == null) {
            root = new TreeNode<>(data);

            size++;

            return;
        }

        TreeNode<T> currentNode = root;

        while (currentNode != null) {
            if (comparator.compare(currentNode.getData(), data) > 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    TreeNode<T> newNode = new TreeNode<>(data);
                    currentNode.setLeft(newNode);

                    size++;

                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    TreeNode<T> newNode = new TreeNode<>(data);
                    currentNode.setRight(newNode);

                    size++;

                    return;
                }
            }
        }
    }

    public boolean contains(T data) {
        if (size == 0) {
            return false;
        }

        TreeNode<T> currentNode = root;

        while (currentNode != null) {
            int compareResult = comparator.compare(currentNode.getData(), data);

            if (compareResult == 0) {
                return true;
            }

            if (compareResult > 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return false;
    }

    public boolean remove(T data) {
        if (size == 0) {
            return false;
        }

        TreeNode<T> currentNode = root;
        TreeNode<T> parentNode = root;
        boolean isLeftBranch = true;

        while (currentNode.getData() != data) {
            parentNode = currentNode;

            if (comparator.compare(currentNode.getData(), data) > 0) {
                isLeftBranch = true;
                currentNode = currentNode.getLeft();
            } else {
                isLeftBranch = false;
                currentNode = currentNode.getRight();
            }
            if (currentNode == null) {
                return false;
            }
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (currentNode == root) {
                root = null;
            } else if (isLeftBranch) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }
        } else if (currentNode.getRight() == null) {
            if (currentNode == root) {
                root = currentNode.getLeft();
            } else if (isLeftBranch) {
                parentNode.setLeft(currentNode.getLeft());
            } else {
                parentNode.setRight(currentNode.getLeft());
            }
        } else if (currentNode.getLeft() == null) {
            if (currentNode == root) {
                root = currentNode.getRight();
            } else if (isLeftBranch) {
                parentNode.setLeft(currentNode.getRight());
            } else {
                parentNode.setRight(currentNode.getRight());
            }
        } else {
            TreeNode<T> rightBranchMinNode = getMinimumNode(currentNode.getRight());

            if (currentNode == root) {
                root = rightBranchMinNode;
            } else if (isLeftBranch) {
                parentNode.setLeft(rightBranchMinNode);
            } else {
                parentNode.setRight(rightBranchMinNode);
            }

            rightBranchMinNode.setLeft(currentNode.getLeft());
        }

        size--;

        return true;
    }

    private TreeNode<T> getMinimumNode(TreeNode<T> node) {
        if (node.getLeft() == null) {
            return node;
        } else {
            return getMinimumNode(node.getLeft());
        }
    }

    public void traverseByWidth(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();

            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void traverseByDepth(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.pop();

            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }

    private void visit(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            visit(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            visit(node.getRight(), consumer);
        }
    }

    public void traverseByDepthWithRecursion(Consumer<T> consumer) {
        if (size == 0) {
            return;
        }

        visit(root, consumer);
    }

    @Override
    public String toString() {
        return root.toString();
    }
}