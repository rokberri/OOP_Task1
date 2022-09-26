package RangeSet;


public class Range<T extends Comparable<T>> {
    private T highBound;
    private T lowBound;

    //1-closed 0-open
    private boolean isClosedH;
    private boolean isClosedL;

    public boolean isClosedH() {
        return isClosedH;
    }

    public boolean isClosedL() {
        return isClosedL;
    }

    public T getHighBound() {
        return highBound;
    }

    public T getLowBound() {
        return lowBound;
    }

    public Range(T lowBound, T highBound, boolean isClosedL, boolean isClosedH) {
        if (lowBound.compareTo(highBound)<0) {
            this.highBound = highBound;
            this.lowBound = lowBound;
            this.isClosedH = isClosedH;
            this.isClosedL = isClosedL;
        }else{
            System.out.println("ERROR");
        }
    }
    private boolean higherThenLowBound(T num) {
        if (this.isClosedL) {
            if(this.lowBound.compareTo(num)<=0){
                return true;
            }
        }else{
            if (this.lowBound.compareTo(num)<0){
                return true;
            }
        }
        return false;
    }
    private boolean lowerThenHighBound( T num){
        if(this.isClosedH){
            if(this.highBound.compareTo(num)>=0){
                return true;
            }
        }else{
            if(this.highBound.compareTo(num)>0){
                return true;
            }
        }
        return false;
    }
    public boolean numInRange(T num){
        return higherThenLowBound(num) && this.lowerThenHighBound(num);
    }
    public boolean rangeInRange(Range<T> range){
        return range.higherThenLowBound(this.lowBound) && range.lowerThenHighBound(this.highBound);
    }

    public boolean canJoinRanges(Range<T> range2){
        if(this.rangeInRange(range2)){
            return true;
        }else {
            if(this.numInRange(range2.lowBound) || this.numInRange( range2.highBound)){
                return true;
            }
        }
        return false;
    }

    public Range<T> joinRanges( Range<T> range2){
        if(this.rangeInRange(range2)) {
            return range2;
        }
        if(range2.rangeInRange(this)){
            return this;
        }
        if(this.numInRange(range2.lowBound)){
            return new Range<T>(this.lowBound, range2.highBound, this.isClosedL,range2.isClosedH);
        }
        if(range2.numInRange(this.lowBound)){
            return new Range<T>(range2.lowBound, this.highBound, range2.isClosedL,this.isClosedH);
        }
        return new Range(0,1,false,false);
    }


    public int compare(Range<T> o) {
        if(lowBound==o.lowBound && highBound==o.highBound){
            if(isClosedL==o.isClosedL && isClosedH==o.isClosedH){
                return 0;
            }
        }
        return -1;
    }
}
