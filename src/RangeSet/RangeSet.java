package RangeSet;


import java.util.ArrayList;
import java.util.List;

public class RangeSet<T extends Comparable<T>> implements RangeSetInterface<T>{
    private List<Range<T>> rangeSet = new ArrayList<>();


    private void makeSetUnic(){
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
    public void remove(Range<T> range) {
        if (contains(range)) {
            for (Range<T> item : rangeSet) {
                if(range.isRangeInRange(item)) {

                    if(range.compare(item)) {
                        rangeSet.remove(range);
                        break;
                    }
                    rangeSet.remove(item);
                    try {
                        if(item.getLowBound()!=range.getLowBound() && item.getHighBound()!=range.getHighBound()) {
                            rangeSet.add(new Range<T>(item.getLowBound(), range.getLowBound(), item.isClosedL(), !range.isClosedL()));
                            rangeSet.add(new Range<T>(range.getHighBound(), item.getHighBound(), !range.isClosedH(), item.isClosedH()));
                            break;
                        }
                        if(item.getLowBound()==range.getLowBound() && item.getHighBound()!=range.getHighBound()) {
                            rangeSet.add(new Range<T>(range.getHighBound(), item.getHighBound(), !range.isClosedH(), item.isClosedH()));
                            break;
                        }
                        if(item.getLowBound()!=range.getLowBound() && item.getHighBound()==range.getHighBound()) {
                            rangeSet.add(new Range<T>(item.getLowBound(), range.getLowBound(), item.isClosedL(), !range.isClosedL()));
                            break;
                        }
                    } catch (InvalidArgsException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


    @Override
    public void add(Range<T> range) {
        int positionToInsert = -1;
        if(rangeSet.isEmpty()){
            rangeSet.add(range);
        }else{
            for (int i = 0; i < rangeSet.size(); i++) {
                if (range.canJoinRanges(rangeSet.get(i))) {
                    Range<T> newRange = range.joinRanges(rangeSet.get(i));
                    rangeSet.remove(i);
                    rangeSet.add(i,newRange);
                    makeSetUnic();
                    break;
                }
                if (range.getLowBound().compareTo(rangeSet.get(i).getHighBound()) >= 0) {
                    positionToInsert = i + 1;
                }
            }
        }
        if(positionToInsert!=-1){
            rangeSet.add(positionToInsert,range);
        }
    }
    public int getSize(){
        return rangeSet.size();
    }

    @Override
    public boolean containsPoint(T point){
        for(Range<T> item : rangeSet){
            if(item.isNumInRange(point)){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean contains(Range<T> range){
        for(Range<T> item : rangeSet ) {
            if(range.isRangeInRange(item)){
                return true;
            }
        }
        return false;
    }
    public String toString(){
        StringBuilder rez = new StringBuilder();
        for(Range<T> item : rangeSet){
            if(item.isClosedL()){
                rez.append('[');
            }else{
                rez.append('(');
            }
            rez.append(item.getLowBound());
            rez.append("..");
            rez.append(item.getHighBound());
            if(item.isClosedH()){
                rez.append(']');
            }else{
                rez.append(')');
            }
        }
        return rez.toString();
    }
}
