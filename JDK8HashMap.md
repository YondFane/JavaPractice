



# HashMap（JDK8）

**HashMap = 数组 + 链表 + 红黑树**

## **一、HashMap初始化**

HashMap默认容量大小为16，最大容量为1073741824（2^29）。

当链表长度大于等于8时并且Hash桶数量大于等于64时，链表转为红黑树。

当红黑树节点数量小于等于6时，红黑树转为链表。

```java
    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
	
    /**
     * The bin count threshold for using a tree rather than list for a
     * bin.  Bins are converted to trees when adding an element to a
     * bin with at least this many nodes. The value must be greater
     * than 2 and should be at least 8 to mesh with assumptions in
     * tree removal about conversion back to plain bins upon
     * shrinkage.
     */
    static final int TREEIFY_THRESHOLD = 8;// 链表转为红黑树阈值

    /**
     * The bin count threshold for untreeifying a (split) bin during a
     * resize operation. Should be less than TREEIFY_THRESHOLD, and at
     * most 6 to mesh with shrinkage detection under removal.
     */
    static final int UNTREEIFY_THRESHOLD = 6; // 红黑树转为链表阈值
	
    /**
     * The smallest table capacity for which bins may be treeified.
     * (Otherwise the table is resized if too many nodes in a bin.)
     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
     * between resizing and treeification thresholds.
     */
    static final int MIN_TREEIFY_CAPACITY = 64; // 链表转为红黑树Hash桶数量阈值
```



**new HashMap（容量 initialCapacity，负载因子loadFactor）**

负载因子：当前长度 > 容量 * 因子时进行扩容。负载因子过大会导致hash桶中的链表过长，查找键值对时间复杂度增高，过小会导致hash桶的数量过多，空间复杂度会增高。

threshold : 阈值

threshold = 容量 * 因子；

扩容时 threshold  << 1 进行double双倍容量扩容

```java
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }
```

**tableSizeFor()方法会根据new HashMap(int initialCapacity, float loadFactor)传入的初始容量initialCapacity进行调整，获取最接近 2^n 次方的值。**

**例如传入20，那么return返回的是32**

```java
	/**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
```

**Hash桶**

```java
    /**
     * The table, initialized on first use, and resized as
     * necessary. When allocated, length is always a power of two.
     * (We also tolerate length zero in some operations to allow
     * bootstrapping mechanics that are currently not needed.)
     */
    transient Node<K,V>[] table;
```

**HashMap数组的链表结构**

```java
	/**
     * Basic hash bin node, used for most entries.  (See below for
     * TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
     */
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;
    }
```

**HashMap红黑树结构**

```java
/**
 * Entry for Tree bins. Extends LinkedHashMap.Entry (which in turn
 * extends Node) so can be used as extension of either regular or
 * linked node.
 */
static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
    TreeNode<K,V> parent;  // red-black tree links
    TreeNode<K,V> left;
    TreeNode<K,V> right;
    TreeNode<K,V> prev;    // needed to unlink next upon deletion
    boolean red;
    TreeNode(int hash, K key, V val, Node<K,V> next) {
        super(hash, key, val, next);
    }
}
```

## **二、增加元素**

### 1、执行put方法

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
```

### 2、先调用hash(key)方法根据hash算法将key的hash值返回。

```java
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

### 3、接着调用putVal()方法

```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    // 1、如果 table为null 或 table长度为0，那么调用resize()方法对table数组的初始化（第一次扩容）
    // new HashMap()创建实例后，在第一次put时对数组进行初始化操作（扩容）
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    // 2、(容量 - 1) & hash 值获取table数组的index下标，如果table[index]为null就对赋值一个新的Node链表
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    // 3、table[index]不为null，那么就进行插入操作
    else {
        Node<K,V> e; K k;
        // 3-1、链头 或 红黑树树顶 key相同情况
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        // 3-2、判断当前table[index]是否为红黑树结构
        else if (p instanceof TreeNode)
            // 3-2-1、强转为TreeNode，执行(TreeNode)putTreeVal()方法进行添加
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            // 3-3、链表结构
            // 遍历链表
            for (int binCount = 0; ; ++binCount) {
                // 遍历到链表尾部，插入K,V
                if ((e = p.next) == null) {
                    // 链表中无相同key，直接将新节点插入到链表尾部
                    p.next = newNode(hash, key, value, null);
                    // 链表节点数量大于等于8时，转为红黑树结构
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        // 链表转红黑树 (数组长度小于64是不会转成红黑树的)
                        treeifyBin(tab, hash);
                    break;
                }
                // 如果key在链表中存在
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        // 3-1-1 链表头发现key相同或红黑树树顶发现key相同 
        // 3-2-1 红黑树中存在key相同的节点
        // 3-3-1 链表中存在key相同的节点
        // 据onlyIfAbsent布尔值判断是否替换对应的value值
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    // 存储容量是否大于临界值 （容量 * 负载因子）
    if (++size > threshold)
        // 扩容
        resize();
    afterNodeInsertion(evict);
    return null;
}
```

**红黑树的putTreeVal()**

