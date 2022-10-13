package RangeSet;


import java.util.Map;

public interface RangeMapInterface<T extends Comparable<T>, C>{

    public Map<Range<T>,C> getMap();


    public Range<T> getKey(C value);
    public void add(Range<T> range, C value);

    public void remove(C value);

    public C getValue(Range<T> value);

    public int getSize();

}
