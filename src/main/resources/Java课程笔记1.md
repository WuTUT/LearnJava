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
9. 继承
   * C++ name hide 父类overload 却子类会被隐蔽掉 Java报错没有name hide
   * 父类的成员变量和子类成员变量无关 java和c++一致
   * 初始化顺序
     * 父类静态 -> 子类静态 -> 父类初始化（做完:） -> 子类初始化
10. 多态
   * java一直在upcast 动态绑定 运行时确定 但static/私有函数/final函数一定是静态绑定

11. System.out.Println(true?Integer.ValueOf(1):Double.valueOf(2)); //course/Pitfall.java
    * 编译期确定overload，运行期确定overwrite
    * ValueOf会发生函数调用，和true？1:2.0 不一样 System.out.println(true ? 1 : 2.0);（额，JDK11这个也是1.0）
    * Println重载，所以根据情况，会调用double类型
12. Exception
    * 网络流、另一个进程在写，UNIX键盘等“file” 获取不了文件大小
    * -Xms100m JVM初始分配的堆内存 -Xmx1G JVM最大允许分配的堆内存，按需分配
    * try catch匹配原则 在能捕捉到的地方被catch main没有结束程序报错
    * 子类对象可以是父类对象
    * java异常声明 C++也有
      1. 编译器静态检查，看是否会抛异常，不能瞒报异常：否则会编译不过； 可以虚假声明会抛有些异常：调用此函数人必须处理异常或者继续声明 
      2. RuntimeException 没必要异常声明
      3. 子类override父类函数  
         A p =new B(); p.f(); // B抛的异常必须比A抛的异常少
      4. 子类构造函数和父类构造函数
         子类抛的异常要求比父类构造函数多：隐式调用父类构造函数 子类声明的异常应该是父类的超集 super又不能放在try里
      5. 子类继承父类，实现接口
         抛出的异常应该是父类那个函数和接口中那个函数的交集 
      6. 构造函数里抛异常
         C++： 语法正确，但可能无法正确回收内存和资源，RAII机制  另外析构函数不能抛出异常
         Java：内存回收安全，手动回收资源
      7. 
    * finally 永远都要执行 文件等需要关闭
    * Rethrow 
    * Throwable -> Error
                -> Exception -> Runtime Exception
                             -> IOException
