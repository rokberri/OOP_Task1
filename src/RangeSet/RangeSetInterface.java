package RangeSet;

public interface RangeSetInterface<T extends Comparable<T>>{
    public void add(Range<T> range);

    public void remove(Range<T> range);

    public int getSize();

    public boolean containsPoint(T point);

    public boolean contains(Range<T> range);

    public String toString();
}