```java
		/**
         * Tree version of putVal.
         */
        final TreeNode<K,V> putTreeVal(HashMap<K,V> map, Node<K,V>[] tab,
                                       int h, K k, V v) {
            Class<?> kc = null;
            boolean searched = false;
            TreeNode<K,V> root = (parent != null) ? root() : this;
            for (TreeNode<K,V> p = root;;) {
                int dir, ph; K pk;
                if ((ph = p.hash) > h)
                    dir = -1;
                else if (ph < h)
                    dir = 1;
                else if ((pk = p.key) == k || (k != null && k.equals(pk)))
                    // 红黑树中存在相同的key，直接返回
                    return p;
                else if ((kc == null &&
                          (kc = comparableClassFor(k)) == null) ||
                         (dir = compareComparables(kc, k, pk)) == 0) {
                    if (!searched) {
                        TreeNode<K,V> q, ch;
                        searched = true;
                        if (((ch = p.left) != null &&
                             (q = ch.find(h, k, kc)) != null) ||
                            ((ch = p.right) != null &&
                             (q = ch.find(h, k, kc)) != null))
                            return q;
                    }
                    dir = tieBreakOrder(k, pk);
                }

                TreeNode<K,V> xp = p;
                if ((p = (dir <= 0) ? p.left : p.right) == null) {
                    Node<K,V> xpn = xp.next;
                    // 添加叶节点
                    TreeNode<K,V> x = map.newTreeNode(h, k, v, xpn);
                    if (dir <= 0)
                        xp.left = x;
                    else
                        xp.right = x;
                    xp.next = x;
                    x.parent = x.prev = xp;
                    if (xpn != null)
                        ((TreeNode<K,V>)xpn).prev = x;
                    // 平衡红黑树
                    moveRootToFront(tab, balanceInsertion(root, x));
                    return null;
                }
            }
        }
```

## **三、HashMap数组扩容**

当数组元素大于等于阈值时，就会调用resize()方法进行数组扩容，扩容完成后数组长度为原来的**两倍**。

```java
/**
 * Initializes or doubles table size.  If null, allocates in
 * accord with initial capacity target held in field threshold.
 * Otherwise, because we are using power-of-two expansion, the
 * elements from each bin must either stay at same index, or move
 * with a power of two offset in the new table.
 *
 * @return the table
 */
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    int oldThr = threshold;
    int newCap, newThr = 0;
    if (oldCap > 0) {
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        // 扩容为之前的两倍
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                 oldCap >= DEFAULT_INITIAL_CAPACITY)
            // 阈值为之前的两倍
            newThr = oldThr << 1; // double threshold
    }
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    else {               // zero initial threshold signifies using defaults
        // 阈值为0，容量和阈值使用默认值 也就是16和12
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                  (int)ft : Integer.MAX_VALUE);
    }
    // 新阈值
    threshold = newThr;
    // 新数组
    @SuppressWarnings({"rawtypes","unchecked"})
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    // 对之前的数组进行处理
    if (oldTab != null) {
        // 遍历原来的数组
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                // 置null
                oldTab[j] = null;
                // 空链表
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                // 红黑树
                else if (e instanceof TreeNode)
                    // 拆分红黑树，拆成两颗树，然后映射都新数组中
                    // 拆分的树节点总数如果小于等于6，就会转化为链表结构
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                // 链表
                else { // preserve order
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    // 拆分链表，根据 hash & oldCap == 0 将链表拆为两个链表（低位链表、高位链表）
                    do {
                        next = e.next;
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    if (loTail != null) {
                        loTail.next = null;
                        // 低位链表依旧放在原来的数组下标位置上
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        // 高位链表放在对应的数组增加长度位置上 
                        // 例如原来数组长度为16,扩容后长度为32，原来位置3，现在位置为20
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```

**扩容时红黑树拆分**

```java
/**
 * Splits nodes in a tree bin into lower and upper tree bins,
 * or untreeifies if now too small. Called only from resize;
 * see above discussion about split bits and indices.
 *
 * @param map the map
 * @param tab the table for recording bin heads
 * @param index the index of the table being split
 * @param bit the bit of hash to split on
 */
final void split(HashMap<K,V> map, Node<K,V>[] tab, int index, int bit) {
    TreeNode<K,V> b = this;
    // Relink into lo and hi lists, preserving order
    TreeNode<K,V> loHead = null, loTail = null;
    TreeNode<K,V> hiHead = null, hiTail = null;
    int lc = 0, hc = 0;
    for (TreeNode<K,V> e = b, next; e != null; e = next) {
        next = (TreeNode<K,V>)e.next;
        e.next = null;
        // 根据hash & bit == 0，将红黑树拆成低位树和高位树
        if ((e.hash & bit) == 0) {
            if ((e.prev = loTail) == null)
                loHead = e;
            else
                loTail.next = e;
            loTail = e;
            ++lc;
        }
        else {
            if ((e.prev = hiTail) == null)
                hiHead = e;
            else
                hiTail.next = e;
            hiTail = e;
            ++hc;
        }
    }

    if (loHead != null) {
        // 红黑树节点总数小于等于6 转为链表形式 UNTREEIFY_THRESHOLD：6
        if (lc <= UNTREEIFY_THRESHOLD)
            tab[index] = loHead.untreeify(map);
        else {
            tab[index] = loHead;
            if (hiHead != null) // (else is already treeified)
                loHead.treeify(tab);
        }
    }
    if (hiHead != null) {
        // 红黑树节点总数小于等于6 转为链表形式
        if (hc <= UNTREEIFY_THRESHOLD)
            tab[index + bit] = hiHead.untreeify(map);
        else {
            tab[index + bit] = hiHead;
            if (loHead != null)
                hiHead.treeify(tab);
        }
    }
}
```