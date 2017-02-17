import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Item[] items;
    private int head, tail, capacity, size;
    public Deque() {
        head = 0;
        tail = 0;
        size = 0;
        capacity = 1;
        items = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item){
        if (item == null){
            throw new NullPointerException();
        }

        if ( this.size == 0 ){
            this.head = 0;
            this.tail = 0;
            this.items[0] = item;
            this.size += 1;
            resize();
            return;
        }

        if ( this.head == 0 ) {
            this.head = this.capacity - 1;
        } else {
            this.head = this.head - 1 ;
        }

        this.items[this.head] = item;
        this.size += 1;
        resize();
    }

    public void addLast(Item item) {
        if ( item == null ) {
            throw new NullPointerException();
        }

        if ( this.size == 0 ){
            this.head = 0;
            this.tail = 0;
            this.items[0] = item;
            this.size += 1;
            resize();
            return;
        }

        this.tail = ( this.tail + 1 ) % this.capacity;
        this.items[this.tail] = item;
        this.size += 1;
        resize();
    }

    public Item removeFirst() {
        if (this.size == 0 ){
            throw new NoSuchElementException();
        }

        Item a = this.items[this.head];
        this.head = ( this.head + 1 ) % this.capacity;
        this.size -= 1;
        resize();
        return a;

    }

    public Item removeLast() {
        if (this.size == 0 ){
            throw new NoSuchElementException();
        }

        Item a = this.items[this.tail];

        if (this.tail == 0) {
            this.tail = this.capacity - 1;
        } else {
            this.tail = this.tail - 1 ;
        }

        this.size -= 1;
        resize();
        return a;

    }

    private void resize(){
        int old = this.capacity,i,j=0;
        if ( this.size == this.capacity){
            this.capacity = 2 * this.capacity;
        } else if ( ( this.capacity > 4) && this.size == ( this.capacity / 4 ) ) {
            this.capacity = this.capacity / 4;
        } else {
            return;
        }
        // System.out.println("called resize for size " + this.size + " capacity " + old);
        // System.out.println("head is " + this.head + " tail is " + this.tail);
        Item[] temp = (Item[]) new Object[this.capacity];
        for (i = this.head; i != this.tail; i = ( i + 1 ) % old) {
            // System.out.println("copying "+ this.items[i] +" from "+ i + " to" + j);
            temp[j] = this.items[i];
            j += 1;
        }
        temp[j] = this.items[i];
        //System.out.println(this.items[i]);
        this.items = temp;
        this.head = 0;
        this.tail = this.size - 1;
    }

    private void display(){
        System.out.println("Displaying data");
        for(int i=this.head; i!=this.tail; i = (i+1)%this.capacity){
            System.out.print(this.items[i]+" ");
        }
        System.out.print(this.items[this.tail]+"\n");
    }

    private class DeqIterator implements Iterator<Item> {

        int cursor, visited;

        public DeqIterator() {
            this.cursor = head;
            this.visited = 0;
        }

        public boolean hasNext() {
            if ( this.visited < size ) {
                return true;
            }
            return false;
        }

        public Item next() {
            int temp;
            if ( !this.hasNext() ) {
                throw new NoSuchElementException();
            }
            temp = this.cursor;
            this.cursor = ( this.cursor + 1 ) % capacity;
            this.visited += 1;
            return items[temp];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public Iterator<Item> iterator() {
        return new DeqIterator();
    }



    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer> ();
        /* for(int i=0;i<=10; i++){
           deque.addFirst(i);
           deque.display();
        }
        Iterator<Integer> it = deque.iterator();
        while ( it.hasNext() ){
            System.out.print(it.next() + " ");
        }*/
        deque.addFirst(0);
         deque.isEmpty();
         deque.removeFirst();
         deque.display();
    }
}
