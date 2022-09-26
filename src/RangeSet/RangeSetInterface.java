package RangeSet;

public interface RangeSetInterface<T extends Comparable<T>> {
    public int add(Range<T> range);

    public int remove(int index);

    public Range<T> getEl(int index);

    public int getSize();

    public void printRanges();


}
