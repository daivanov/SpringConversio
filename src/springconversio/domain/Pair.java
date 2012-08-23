package springconversio.domain;

public class Pair<T1,T2>
{
    private final T1 key;
    private final T2 value;

    public Pair(T1 aKey, T2 aValue)
    {
        key   = aKey;
        value = aValue;
    }

    public T1 getKey()   { return key; }
    public T2 getValue() { return value; }
}