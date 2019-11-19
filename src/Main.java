public class Main {

    public static void main(String[] args) {
        int pages[] = {3,6,1,7,2,4,7,2,0,3,5,4,7,2,0,1,4,6,3,5,3,2,1,4,5,6,7,0,1,2};
        int result;

        FifoPageManager fifo = new FifoPageManager(30, 6, pages);

        result = fifo.getPageFaults();

        System.out.println(result);

        LRUPageManager lru = new LRUPageManager(30, 6, pages);

        result = lru.getPageFaults();

        System.out.println(result);

        OptimalPageManager opm = new OptimalPageManager(30, 6, pages);

        result = opm.getPageFaults();

        System.out.println(result);
    }
}
