1. ArrayList与List与数组转换
```java
    ArrayList<Integer> ans = new ArrayList<>();
    ans.stream().mapToInt(Integer::intValue).toArray();
```