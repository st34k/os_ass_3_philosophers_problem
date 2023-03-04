import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
     protected int id;

     protected enum Actions {EATING, THINKING}

     protected Actions action;
     protected Semaphore leftFork;
     protected Semaphore rightFork;

     public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
          this.id = id;
          this.leftFork = leftFork;
          this.rightFork = rightFork;
          this.action = Actions.THINKING;
     }

     private void pickUpLeftFork() throws InterruptedException {
          this.leftFork.acquire();
          log("picked up left fork");
     }

     private void pickUpRightFork() throws InterruptedException {
          this.rightFork.acquire();
          log("picked up right fork");
     }

     private void releaseForks() {
          this.leftFork.release();
          this.rightFork.release();

          log("released both forks");
     }

     private int getRandomMilliseconds() {
          return (int) (Math.random() * 3000);
     }

     private void changeAction() {
          this.action = this.action == Actions.EATING ? Actions.THINKING : Actions.EATING;
          log("is" + " " + this.action.name().toLowerCase());
     }

     @Override
     public void run() {
          try {
               log(this.action.name());

               for (; ; ) {
                    Thread.sleep(getRandomMilliseconds());

                    this.pickUpLeftFork();
                    this.pickUpRightFork();

                    this.changeAction();

                    Thread.sleep(getRandomMilliseconds());

                    this.releaseForks();

                    this.changeAction();
               }
          } catch (InterruptedException e) {
               log("stopped due to an error");
               e.printStackTrace();
          }
     }

     private void log(String action) {
          System.out.println("Philosopher " + this.id + " " + action);
     }
}