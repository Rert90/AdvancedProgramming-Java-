public class Pair<K,V>{
    private final K key;
    private final V value;
    public Pair(K key,V value){
        this.key=key;
        this.value=value;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
