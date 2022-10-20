package RangeSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RangeMap<T extends Comparable<T>, C> implements RangeMapInterface<T, C> {
    private Map<Range<T>, C> rangeMap = new HashMap<>();

    public Set<Map.Entry<Range<T>, C>> getEntryKeySet(){
        return rangeMap.entrySet();
    }

    @Override
    public Range<T> getKey(C value) {
        Range<T> curVal = null;
        for (Map.Entry<Range<T>, C> entry : rangeMap.entrySet()){
            if(entry.getValue().equals(value)){
               curVal = entry.getKey();
            }
        }
        return curVal;
    }

    public C getElement(T value){
        for (Map.Entry<Range<T>, C> entry : rangeMap.entrySet()){
            if(entry.getKey().isNumInRange(value)){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public C getElement(Range<T> range) {
        for (Map.Entry<Range<T>, C> entry : rangeMap.entrySet()){
            if(entry.getKey().compare(range)){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void add(Range<T> range, C value) {
        if(rangeMap.isEmpty()){
            rangeMap.put(range,value);
        }else{
            boolean hasTheSame = false;
            for (Map.Entry<Range<T>, C> entry : rangeMap.entrySet()){
                if(entry.getKey().isNumInRange(range.getLowBound()) || entry.getKey().isNumInRange(range.getHighBound())){
                    hasTheSame = true;
                }
            }
            if(!hasTheSame){
                rangeMap.put(range, value);
            }

        }
    }

    @Override
    public void remove(C value) {
        Set<Map.Entry<Range<T>, C>> map = rangeMap.entrySet();
        for (Map.Entry<Range<T>, C> entry : map){
            if(entry.getValue().equals(value)){
                rangeMap.remove(entry.getKey());
                break;
            }
        }
    }

    @Override
    public int getSize() {
        return rangeMap.size();
    }
}
