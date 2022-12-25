package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private Queue<T> queue = new LinkedList<>();
    private int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while (capacity == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Something went wrong!", e);
            }
        }
        capacity--;
        queue.add(element);
        notify();

    }

    public synchronized T take() throws InterruptedException {
        while (capacity != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Something went wrong!", e);
            }
        }
        capacity++;
        notify();
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
