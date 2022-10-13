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
        }else{
            System.out.println("ERROR");
        }
    }

    public void printSet(RangeSetInterface<T> rangeSet){
        for(int el=0; el<rangeSet.getSize(); el++){
            printRange(rangeSet.getEl(el));
        }
    }


    public <A extends Comparable<T>,C> void printMap(RangeMapInterface<T,C> rangeMap) {
        for (Map.Entry<Range<T>, C> entry : rangeMap.getMap().entrySet()) {
            printRange(entry.getKey());
            System.out.println("--> "+entry.getValue());
        }
    }

    public static void printTestExample(){
        RangeSetInterface<Integer> rangeSet = new RangeSet<Integer>();
        IORange<Integer> printerSet = new IORange<Integer>();

        System.out.println("---RangeSet---\n");


        rangeSet.add(Range.closedOpen(1,3));
        printerSet.printSet(rangeSet);
        System.out.println("\n---------------------------------------------------------------");
        rangeSet.add(Range.closedOpen(3,5));
        printerSet.printSet(rangeSet);
        System.out.println("\n---------------------------------------------------------------");
        rangeSet.add(Range.closedOpen(12,15));
        printerSet.printSet(rangeSet);
        System.out.println("\n---------------------------------------------------------------");
        rangeSet.add(Range.closedOpen(7,9));
        printerSet.printSet(rangeSet);
        System.out.println("\n---------------------------------------------------------------");
        System.out.println("\nContains Range [0..5]: ");
        System.out.println(rangeSet.contains(Range.closed(0,5)));

        System.out.println("\nContains Range [7..9]: ");
        System.out.println(rangeSet.contains(Range.closedOpen(7,9)));
        System.out.println();

        System.out.println("Remove Range [1..5): ");
        rangeSet.remove(Range.closedOpen(1,5));
        printerSet.printSet(rangeSet);
        System.out.println("\n##########################################################################################");

//-------------------------------------------------------------------------------------------------------------------------------------

        RangeMapInterface<String,Integer> rangeMap = new  RangeMap<String, Integer>();
        IORange<String> printerMap = new IORange<String>();



        System.out.println("\n---RangeMap---\n");

        rangeMap.add(Range.closed("A","C"),1);
        rangeMap.add(Range.closed("D","F"),2);
        rangeMap.add(Range.closed("G","I"),3);
        printerMap.printMap(rangeMap);
        System.out.println();

        System.out.println("Remove element with value 2: ");
        rangeMap.remove(2);
        printerMap.printMap(rangeMap);
        System.out.println();

        System.out.println("\nGet element by value 3: ");
        printerMap.printRange(rangeMap.getKey(3));
        System.out.println();

        System.out.println("\nGet element by key [\"G\"..\"I\"]: ");
        System.out.println(rangeMap.getValue(Range.closed("G","I")));

    }
}
