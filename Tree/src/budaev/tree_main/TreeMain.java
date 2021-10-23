package budaev.tree_main;

import budaev.tree.Tree;

import java.util.Comparator;

public class TreeMain {
    public static void main(String[] args) {
        Tree<Integer> tree1 = new Tree<>();
        tree1.add(10);
        tree1.add(3);
        tree1.add(20);
        tree1.add(33);
        tree1.add(30);
        tree1.add(1);
        tree1.add(6);
        tree1.add(null);
        tree1.add(500);


        Tree<String> tree2 = new Tree<>(Comparator.comparing(String::toString));
        tree2.add("asd");
        tree2.add("c");
        tree2.add("bb");
        tree2.add("b");
        tree2.add("ab");
        tree2.add("a");

        System.out.println(tree2);
        System.out.println(tree1);

        System.out.println("Удалён элемент 3 - " + tree1.remove(3));

        System.out.println(tree1);

        System.out.println("Содержит элемент 30 - " + tree1.contains(30));
        System.out.println("Количество элементов - " + tree1.getSize());

        System.out.println("Обход дерева в ширину:");
        tree1.traverseByWidth(System.out::println);

        System.out.println();
        System.out.println("Обход дерева в глубину:");
        tree1.traverseByDepth(System.out::println);

        System.out.println();
        System.out.println("Обход дерева в глубину с рекурсией:");
        tree1.traverseByDepthWithRecursion(System.out::println);
    }
}