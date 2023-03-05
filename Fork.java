// Fork class holds the semaphore and its number

import java.util.concurrent.Semaphore;

public class Fork {
     private final int num;
     private final Semaphore s;

     public Fork(int num) {
          this.num = num;
          this.s = new Semaphore(1);
     }

     public void acquire() throws InterruptedException {
          this.s.acquire();
     }

     public void release() {
          this.s.release();
     }

     public int getNum() {
          return num;
     }
}