public class DeadlockExample {

        volatile boolean isNotified = false;

    /*
     Первая нить входит в синхронизированный блок метода 2 и ждет.
      Вторая нить пытается войти в синхронизированный блок метода1, но ждет в очереди.
       Нить 1 ждет чтобы оповестить и нить 2 ждет в синхронизированном блоке.
        Все ждут. Дедлок
     */
        public synchronized void method1() {
            try {
                isNotified = false;
                while (!isNotified)
                    wait();
                notifyAll();
                System.out.println("Method 1 not notified");
            } catch (InterruptedException ex) {
              ex.printStackTrace();
            }
        }

        public synchronized void method2() {
            try {
                isNotified = true;
                while (isNotified)
                    wait();
                notifyAll();

                System.out.println("Method 2 notified");
            } catch (InterruptedException ex) {
              ex.printStackTrace();
            }
        }

        public static void main(String[] args)
        {
            DeadlockExample deadlockExample=new DeadlockExample();
          new Thread(() -> deadlockExample.method2()).start();
            new Thread(() -> deadlockExample.method1()).start();
        }
    }