package com.wutut.ThinkinginJava.Chap21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//保护性暂停
public class GuardedObject {
    private int id;

    private Object response;// 传递的结果
    // 获取结果

    public GuardedObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Object get(long timeout) {
        synchronized (this) {// this 不是response
            long begin = System.currentTimeMillis();
            long passedTime = 0;
            while (response == null) {
                long waitTime = timeout - passedTime;
                if (waitTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passedTime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (this) {
            this.response = response;
            this.notifyAll();
        }
    }

    public static void main(String[] args) {
        // GuardedObject guardedObject = new GuardedObject(1);
        // new Thread(() -> {
        // System.out.println(guardedObject.get(2000));

        // }, "t1").start();
        // new Thread(() -> {
        // System.out.println("download now");
        // Downloader downloader = new Downloader();
        // try {
        // guardedObject.complete(downloader.download());
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // }).start();
        for (int i = 0; i < 3; i++) {
            new People().start();

        }
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Integer id : Mailboxes.getIds()) {
            System.out.println("remove id " + id);
            new PostMan(id, "hello " + id).start();

        }
    }
}

class Mailboxes {
    private static Map<Integer, GuardedObject> boxes = new Hashtable<>();
    private static int id = 1;

    private static synchronized int generatedId() {
        return id++;
    }

    public static GuardedObject createGuardedObject() {
        GuardedObject go = new GuardedObject(generatedId());
        boxes.put(go.getId(), go);
        return go;
    }

    public static GuardedObject getGuardedObject(int id) {
        // return boxes.remove(id); // why false????
        return boxes.get(id);
    }

    public static Set<Integer> getIds() {
        // System.err.println(boxes.keySet());
        return boxes.keySet();
    }
}

class People extends Thread {
    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.createGuardedObject();
        // System.out.println("id= " + guardedObject.getId());
        Object mail = guardedObject.get(5000);
        System.out.println("收信 id= " + guardedObject.getId() + " " + mail);
    }
}

class PostMan extends Thread {
    private int id;
    private String mail;

    public PostMan(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.getGuardedObject(id);
        System.out.println("送信 id= " + id + " " + mail);
        guardedObject.complete(mail);
    }
}

class Downloader {
    public List<String> download() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL("https://www.baidu.com/").openConnection();
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
