package RangeSet;


import java.util.Objects;


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
        } catch (InvalidArgsException e) {
            e.catchEX();
        }
        return null;
    }

    public static <T extends Comparable<T>> Range<T> open(T lowBound, T highBound){
        try {
            return new Range<T>(lowBound, highBound,false, false);
        } catch (InvalidArgsException e) {
            e.catchEX();
        }
        return null;
    }

    public static <T extends Comparable<T>> Range<T> closedOpen(T lowBound, T highBound){
        try {
            return new Range<T>(lowBound, highBound,true, false);
        } catch (InvalidArgsException e) {
            e.catchEX();
        }
        return null;
    }

    public static <T extends Comparable<T>> Range<T> openClosed(T lowBound, T highBound){
        try {
            return new Range<T>(lowBound, highBound,false, true);
        } catch (InvalidArgsException e) {
            e.catchEX();
        }
        return null;
    }
    public static <T extends Comparable<T>> Range<T> point(T bound){
        try {
            return new Range<T>(bound);
        } catch (InvalidArgsException e) {
            e.catchEX();
        }
        return null;
    }

    public Range(T lowBound, T highBound, boolean isClosedL, boolean isClosedH) throws InvalidArgsException {
        if (lowBound.compareTo(highBound) < 0) {
            this.highBound = highBound;
            this.lowBound = lowBound;
            this.isClosedH = isClosedH;
            this.isClosedL = isClosedL;
        }else{
            throw new InvalidArgsException("Invalid args");
        }
    }
    public Range(T bound) throws InvalidArgsException {
        this.highBound = bound;
        this.lowBound = bound;
        this.isClosedH = true;
        this.isClosedL = true;
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
        boolean inLowBound = false;
        boolean inHighBound = false;
        if(range.highBound!=this.highBound && range.lowBound!=this.lowBound) {
            return range.isHigherThenLowBound(this.lowBound) && range.isLowerThenHighBound(this.highBound);
        }else{
            if(range.getHighBound()==this.highBound && range.getLowBound()!=this.lowBound){
                if(range.isClosedH == this.isClosedH){
                    inHighBound = true;
                }
                inLowBound = range.isHigherThenLowBound(this.lowBound);
            }
            if(range.getHighBound()!=this.highBound && range.getLowBound()==this.lowBound){
                if(range.isClosedL == this.isClosedL){
                    inLowBound = true;
                }
                inHighBound =  range.isLowerThenHighBound(this.highBound);
            }
            if(range.getHighBound()==this.highBound && range.getLowBound()==this.lowBound){
                if(range.isClosedL == this.isClosedL){
                    inLowBound = true;
                }
                if(range.isClosedH == this.isClosedH){
                    inHighBound = true;
                }
            }
        }
        return inLowBound && inHighBound;
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
            } catch (InvalidArgsException e) {
                e.catchEX();
            }
        }
        if(range2.isNumInRange(this.lowBound) || this.isNumInRange(range2.highBound)){
            try {
                return new Range<T>(range2.lowBound, this.highBound, range2.isClosedL,this.isClosedH);
            } catch (InvalidArgsException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return new Range(0,1,false,false);//??????????????????????????????????????????
        } catch (InvalidArgsException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean compare(Range<T> o) {
        if(lowBound==o.lowBound && highBound==o.highBound){
            if(isClosedL==o.isClosedL && isClosedH==o.isClosedH){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range<?> range = (Range<?>) o;
        return isClosedH == range.isClosedH && isClosedL == range.isClosedL && Objects.equals(highBound, range.highBound) && Objects.equals(lowBound, range.lowBound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(highBound, lowBound, isClosedH, isClosedL);
    }
}
