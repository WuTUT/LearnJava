1. Integer 
   1.  
    ```java
    Integer n1 = new Integer(127);
    Integer n2 = new Integer(127);
    //n1 != n2
    Integer n1 = 127;
    Integer n2 = 127;
    //n1 == n2
    Integer n1 = 127;
    Integer n2 = new Integer(127);
    //n1 != n2 
    ``` 
    为了加快程序运行速度，预先做好了堆里-128~127 字面量不制造新对象了 
2. this
   1. 构造函数第一句 可以this()另外一个构造函数且只能第一句 C++11也支持
3. gc
   1. gc 只是通知 不一定真的发生gc
4. 堆和堆栈中的初始化
   1. 成员变量 ： 被清0  C++没有做
   2. 定义时赋值 new一个，调用函数（顺序）        C++11
      * C编译器1次扫描 故函数声明顺序要保证 java至少两次扫描 (没有头文件)
   3. 静态成员变量
      * 表达方式 Class.static      C++ ::
      * 在哪 何时初始化： 启动jvm 一个类的名字 找这个类装载，执行main 动态装所需的载新class javac时就产生了.class
        C++ 静态变量在全局变量，限制类内使用 而 Java 和类的数据在一起 （按需）装载时初始化 
      * 静态初始化块 类装载时执行 
      * 定义初始化块 在构造函数之前执行 用处：没有名字的类（匿名类）的构造函数
5. import
   * 现在的java编译器是java写的，在jvm里编译 import时装载类
   * package 编译时可能通过 但是运行可能找不到.class
   * 没声明属于默认包
6. protected
   * 子类可以访问，包类可以访问  C++ 子类可以访问
7. public 类 包外可以访问
8. private/protected类 内部的类才有意义