package CustomArrayListWorkshopNoNotes.CustomStack;

import java.util.function.Consumer;

//node-структурата
//кутийка, която пази елемент, информация за тази стойност как се отнася към другите стойнсти към структурите данни
//можем да пазим каквито си поискаме полета за всеки един ноуд, за да имаме правилен стейт на структурата
//такива каквито ни трябват за правилен стейт на структурата
//new е момента в който ще заделя actual памет в рамта 
public class SmartStack {
    public int value;
    public Node top;

    public static class Node {
        public Node prev;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public void push(int element) {
        Node newNode = new Node(element);

        if (top != null) {
            newNode.prev = top;
        }
        top = newNode;
    }

    public int pop() {
        int val = top.value;
        top = top.prev;
        return val;
    }

    public int peek() {
        return top.value;
    }

    public void foreach(Consumer<Integer> consumer) {
        Node last = top;

        while (last != null) {
            consumer.accept(last.value);
            last = last.prev;
        }
    }
}
