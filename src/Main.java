import java.util.Random;

public class Main {


    /**
     * @param numberOfFrames
     */
    public static void printAverage(int numberOfFrames) {

        int[] pages = new int[30];
        int trials = 50;
        int references = 30;
        int numberOfPages = pages.length;

        double fifoResult = 0;
        double lruResult = 0;
        double optResult = 0;

        Random random = new Random();



        for (int i = 0; i < trials; i++) {


            for (int j = 0; j < references; j++)
                pages[j] = random.nextInt(10);

            FifoPageManager fifo = new FifoPageManager(numberOfPages, numberOfFrames, pages);
            fifoResult += fifo.getPageFaults();

            LRUPageManager lru = new LRUPageManager(numberOfPages, numberOfFrames, pages);
            lruResult += lru.getPageFaults();

            OptimalPageManager opt = new OptimalPageManager(numberOfPages, numberOfFrames, pages);
            optResult += opt.getPageFaults();
        }

        System.out.println(numberOfFrames + "-frame fifo average: " + (fifoResult / trials));
        System.out.println(numberOfFrames + "-frame lru average: " + (lruResult / trials));
        System.out.println(numberOfFrames + "-frame opt average: " + (optResult / trials) + "\n\n");
    }


    public static void main(String[] args) {
        /* test cases
         3,6,1,7,2,4,7,2,0,3,5,4,7,2,0,1,4,6,3,5,3,2,1,4,5,6,7,0,1,2
         3 frames
         fifo = 27, lru = 27, opm = 20
         4 frames
         fifo = 25, lru = 27, opm = 16
         5 frames
         fifo = 23, lru = 25, opm = 14
         6 frames
         fifo = 15, lru = 17, opm = 12
         */
        printAverage(3);
        printAverage(4);
        printAverage(5);
        printAverage(6);
    }
}
