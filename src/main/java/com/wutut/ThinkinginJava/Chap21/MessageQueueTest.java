package com.wutut.ThinkinginJava.Chap21;

import java.util.LinkedList;

public class MessageQueueTest {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);
        for (int i = 0; i < 5; i++) {
            int id = i;
            new Thread(() -> {
                queue.put(new Message(id, "value " + id));
            }, "Producer " + i).start();
        }
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                while (true) {
                    queue.take();
                }

            }, "Consumer " + i).start();
        }

    }
}

class Message {
    private int id;
    private Object value;

    Message(int id, Object value) {
        this.value = value;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }
}

class MessageQueue {
    private LinkedList<Message> list = new LinkedList<>();
    private int capacity;

    MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public Message take() {
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    System.out.println("Queue Empty");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Message message = list.removeFirst();
            System.out.println(Thread.currentThread().getName() + " take one " + message.getValue());
            list.notifyAll();
            return message;

        }
    }

    public void put(Message message) {
        synchronized (list) {
            while (list.size() == capacity) {
                try {
                    System.out.println("Queue Full");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            list.addLast(message);
            System.out.println(Thread.currentThread().getName() + " put one " + message.getValue());
            list.notifyAll();
        }
    }
}