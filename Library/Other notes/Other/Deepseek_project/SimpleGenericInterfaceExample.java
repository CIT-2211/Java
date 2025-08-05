// 泛型接口：要求实现类必须实现compareTo方法
interface ComparableContainer<T extends Comparable<T>> {
    /**
     * 添加元素
     * @param item 要添加的元素
     */
    void add(T item);
    
    /**
     * 获取最大值
     * @return 容器中的最大元素
     */
    T getMax();
    
    /**
     * 获取最小值
     * @return 容器中的最小元素
     */
    T getMin();
}

// 实现类：简单容器
class SimpleContainer<T extends Comparable<T>> implements ComparableContainer<T> {
    private T[] items;
    private int size;
    
    @SuppressWarnings("unchecked")
    public SimpleContainer(int capacity) {
        // 创建泛型数组（实际开发中更推荐使用List）
        items = (T[]) new Comparable[capacity];
        size = 0;
    }
    
    @Override
    public void add(T item) {
        if (size < items.length) {
            items[size++] = item;
        } else {
            System.out.println("容器已满!");
        }
    }
    
    @Override
    public T getMax() {
        if (size == 0) return null;
        
        T max = items[0];
        for (int i = 1; i < size; i++) {
            if (items[i].compareTo(max) > 0) {
                max = items[i];
            }
        }
        return max;
    }
    
    @Override
    public T getMin() {
        if (size == 0) return null;
        
        T min = items[0];
        for (int i = 1; i < size; i++) {
            if (items[i].compareTo(min) < 0) {
                min = items[i];
            }
        }
        return min;
    }
}

public class SimpleGenericInterfaceExample {
    public static void main(String[] args) {
        // 使用String类型（String实现了Comparable）
        ComparableContainer<String> stringContainer = new SimpleContainer<>(3);
        stringContainer.add("Apple");
        stringContainer.add("Banana");
        stringContainer.add("Cherry");
        
        System.out.println("最大字符串: " + stringContainer.getMax()); // Cherry
        System.out.println("最小字符串: " + stringContainer.getMin()); // Apple
        
        // 使用Integer类型（Integer实现了Comparable）
        ComparableContainer<Integer> numberContainer = new SimpleContainer<>(4);
        numberContainer.add(50);
        numberContainer.add(20);
        numberContainer.add(80);
        numberContainer.add(30);
        
        System.out.println("最大数字: " + numberContainer.getMax()); // 80
        System.out.println("最小数字: " + numberContainer.getMin()); // 20
        
        // 尝试使用不可比较的类型 - 编译错误！
        /*
        class Person {} // 未实现Comparable
        
        ComparableContainer<Person> personContainer = new SimpleContainer<>(2);
        personContainer.add(new Person());
        */
    }
}