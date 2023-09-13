package pre;

class MyCircularQueue {
    private int[] buffer;
    private int count;
    private int front;
    private int rear;
    private int size;

    public MyCircularQueue(int k) {
        buffer = new int[k];
        count = 0;
        front = 0;
        rear = -1;
        size = k;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        rear = (rear + 1) % size;
        buffer[rear] = value;
        count++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        front = (front + 1) % size;
        count--;
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : buffer[front];
    }

    public int Rear() {
        return isEmpty() ? -1 : buffer[rear];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == size;
    }

    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        myCircularQueue.enQueue(1);
        myCircularQueue.enQueue(2);
        myCircularQueue.enQueue(3);
        myCircularQueue.enQueue(4);
        myCircularQueue.Rear();
        myCircularQueue.isFull();
        myCircularQueue.deQueue();
        myCircularQueue.enQueue(4);
        System.out.println(myCircularQueue.Rear());
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */