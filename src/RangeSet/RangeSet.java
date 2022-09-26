package RangeSet;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class RangeSet<T extends Comparable<T>> implements RangeSetInterface<T>{
    private final ArrayList<Range<T>> rangeSet = new ArrayList<>();

    private void checkForUnic(){
        for (int i = 0; i < rangeSet.size(); i++) {
            for(int j=i+1; j< rangeSet.size(); j++) {
                Range<T> range1 = rangeSet.get(i);
                Range<T> range2 = rangeSet.get(j);
                if(range1.canJoinRanges(range2)){
                    Range<T> newRange = range1.joinRanges(range2);
                    rangeSet.remove(i);
                    rangeSet.add(i,newRange);
                    rangeSet.remove(j);
                }
            }
        }
    }
    public int remove(int index){
        rangeSet.remove(index);
        return 0;
    }

    public Range<T> getEl(int index){
        return rangeSet.get(index);
    }

    @Override
    public int add(Range<T> range) {
        if(rangeSet.isEmpty()){
            rangeSet.add(range);
            return 0;
        }else{
            for (int i = 0; i < rangeSet.size(); i++) {
                if (range.canJoinRanges(rangeSet.get(i))) {
                    Range<T> newRange = range.joinRanges(rangeSet.get(i));
                    rangeSet.remove(i);
                    rangeSet.add(i,newRange);
                    checkForUnic();
                    return 0;
                }
            }
        }
        rangeSet.add(range);
        return 0;
    }



    public int getSize(){
        return rangeSet.size();
    }
    public void printRanges(){
        for(Range<T> r : rangeSet){
            r.printRange();
        }
//        int el =0;
//        while (getEl(el)!=null){
//            getEl(el).printRange();
//            el++;
//            if(el==getSize()){
//                break;
//            }
//        }

    }
}
