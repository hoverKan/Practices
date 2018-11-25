package com.sino.test;

import java.util.HashMap;

/**
 * @author Hover Kan
 * @create 2018-11-20 下午9:04
 *
 * HashMap 源码解释：
 *
 *
 */
public class TestHashMap {

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        /**
         * 在 HashMap 中定义了几个常量:
         *
         * static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
         * static final int MAXIMUM_CAPACITY = 1 << 30;
         * static final float DEFAULT_LOAD_FACTOR = 0.75f;
         * static final int TREEIFY_THRESHOLD = 8;
         * static final int UNTREEIFY_THRESHOLD = 6;
         * static final int MIN_TREEIFY_CAPACITY = 64;
         * 依次解释以上常量:
         *
         * DEFAULT_INITIAL_CAPACITY:
         *          初始容量，也就是默认会创建 16 个箱子，箱子的个数不能太多或太少。如果太少，很容易触发扩容，如果太多，遍历哈希表会比较慢。
         * MAXIMUM_CAPACITY:
         *          哈希表最大容量，一般情况下只要内存够用，哈希表不会出现问题。
         * DEFAULT_LOAD_FACTOR:
         *          默认的负载因子。因此初始情况下，当键值对的数量大于 16 * 0.75 = 12 时，就会触发扩容。
         * TREEIFY_THRESHOLD:
         *          如果哈希函数不合理，即使扩容也无法减少箱子中链表的长度，
         *          因此 Java 的处理方案是当链表太长时，转换成红黑树。这个值表示当某个箱子中，链表长度大于 8 时，有可能会转化成树。
         * UNTREEIFY_THRESHOLD:
         *          在哈希表扩容时，如果发现链表长度小于 6，则会由树重新退化为链表。
         * MIN_TREEIFY_CAPACITY:
         *          在转变成树之前，还会有一次判断，只有键值对数量大于 64 才会发生转换。
         *          这是为了避免在哈希表建立初期，多个键值对恰好被放入了同一个链表中而导致不必要的转化。
         */
    }
}
