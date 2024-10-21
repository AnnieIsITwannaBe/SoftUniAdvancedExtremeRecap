package CustomArrayListWorkshopNoNotes;

import java.util.function.Consumer;

public class SmartArray {

    public int[] elements;

    public int index;

    public SmartArray() {
        this.elements = new int[2];
        this.index = 0;
    }

    public void add(int element) {
        if (index == elements.length) {
            elements = resize(elements.length * 2);
        }
        elements[index] = element;
        index++;
    }

    public int get(int index) {
        return elements[index];
    }

    public int remove(int index) {
        int toBeRemoved = elements[index];


        for (int i = index; i < this.index - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[this.index - 1] = 0;
        this.index--;
        if (this.index == elements.length / 4) {
            elements = resize(elements.length / 2);
        }
        return toBeRemoved;
    }

    public boolean contains(int element) {
        for (int i = 0; i < this.index; i++) {
            if (elements[i] == element) {
                return true;
            }
        }
        return false;
    }

    public void add(int index, int element) {
        int lastElement = elements[this.index - 1];

        for (int i = this.index - 1; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        add(lastElement);
    }

    public void forEach(Consumer<Integer> consumer) {
        for (int i = 0; i < this.index; i++) {
            consumer.accept(elements[i]);
        }
    }

    private int[] resize(int newSize) {
        int[] newArray = new int[newSize];
        if (this.index >= 0) System.arraycopy(elements, 0, newArray, 0, this.index);
        return newArray;
    }
}
