package RangeSet;


import java.util.Map;
import java.util.Set;

public interface RangeMapInterface<T extends Comparable<T>, C>{

    public Set<Map.Entry<Range<T>, C>> getEntryKeySet();
    public Range<T> getKey(C value);
    public void add(Range<T> range, C value);

    public void remove(C value);
    public void remove(T value);
    private boolean containsPoint(T point);


    public C getElement(T value);
    public C getElement(Range<T> range);

    public int getSize();

}
