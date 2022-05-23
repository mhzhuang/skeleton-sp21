package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        comp = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maximum = get(0);

        for (int i = 0; i < this.size(); i++) {
            if(comp.compare(maximum, get(i)) < 0) {
                maximum = get(i);
            }
        }
        return maximum;
    }

    public T max(Comparator<T> c) {
        if (c == null) {
            return null;
        }

        Comparator<T> com = c;
        T maximum = this.get(0);

        for (int i = 0; i < this.size(); i++) {
            if(comp.compare(maximum, this.get(i)) < 0) {
                maximum = this.get(i);
            }
        }
        return maximum;
    }

}
