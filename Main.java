package third_modul.fourth;

public class Main {
    public static void main(String[] args) {
        MyTreeSet<Integer> set = new MyTreeSet<>();
        // Заполняем сет
        for (int i = 0; i < 15; i++) {
            set.add(i);
        }
        set.printTree();

        set.add(1); // Не добавится, так как в сете недопустимы дубликаты
        set.add(1234); // Добавиться, такого элемента еще не было
        set.printTree();

        set.remove(1);
        set.remove(7);
        set.printTree();

        System.out.println(set.contains(0)); // true
        System.out.println(set.contains(4)); // true
        System.out.println(set.contains(43)); // false
        System.out.println(set.contains(-4)); // false
        System.out.println(set.contains(13)); // true
    }
}
