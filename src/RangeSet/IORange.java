package RangeSet;

import java.util.Iterator;
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
        System.out.print(rangeSet.toString());
    }


    public <A extends Comparable<T>,C> void printMap(RangeMapInterface<T,C> rangeMap) {
        for (Map.Entry<Range<T>, C> entry : rangeMap.getEntryKeySet()) {
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
        rangeSet.add(Range.open(15,19));
        printerSet.printSet(rangeSet);
        System.out.println("\n---------------------------------------------------------------");
        System.out.println("\nContains Range [1..5): ");
        System.out.println(rangeSet.contains(Range.closedOpen(1,5)));


//        System.out.println("EBANAYA HERNYA");
//        Range<Integer> r1 = Range.closedOpen(1,5);
//        Range<Integer> r2 = Range.closedOpen(1,5);
//        System.out.println(r2.isRangeInRange(r1));

        System.out.println("\nContains Range [17..18): ");
        System.out.println(rangeSet.contains(Range.closedOpen(17,18)));

        System.out.println("\nContains Range [7..9]: ");
        System.out.println(rangeSet.contains(Range.closed(7,9)));
        System.out.println();

        System.out.println("\nContains Range 18: ");
        System.out.println(rangeSet.contains(Range.point(18)));
        System.out.println();

        System.out.println("Remove Point 18: ");
        rangeSet.remove(Range.point(18));
        printerSet.printSet(rangeSet);

        System.out.println("\n##########################################################################################");

//-------------------------------------------------------------------------------------------------------------------------------------

        RangeMapInterface<String,Integer> rangeMap = new  RangeMap<String, Integer>();
        IORange<String> printerMap = new IORange<String>();

        System.out.println("\n---RangeMap---\n");

        rangeMap.add(Range.closed("A","C"),1);
        rangeMap.add(Range.closed("D","F"),2);
        rangeMap.add(Range.closed("G","I"),3);
        rangeMap.add(Range.closed("B","F"),15);
        printerMap.printMap(rangeMap);
        System.out.println();

        System.out.println("Remove element with value 2: ");
        rangeMap.remove(2);
        printerMap.printMap(rangeMap);
        System.out.println();

        System.out.println("Which value is I: ");
        System.out.println(rangeMap.getElement("I"));
        System.out.println();
        
        System.out.println("\nGet element by value 3: ");
        printerMap.printRange(rangeMap.getKey(3));
        System.out.println();

        System.out.println("\nGet element by key [\"G\"..\"I\"]: ");
        System.out.println(rangeMap.getElement(Range.closed("G","I")));

    }
}
