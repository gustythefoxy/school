package l3_arraybag;

public class ArrayBag<T> implements BagInterface<T>{
    
    private final T[] bag;
    private final static int DEFAULT_CAPACITY = 25;
    private int numberOfEntries;

    public ArrayBag() {
        bag = (T[]) new Object[DEFAULT_CAPACITY];
        numberOfEntries = 0;
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries >= DEFAULT_CAPACITY;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean add(T newEntry) {
        
        if(isFull() || newEntry == null){
            return false;
        }
        else{
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
            return true;
        }
    }

    @Override
    public T remove(){
        if (isEmpty()){
            return null;
        }
        T removedEntry = bag[numberOfEntries - 1];
        bag[numberOfEntries - 1] = null;
        numberOfEntries--;
        return removedEntry;
    }

    @Override
    public boolean remove(T anEntry) {
        
        for(int i = 0; i < numberOfEntries; i++){
            if(bag[i].equals(anEntry)){
                bag[i] = bag[numberOfEntries - 1]; //replace withb the last item
                bag[numberOfEntries] = null;
                numberOfEntries--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        while(!isEmpty()){
            remove();
        }
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        
        int freq = 0;
        for(int i = 0; i < numberOfEntries; i++){
            if(bag[i].equals(anEntry)){
                freq++;
            }
        }
        return freq;
    }

    @Override
    public boolean contains(T anEntry) {
        
        for(int i = 0; i < numberOfEntries; i++){
            if(bag[i].equals(anEntry)){
                return true;
            }
        }
        return false;
    }

    @Override
    public T[] toArray() {
        
        T[] bagClone = (T[]) new Object[numberOfEntries];
        for(int i = 0; i < numberOfEntries; i++){
            bagClone[i] = bag[i];
        }
        return bagClone;
        
//        return Arrays.copyOf(bag, numberOfEntries); //more efficient but need to import Array class
    }
    
    //Question 2
    @Override
    public BagInterface<T> union(BagInterface<T> otherBag){
        
        ArrayBag<T> everything = new ArrayBag<>(); // New empty bag with default capacity

        // Add all elements from this bag
        T[] thisBagArray = this.toArray();
        for (T item : thisBagArray) {
            everything.add(item);
        }

        // Add all elements from the other bag
        T[] otherBagArray = otherBag.toArray();
        for (T item : otherBagArray) {
            everything.add(item);
        }

        return everything;
    }
    
    //Question 3
    @Override
    public BagInterface<T> intersection(BagInterface<T> otherBag){
        
        BagInterface<T> commonItems = new ArrayBag<>(); // New empty bag with default capacity
        
        BagInterface<T> tempThisBag = new ArrayBag<>(); // Temporary this bag with default capacity to handle content changes
        for (T item : this.toArray()) {
            tempThisBag.add(item);
        }
        
        for (T item : otherBag.toArray()){
            if(tempThisBag.contains(item)){ //Checks if tempThisBag has the same item as in otherBag
                commonItems.add(item);
                tempThisBag.remove(item); //To avoid adding unnecessary duplicates
            }
        }

        return commonItems;
    }
    
    //Question 4
    @Override
    public BagInterface<T> difference(BagInterface<T> otherBag){
        
        // New empty bag with default capacity
        BagInterface<T> leftOver = new ArrayBag<>();
        
        // Retrieves the common elements
        BagInterface<T> commonItem = intersection(otherBag);
        
        // Adds elements from this bag but skips common items
        for (T item : this.toArray()) {
            if(commonItem.contains(item)){
                commonItem.remove(item); // Prevent skipping more than needed; duplicates are removed according to their frequencies in commonItem
                continue;
            }
            leftOver.add(item);
        }
        return leftOver;
    }
}
