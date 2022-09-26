import RangeSet.RangeSet;
import RangeSet.Range;
import RangeSet.IORange;


import RangeSet.Range;
import RangeSet.RangeMap;
import com.google.common.collect.TreeRangeSet;


public class MainAPP {
    public static void main(String[] args) {

// -----------------------------------------------------------------------------------------
        RangeSet<Integer> rangeSet = new RangeSet<Integer>();
        IORange<Integer> printer = new IORange<Integer>();


//        rangeSet.add(new Range<Integer>(1,3,true,false));
//        printer.printSet(rangeSet);
//        System.out.println("\n---------------------------------------------------------------");
//        rangeSet.add(new Range<Integer>(12,15,true,false));
//        printer.printSet(rangeSet);
//        System.out.println("\n---------------------------------------------------------------");
//        rangeSet.add(new Range<Integer>(2,7,true,false));
//        printer.printSet(rangeSet);
//        System.out.println("\n---------------------------------------------------------------");
//        rangeSet.add(new Range<Integer>(6,13,true,false));
//        printer.printSet(rangeSet);
//        System.out.println("\n---------------------------------------------------------------");



        rangeSet.add(new Range<Integer>(1,3,true,false));
        printer.printSet(rangeSet);
        System.out.println("\n---------------------------------------------------------------");
        rangeSet.add(new Range<Integer>(4,5,true,false));
        printer.printSet(rangeSet);
        System.out.println("\n---------------------------------------------------------------");
        rangeSet.add(new Range<Integer>(12,15,true,false));
        printer.printSet(rangeSet);
        System.out.println("\n---------------------------------------------------------------");
        rangeSet.add(new Range<Integer>(7,9,true,false));
        printer.printSet(rangeSet);
        System.out.println("\n---------------------------------------------------------------");


//-----------------------------------------------------------------------------------------
//        RangeMap<Integer, String> experienceRangeDesignationMap = TreeRangeMap.create();
//
//        experienceRangeDesignationMap.put(Range.closed(0, 2), "Associate");
//        experienceRangeDesignationMap.put(Range.closed(3, 5), "Senior Associate");
//        experienceRangeDesignationMap.put(Range.closed(6, 8),  "Vice President");
//        experienceRangeDesignationMap.put(Range.closed(9, 15), "Executive Director");
//
//        System.out.println(experienceRangeDesignationMap.get(5));
//-----------------------------------------------------------------------------------------
//        RangeMap<String> rangeMap = new RangeMap<String>();
//        IORange<String> printer = new IORange<String>();
//        rangeMap.add(new Range<String>("0","2",true,true),"A");
//        rangeMap.add(new Range<String>("3","5",true,true),"B");
//        rangeMap.add(new Range<String>("6","8",true,true),"C");
////        rangeMap.add(new Range<String>("A","C",true,true),"1");
////        rangeMap.add(new Range<String>("D","F",true,true),"2");
////        rangeMap.add(new Range<String>("G","I",true,true),"3");
//        System.out.println(rangeMap.getSize());
//        printer.printMap(rangeMap);

//        System.out.println(rangeMap.remove("A"));
//
//        rangeMap.printRanges();
//
//        System.out.println(rangeMap.get("5"));

    }
}
