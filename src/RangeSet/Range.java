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


    public static <T extends Comparable<T>> Range<T> closed(T lowBound, T highBound){
        try {
            return new Range<T>(lowBound, highBound,true, true);
        } catch (Exceptions.InvalidArgsException e) {
            e.catchEx();
        }
        return null;
    }

    public static <T extends Comparable<T>> Range<T> open(T lowBound, T highBound){
        try {
            return new Range<T>(lowBound, highBound,false, false);
        } catch (Exceptions.InvalidArgsException e) {
            e.catchEx();
        }
        return null;
    }

    public static <T extends Comparable<T>> Range<T> closedOpen(T lowBound, T highBound){
        try {
            return new Range<T>(lowBound, highBound,true, false);
        } catch (Exceptions.InvalidArgsException e) {
            e.catchEx();
        }
        return null;
    }

    public static <T extends Comparable<T>> Range<T> openClosed(T lowBound, T highBound){
        try {
            return new Range<T>(lowBound, highBound,false, true);
        } catch (Exceptions.InvalidArgsException e) {
            e.catchEx();
        }
        return null;
    }

    public Range(T lowBound, T highBound, boolean isClosedL, boolean isClosedH) throws Exceptions.InvalidArgsException {
        if (lowBound.compareTo(highBound) < 0) {
            this.highBound = highBound;
            this.lowBound = lowBound;
            this.isClosedH = isClosedH;
            this.isClosedL = isClosedL;
        }else{
            throw new Exceptions.InvalidArgsException("Invalid args");
        }
    }
    private boolean isHigherThenLowBound(T num) {
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
    private boolean isLowerThenHighBound(T num){
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
    public boolean isNumInRange(T num){
        return isHigherThenLowBound(num) && this.isLowerThenHighBound(num);
    }
    public boolean isRangeInRange(Range<T> range){
        return range.isHigherThenLowBound(this.lowBound) && range.isLowerThenHighBound(this.highBound);
    }

    public boolean canJoinRanges(Range<T> range2){
        if(this.isRangeInRange(range2)){
            return true;
        }else {
            if(this.isNumInRange(range2.lowBound) || this.isNumInRange( range2.highBound)){
                return true;
            }
        }
        return false;
    }

    public Range<T> joinRanges( Range<T> range2){
        if(this.isRangeInRange(range2)) {
            return range2;
        }
        if(range2.isRangeInRange(this)){
            return this;
        }
        if(this.isNumInRange(range2.lowBound) || range2.isNumInRange(this.highBound)){
            try {
                return new Range<T>(this.lowBound, range2.highBound, this.isClosedL,range2.isClosedH);
            } catch (Exceptions.InvalidArgsException e) {
                e.catchEx();
            }
        }
        if(range2.isNumInRange(this.lowBound) || this.isNumInRange(range2.highBound)){
            try {
                return new Range<T>(range2.lowBound, this.highBound, range2.isClosedL,this.isClosedH);
            } catch (Exceptions.InvalidArgsException e) {
                e.catchEx();
            }
        }
        try {
            return new Range(0,1,false,false);//??????????????????????????????????????????
        } catch (Exceptions.InvalidArgsException e) {
            throw new RuntimeException(e);
        }
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
