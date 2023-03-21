public class Philosopher implements Runnable {
     private final int id;

     private enum Actions {EATING, THINKING, HUNGRY}

     private Actions action;
     private final Fork leftFork;
     private final Fork rightFork;

     public Philosopher(int id, Fork leftFork, Fork rightFork) {
          this.id = id;
          this.leftFork = leftFork;
          this.rightFork = rightFork;
          this.action = Actions.THINKING;
     }

     private void pickUpForks() throws InterruptedException {
          // to avoid a deadlock -
          // even num philosopher gets right fork first
          // odd num philosopher gets left fork first
          if (id % 2 == 0) {
               this.rightFork.acquire();
               log("picked up right fork " + rightFork.getNum());

               this.leftFork.acquire();
               log("picked up left fork " + leftFork.getNum());
          } else {
               this.leftFork.acquire();
               log("picked up left fork " + leftFork.getNum());

               this.rightFork.acquire();
               log("picked up right fork " + rightFork.getNum());
          }
     }

     private void releaseForks() {
          this.leftFork.release();
          log("released left fork " + leftFork.getNum());

          this.rightFork.release();
          log("released right fork " + rightFork.getNum());
     }

     private int getRandomMilliseconds() {
          return (int) (Math.random() * 3000);
     }

     private void changeAction(Actions a) {
          this.action = a;
          log("is" + " " + this.action.name());
     }

     @Override
     public void run() {
          try {
               log(this.action.name());

               while (true) {
                    Thread.sleep(getRandomMilliseconds());

                    this.changeAction(Actions.HUNGRY);

                    this.pickUpForks();

                    this.changeAction(Actions.EATING);

                    Thread.sleep(getRandomMilliseconds());

                    this.releaseForks();

                    this.changeAction(Actions.THINKING);
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