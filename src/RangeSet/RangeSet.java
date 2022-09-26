package RangeSet;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class RangeSet<T extends Comparable<T>> implements RangeSetInterface<T>{
    private final ArrayList<Range<T>> rangeSet = new ArrayList<>();

    private void isUnic(){
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
        int positionToInsert = 0;
        if(rangeSet.isEmpty()){
            rangeSet.add(range);
            return 0;
        }else{
            for (int i = 0; i < rangeSet.size(); i++) {
                if (range.canJoinRanges(rangeSet.get(i))) {
                    Range<T> newRange = range.joinRanges(rangeSet.get(i));
                    rangeSet.remove(i);
                    rangeSet.add(i,newRange);
                    isUnic();
                    return 0;
                }
                if(range.getLowBound().compareTo(rangeSet.get(i).getHighBound())>0){
                    positionToInsert=i+1;
                }
            }
        }
        rangeSet.add(positionToInsert,range);
        return 0;
    }




    public int getSize(){
        return rangeSet.size();
    }

    public int containsEl(Range<T> range) throws IndexOutOfBoundsException{
        int position = -1;
        for(int el=0; el<rangeSet.size(); el++){
            if(range.compare(rangeSet.get(el))==0){
                position=el;
            }
        }
        if(position==-1){
            throw new IndexOutOfBoundsException("Could not find such element");
        }
        return position;
    }

    public Range<T> findElement(Range<T> range)  {
        int position = 0;
        try {
            position = containsEl(range);
        } catch (IndexOutOfBoundsException er){
            er.getStackTrace();
        }
        return null;
    }

}
