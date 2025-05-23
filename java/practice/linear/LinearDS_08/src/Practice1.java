// Practice1
// ArrayList 를 이용한 큐 구현

import java.util.ArrayList;

class MyQueue1 {
    ArrayList list;

    MyQueue1() {
        this.list = new ArrayList();
    }

    public boolean isEmpty() {
        if (this.list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void push(int data) {
        this.list.add(data);
    }

    public Integer pop() {
        if (this.list.isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }

        int data = (int) this.list.get(0); // list가 제네릭하게 되어있지 않아 (int)로 형변환 해줌
        this.list.remove(0);
        return data;
    }

    public Integer peek() {
        if (this.list.isEmpty()) {
            System.out.println("Queue is empty!");
            return null;
        }

        return (int) this.list.get(0);
    }

    public void printQueue() {
            System.out.println(this.list);
    }
}

public class Practice1 {
    public static void main(String[] args) {
        // Test code
        MyQueue1 myQueue = new MyQueue1();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(5);

        myQueue.printQueue();   // 1, 2, 3, 4, 5

        System.out.println(myQueue.peek()); // 1
        myQueue.printQueue();   // 1, 2, 3, 4, 5

        System.out.println(myQueue.pop());  // 1
        myQueue.printQueue();   // 2, 3, 4, 5

        System.out.println(myQueue.pop());  // 2
        myQueue.printQueue();   // 3, 4, 5

        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        myQueue.printQueue();
    }
}
