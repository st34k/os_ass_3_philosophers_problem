import java.util.*;
import java.util.concurrent.Semaphore;

public class DiningTable {

     public static void main(String[] args) {
          Scanner s = new Scanner(System.in);
          System.out.println("How many philosophers are at the table? ");

          int n = s.nextInt();

          Semaphore[] forks = new Semaphore[n];
          Philosopher[] philosophers = new Philosopher[n];

          for (int i = 0; i < n; i++) {
               forks[i] = new Semaphore(1);
          }

          for (int i = 0; i < n; i++) {
               philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % n]);
          }

          System.out.println(n + " Philosophers sit at the table..");

          for(Philosopher p:philosophers) {
               new Thread(p).start();
          }
     }
}
