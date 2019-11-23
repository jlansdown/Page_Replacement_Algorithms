import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    /**
     *
     */
    public static void printAverages() throws IOException {
        int[] convertedJobFile;
        int[] pages = new int[30];
        int trials = 50;
        int references = 30;
        int numberOfFrames;
        int numberOfPages = pages.length;
        String filename;

        FileProcessor fileProcessor;


        double fifoResult = 0;
        double lruResult = 0;
        double optResult = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name...\n" +
                "1 (3 frames)\n" +
                "2 (4 frames)\n" +
                "3 (5 frames)\n" +
                "4 (6 frames)\n" +
                "5 (50 random trials: ");
        filename = scanner.nextLine();
        File jobFile = new File(filename + ".txt");

        if (filename.equals("5"))
            fileProcessor = new FileProcessor(jobFile, true);
        else
            fileProcessor = new FileProcessor(jobFile);

        convertedJobFile = fileProcessor.convertJobFileToArray();
        numberOfFrames = convertedJobFile[0];

        for (int i = 0; i < numberOfPages; i++)
            pages[i] = convertedJobFile[i+1];

        FifoPageManager fifo = new FifoPageManager(numberOfPages, numberOfFrames, pages);
        LRUPageManager lru = new LRUPageManager(numberOfPages, numberOfFrames, pages);
        OptimalPageManager opt = new OptimalPageManager(numberOfPages, numberOfFrames, pages);

        System.out.println("\n" + numberOfFrames + "-frame fifo: " + fifo.getPageFaults());
        System.out.println(numberOfFrames + "-frame lru: " + lru.getPageFaults());
        System.out.println(numberOfFrames + "-frame opt: " + opt.getPageFaults() + "\n");


        /*
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


         */
    }

    public static void () test

    public static void main(String[] args) throws IOException {
        printAverages();
    }
}
