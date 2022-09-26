package RangeSet;

import java.util.HashMap;
import java.util.Map;

public class RangeMap<T extends Comparable<T>> implements RangeMapInterface {
    private final HashMap<Range<T>, String> rangeMap = new HashMap<>();


    @Override
    public int add(Range range, String value) {
        if(rangeMap.isEmpty()){
            rangeMap.put(range,value);
            return 0;
        }else{
            for (Map.Entry<Range<T>, String> entry : rangeMap.entrySet()){
                if(range.canJoinRanges(entry.getKey())){
                    return 1;
                }
            }
            rangeMap.put(range,value);
        }
        return 0;
    }

    @Override
    public int remove(String value) {
        for (Map.Entry<Range<T>, String> entry : rangeMap.entrySet()){
            if(entry.getValue().equals(value)){
                rangeMap.remove(entry.getKey());
                return 0;
            }
        }
        return 1;
    }

    public String get(T index) {
        for (Map.Entry<Range<T>, String> entry : rangeMap.entrySet()){
            if(entry.getKey().numInRange(index)){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public int getSize() {
        return rangeMap.size();
    }

    @Override
    public void printRanges() {
        for (Map.Entry<Range<T>, String> entry : rangeMap.entrySet()) {
            entry.getKey().printRange();
            System.out.println("--> "+entry.getValue());
        }
    }
}
