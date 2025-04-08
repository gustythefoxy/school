package l3_arraybag;

public class ArrayBagDemo {
    
    private static void testAdd(BagInterface<String> aBag, String[] content){
        
        System.out.print("Adding ");
        for(int i = 0; i < content.length; i++){
            System.out.print(content[i] + " ");
            aBag.add(content[i]);
        }
        System.out.println("");
    }
    
    private static <T> void displayBag(BagInterface<T> aBag){
        System.out.println("The bag contains " + aBag.getCurrentSize() + " string(s), as follows:");
        
        for(T item : aBag.toArray()){
            System.out.print(item.toString() + " ");
        }
        System.out.println("\n");
    }
    
    public static void main(String[] args) {
        
        System.out.println("bag1:");
        String[] content1 = {"A","A","B","A","C","A"};
        BagInterface<String> bag1 = new ArrayBag<>();
        testAdd(bag1, content1);
        displayBag(bag1);
        
        System.out.println("bag2:");
        String[] content2 = {"A","B","A","C","B","C","D","another string"};
        BagInterface<String> bag2 = new ArrayBag<>();
        testAdd(bag2, content2);
        displayBag(bag2);
        
        System.out.println("bag3, test the method union of bag1 and bag2:");
        BagInterface<String> bag3 = new ArrayBag<>();
        bag3 = bag1.union(bag2);
        displayBag(bag3);
        
        System.out.println("bag4, test the method intersection of bag1 and bag2:");
        BagInterface<String> bag4 = new ArrayBag<>();
        bag4 = bag1.intersection(bag2);
        displayBag(bag4);
        
        System.out.println("bag5, test the method difference of bag1 and bag2:");
        BagInterface<String> bag5 = new ArrayBag<>();
        bag5 = bag1.difference(bag2);
        displayBag(bag5);
    }
}
