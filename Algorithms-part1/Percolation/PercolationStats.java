import java.lang.*;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int trials;
    private double thresholds[];
    private Percolation p;
    private double mean, s;
    public PercolationStats(int n, int trials){
       // perform trials independent experiments on an n-by-n grid
       if(n<=0 || trials <=0 ){
            throw new IllegalArgumentException();
        }
       this.trials = trials;
       this.thresholds = new double[trials];
       for(int i=0;i<trials;i++){
        // performing trials
            p = new Percolation(n);
            float count = 0;
            while(!p.percolates()){
               int a = StdRandom.uniform(1, n+1);
               int b = StdRandom.uniform(1, n+1);
               if(p.isOpen(a,b)){
                    continue;
                }
                p.open(a,b);
                count += 1;
            }
            thresholds[i] = count/(n*n);
            //System.out.println(thresholds[i]);
        }
       mean();
       stddev();
   }
   public double mean(){
        // sample mean of percolation threshold
        int t = this.trials;
        float m = 0;
        for(int i=0;i<t;i++){
            m += this.thresholds[i];
        }
        this.mean = m/t;
        return this.mean;
   }
   public double stddev(){
       // sample standard deviation of percolation threshold
       float d = 0;
       for(int i=0; i<this.trials;i++){
            double temp = (this.mean - this.thresholds[i]);
            temp *= temp;
            d += temp;
        }
       d = d/(this.trials - 1);
       this.s = Math.sqrt(d);
       return this.s;
   }
   public double confidenceLo(){
       // low  endpoint of 95% confidence interval
       return this.mean - (1.96)*s/Math.sqrt(this.trials);
   }
   public double confidenceHi() {
        // high endpoint of 95% confidence interval
       return this.mean + (1.96)*s/Math.sqrt(this.trials);
   }

    private void result(){
        System.out.println("mean                    = "+this.mean);
        System.out.println("stddev                  = "+this.s);
        System.out.println("95% confidence interval = " + this.confidenceLo()+  ", " + this.confidenceHi());
    }

   public static void main(String[] args){
       int n = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);
       PercolationStats ps = new PercolationStats(n, trials);
       ps.result();
   }
}
