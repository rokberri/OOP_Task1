package RangeSet;

public interface RangeSetInterface<T extends Comparable<T>> {
    public void add(Range<T> range);

    public void remove(Range<T> range);

    public Range<T> getEl(int index);

    public int getSize();

    public boolean contains(Range<T> range);

}
