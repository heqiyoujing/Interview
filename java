一、避免空指针
    Object object = null;
    String string = object.toString();、
    
    NullPointExecption-> RuntimeExecption-> Execption-> Throwable
    
    (1)字符串比较，常量放前面
    if(status.equals(SUCCESS))-> if(SUCCESS.equals(status))
    (2)初始化默认值
    User user = new User();
    String = StringUtils.EMPTY;
    (3)返回空集合
    return list== null?new ArrayList():list;
二、Java异常机制
    Throwable
      Error
      Exception(需要捕捉或抛出）
        ClassNotFoundExecption(需要捕捉或抛出）
        CloneNotSupportedExecption(需要捕捉或抛出）
        IOExecption(需要捕捉或抛出）
          EOFExeciton(需要捕捉或抛出）
          FileNotFoundExecption(需要捕捉或抛出）
          MalformedURLExecption(需要捕捉或抛出）
          UnKnownHost
        RuntimeExecption
          ArithmeticExecption
          ClassCastExecption
          IllgalArgumentExecption
          IllgalStateExecption
          IndexOutOfBoundsExecption
          NoSushElemtmentExecption
          NullPointerExecption
 三、HashMap相关
    1.HashMap中为什么用红黑树?
    之所以选择红黑树是为了解决二叉查找树的缺陷：二叉查找树在特殊情况下会变成一条线性结构（这就跟原来使用链表结构一样了，造成层次很深的问题）
    遍历查找会非常慢。而红黑树在插入新数据后可能需要通过左旋、右旋、变色这些操作来保持平衡。引入红黑树就是为了查找数据快，解决链表查询深度的问题。
    我们知道红黑树属于平衡二叉树，为了保持“平衡”是需要付出代价的，但是该代价所损耗的资源要比遍历线性链表要少。
    所以当长度大于8的时候，会使用红黑树；如果链表长度很短的话，根本不需要引入红黑树，引入反而会慢。
    
    什么是红黑树？
      每个节点非红即黑
      根节点总是黑色的
      如果节点是红色的，则它的子节点必须是黑色的（反之不一定）
      每个叶子节点都是黑色的空节点（NIL节点）
      从根节点到叶节点或空子节点的每条路径，必须包含相同数目的黑色节点（即相同的黑色高度）
    2.新调整 HashMap 大小存在什么问题吗？
    因为如果两个线程都发现 HashMap 需要重新调整大小了，它们会同时试着调整大小。在调整大小的过程中，存储在链表中的元素的次序会反过来。
    因为移动到新的 bucket 位置的时候，HashMap 并不会将元素放在链表的尾部，而是放在头部。这是为了避免尾部遍历（tail traversing）。
    如果条件竞争发生了，那么就死循环了。多线程的环境下不使用 HashMap。
    为什么多线程会导致死循环，它是怎么发生的？
    3.HashMap 与 HashTable 区别
    默认容量不同，扩容不同
    线程安全性：HashTable 安全
    效率不同：HashTable 要慢，因为加锁
    4.CocurrentHashMap
    CocurrentHashMap（JDK 1.7）：是由 Segment 数组和 HashEntry 数组和链表组成。
        Segment 是基于重入锁（ReentrantLock）：一个数据段竞争锁。
    CocurrentHashMap（JDK 1.8）：CocurrentHashMap 抛弃了原有的 Segment 分段锁，采用了 CAS + synchronized 来保证并发安全性。
        其中的 val next 都用了 volatile 修饰，保证了可见性。
        最大特点是引入了 CAS：借助 Unsafe 来实现 native code。CAS有3个操作数，内存值 V、旧的预期值 A、要修改的新值 B。
        当且仅当预期值 A 和内存值 V 相同时，将内存值V修改为 B，否则什么都不做。Unsafe 借助 CPU 指令 cmpxchg 来实现。
    CAS 会出现的问题：ABA
    解决：对变量增加一个版本号，每次修改，版本号加 1，比较的时候比较版本号。
    
四、
