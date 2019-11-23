import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private int[] convertedJobFile;
    private int[] pages = new int[30];
    private int numberOfFrames;
    private int numberOfPages = pages.length;
    private FileProcessor fileProcessor;
    private File jobFile;
    private String filename;
    private Scanner scanner;

    private Main() {
        scanner = new Scanner(System.in);

        System.out.print("Enter file name...\n" +
                "1 (3 frames)\n" +
                "2 (4 frames)\n" +
                "3 (5 frames)\n" +
                "4 (6 frames)\n" +
                "5 (50 random trials: ");
        this.filename = scanner.nextLine();

        this.jobFile = new File(filename + ".txt");
        this.scanner = new Scanner(System.in);
    }

    /**
     *
     */
    private void printAverages() throws IOException {
        fileProcessor = new FileProcessor(jobFile);

        convertedJobFile = fileProcessor.convertJobFileToArray();
        numberOfFrames = convertedJobFile[0];

        for (int i = 0; i < numberOfPages; i++)
            pages[i] = convertedJobFile[i + 1];

        FifoPageManager fifo = new FifoPageManager(numberOfPages, numberOfFrames, pages);
        LRUPageManager lru = new LRUPageManager(numberOfPages, numberOfFrames, pages);
        OptimalPageManager opt = new OptimalPageManager(numberOfPages, numberOfFrames, pages);

        System.out.println("\n" + numberOfFrames + "-frame fifo: " + fifo.getPageFaults());
        System.out.println(numberOfFrames + "-frame lru: " + lru.getPageFaults());
        System.out.println(numberOfFrames + "-frame opt: " + opt.getPageFaults() + "\n");

    }

    private void printRandomAverages() throws IOException {
        int trials = 50;
        double fifoResult = 0;
        double lruResult = 0;
        double optResult = 0;

        System.out.print("Enter number of frames...\n" +
                "3, 4, 5, 6: ");

        String numberOfFramesForRandomTrials = scanner.nextLine();

        for (int i = 0; i < trials; i++) {

            fileProcessor = new FileProcessor(jobFile, numberOfFramesForRandomTrials, true);

            convertedJobFile = fileProcessor.convertJobFileToArray();
            numberOfFrames = convertedJobFile[0];

            for (int j = 0; j < numberOfPages; j++)
                pages[j] = convertedJobFile[j + 1];

            FifoPageManager fifo = new FifoPageManager(numberOfPages, numberOfFrames, pages);
            fifoResult += fifo.getPageFaults();

            LRUPageManager lru = new LRUPageManager(numberOfPages, numberOfFrames, pages);
            lruResult += lru.getPageFaults();

            OptimalPageManager opt = new OptimalPageManager(numberOfPages, numberOfFrames, pages);
            optResult += opt.getPageFaults();
        }

        System.out.println("\n" + numberOfFramesForRandomTrials + "-frame fifo average: " + (fifoResult / trials));
        System.out.println(numberOfFramesForRandomTrials + "-frame lru average: " + (lruResult / trials));
        System.out.println(numberOfFramesForRandomTrials + "-frame opt average: " + (optResult / trials) + "\n\n");
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        if (main.filename.equals("5"))
            main.printRandomAverages();
        else
            main.printAverages();
    }
}
