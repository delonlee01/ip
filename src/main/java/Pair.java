public class Pair<K, V> {
    private K first;
    private V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return this.first;
    }

    public V getSecond() {
        return this.second;
    }

    public void setSecond(V value) {
        this.second = value;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Pair) {
            @SuppressWarnings("rawtypes")
            Pair pair = (Pair) object;
            return this.first.equals(pair.getFirst()) && this.second.equals(pair.getSecond());
        }
        return false;
    }
}
