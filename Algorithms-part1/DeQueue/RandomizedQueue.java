import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>{
    private int size, capacity;
    private Item[] items;
    public RandomizedQueue(){
        size = 0;
        capacity = 1;
        items = (Item[]) new Object[capacity];
    }

    public boolean isEmpty(){
        if (this.size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return this.size;
    }

    public void enqueue(Item item) {
        this.items[this.size] = item;
        this.size += 1;
        resize();
    }

    public Item dequeue(){
        int index = StdRandom.uniform(this.size);
        Item it = this.items[index];
        for(int i=index; i < this.size-1; i++){
            this.items[i] = this.items[i+1];
        }
        this.size -= 1;
        resize();
        return it;
    }

    public Item sample(){
        int index = StdRandom.uniform(this.size);
        return this.items[index];
    }

    public Iterator<Item> iterator(){
        Iterator<Item> it = new Iterator<Item>(){
            private int index=0;

            public boolean hasNext(){
                return index>=0 && index<size;
            }

            public Item next(){
                if (!this.hasNext()){
                    throw new NoSuchElementException();
                }
                return items[index++];
            }

            public void remove(){
                throw new UnsupportedOperationException();
            }
        };

        return it;
    }

    private void resize(){
        if (this.size == this.capacity){
            // while insertion if queue is full
            this.capacity = 2 * this.capacity;
            Item[] temp = (Item[]) new Object[this.capacity];
            for(int i = 0; i < this.size; i++){
                temp[i] = this.items[i];
            }
            this.items = temp;
        }else if(this.size <= this.capacity/4){
            // while removal if memory is free
            this.capacity = this.capacity/4;
            Item[] temp = (Item[]) new Object[this.capacity];
            for(int i = 0; i < this.size; i++){
                temp[i] = this.items[i];
            }
            this.items = temp;
        }
    }

    public static void main(String[] args){
    }

}
