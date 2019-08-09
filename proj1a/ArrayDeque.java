// Array Deque
public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private float factor;   // size / length
    private T[] Array;

    public ArrayDeque() {
        Array = (T[]) new Object[8];
        size = 0;
        factor = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    private boolean usageFactor() {
        return (factor < 0.25 && Array.length > 16);
    }

    private void resize() {
        int target = Array.length;
        if (usageFactor()) {
            target /= 2;
        } else if (size == Array.length) {
            target *= 2;
        }
        if (usageFactor() || (size == Array.length)) {
            T[] newArray = (T[]) new Object[target];
            factor = (float) size / (float) target;
            int start = 0;
            int end = 0;
            int length;
            if (size == Array.length) {
                end = size - 1;
            } else {
                for (int i = 0; i < Array.length - 1; i++) {
                    if ((Array[i] == null) && (Array[i + 1] != null)) {
                        start = i + 1;
                    }
                    if ((Array[i] != null) && (Array[i + 1] == null)) {
                        end = i;
                    }
                }
            }
            if (start > end) {
                length = (size - start) + end + 1;
            } else {
                length = end - start + 1;
            }
            System.arraycopy(Array, start, newArray, 0, length);
            Array = newArray;
            nextFirst = Array.length - 1;            // Array.length - 1  // 0
            nextLast = length;                   // length            // length + 1
        }
    }

    //=========== 辅助方法 ==============
    private void pointAF(T item) {
        if (isEmpty()) {
            nextFirst = 0;
        }
        Array[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = Array.length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    private void pointAL(T item) {
        Array[nextLast] = item;
        if (nextLast == Array.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    }

    private T pointRF() {
        T res;
        if (nextFirst == Array.length - 1) {
            res = Array[0];
            Array[0] = null;
            nextFirst = 0;
        } else {
            res = Array[nextFirst + 1];
            Array[nextFirst + 1] = null;
            nextFirst += 1;
        }
        return res;
    }

    private T pointRL() {
        T res;
        if (nextLast == 0) {
            res = Array[Array.length - 1];
            Array[Array.length - 1] = null;
            nextLast = Array.length - 1;
        } else {
            res = Array[nextLast - 1];
            Array[nextLast - 1] = null;
            nextLast -= 1;
        }
        return res;
    }

    //=========== 方法实现 ==============
    public void addFirst(T item) {
        resize();
        pointAF(item);
        size += 1;
        factor = (float) size / (float) Array.length;
    }

    public void addLast(T item) {
        resize();
        pointAL(item);
        size += 1;
        factor = (float) size / (float) Array.length;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < Array.length; i++) {
            res.append(Array[i]).append(" ");
        }
        System.out.println(res);
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T res = pointRF();
        size -= 1;
        resize();
        factor = (float) size / (float) Array.length;
        return res;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T res = pointRL();
        size -= 1;
        resize();
        factor = (float) size / (float) Array.length;
        return res;
    }

    public T get(int index) {
        return Array[index];
    }


}


