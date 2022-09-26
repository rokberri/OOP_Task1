package RangeSet;

public interface RangeMapInterface<T extends Comparable<T>>{

    public int add(Range range,String value);

    public int remove(String value);

//    public String get(T value);

    public int getSize();

    public void printRanges();

}
