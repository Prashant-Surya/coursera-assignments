import edu.princeton.cs.algs4.StdIn;

public class Permutation{

    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        String[] data = StdIn.readAllStrings();
        //System.out.println(data.length);
        RandomizedQueue<String> rd = new RandomizedQueue<String>();
        for (String s: data){
            rd.enqueue(s);
        }
        for (int i=0;i < k ; i++ ){
            System.out.println(rd.dequeue());
        }

    }

}
