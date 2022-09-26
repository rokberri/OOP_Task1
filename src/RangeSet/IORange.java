package RangeSet;

import java.util.Map;

public class IORange<T extends Comparable<T>> {
    public void printRange(Range<T> range){
        if(range!=null) {
            char openBracket = '(';
            char closeBracket = ')';
            if (range.isClosedL()) {
                openBracket = '[';
            }
            if (range.isClosedH()) {
                closeBracket = ']';
            }
            System.out.print(openBracket);
            System.out.print(range.getLowBound() + ".." + range.getHighBound());
            System.out.print(closeBracket);
        }
    }

    public void printSet(RangeSet<T> rangeSet){
        for(int el=0; el<rangeSet.getSize(); el++){
            printRange(rangeSet.getEl(el));
        }
    }


    public void printMap(RangeMap<T> rangeMap) {
        for (Map.Entry<Range<T>, String> entry : rangeMap.getEntryKeySet()) {
            printRange(entry.getKey());
            System.out.println("--> "+entry.getValue());
        }
    }
}
