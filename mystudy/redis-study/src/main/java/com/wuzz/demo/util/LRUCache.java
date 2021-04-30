package com.wuzz.demo.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2021/4/26 11:09
 * @since 1.0
 **/
public class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private static final long serialVersionUID = -5672516463189218122L;

    /**容量*/
    private int capacity;

    /**
     * 构造一个缓存容器
     * @param capacity 容量（参数的指定参考 {@link java.util.HashMap}）
     */
    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    /**
     * 构造一个缓存容器，默认缓存大小为 16
     */
    public LRUCache() {
        this(1 << 4);
    }

    /**
     * 如果此地图应该删除其最老的条目，则返回true 。 在将新条目插入到地图中之后，此方法由put和putAll调用。
     * 它为实施者提供每次添加新的条目时删除最老条目的机会。
     * 如果地图代表一个缓存，这是非常有用的：它允许地图通过删除陈旧的条目来减少内存消耗。
     * 示例使用：此覆盖将允许地图长达100个条目，然后每次添加新条目时删除最老条目，保持100个条目的稳定状态。
     *<pre>
     *   private static final int MAX_ENTRIES = 100;
     *
     *   protected boolean removeEldestEntry(Map.Entry eldest) {
     *      return size() > MAX_ENTRIES;
     *   }
     * </pre>
     * 该方法通常不会以任何方式修改地图，而是允许地图按其返回值的指示进行修改。
     * 它被允许用于此方法来直接修改地图，但如果这样做的话，它必须返回false（指示地图不应试图任何进一步的修改）。
     * 从该方法中修改地图之后返回true的效果是未指定的。
     *
     * 这个实现只返回false （这样，这个地图就像一个法线贴图 - 最老的元素永远不会被删除）。
     * <p>
     * 以上来自Java API文档中的 LinkedHashMap 该方法的注释翻译。
     * 在重写该方法后，返回的结果参考于API中的示例使用。
     * </p>
     *
     * @param eldest 地图中最近插入的条目，或者如果这是访问顺序的地图，最近访问的条目。
     *              这是将被删除的条目，此方法返回true 。
     *               如果在put或putAll调用之前地图为空，导致此调用，则将是刚插入的条目;
     *               换句话说，如果地图包含单个条目，则最长条目也是最新的条目。
     * @return 在新插入数据时，返回true 就删除；false 就不删除
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public static void main(String[] args) {
        // 指定容量为10
        LRUCache<Integer, Integer> map = new LRUCache<>(10);

        int len = 11;
        for (int i = 1; i <= len; i++) {
            map.put(i,i);
        }

        System.out.println(map.get(1));
        System.out.println(map.get(3));
        map.forEach((key, value) -> System.out.println("key = " + key + ", value = " + value));
    }

}
