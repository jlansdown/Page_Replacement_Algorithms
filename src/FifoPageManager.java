import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class FifoPageManager extends PageReplacementManager {


    /**
     * @param numberOfPages
     * @param numberOfFrames
     * @param pages
     */
    FifoPageManager(int numberOfPages, int numberOfFrames, int[] pages) {
        this.setNumberOfPages(numberOfPages);
        this.setNumberOfFrames(numberOfFrames);
        this.setPages(pages);
    }

    /**
     * @return pageFaults
     */
    @Override
    int getPageFaults() {
        HashSet<Integer> frames = new HashSet<>(this.getNumberOfFrames());
        Queue<Integer> referenceQueue = new LinkedList<>();
        int pageFaults = 0;

        for (int i = 0; i < this.getNumberOfPages(); i++) {

            if (frames.size() < this.getNumberOfFrames()) {

                if (!frames.contains(this.getPages()[i])) {
                    frames.add(this.getPages()[i]);
                    pageFaults++;
                    referenceQueue.add(this.getPages()[i]);
                }
            } else {
                if (!frames.contains(this.getPages()[i])) {
                    int firstItemInReferenceQueue = referenceQueue.peek();
                    referenceQueue.poll();
                    frames.remove(firstItemInReferenceQueue);
                    frames.add(this.getPages()[i]);
                    referenceQueue.add(this.getPages()[i]);
                    pageFaults++;
                }
            }
        }
        return pageFaults;
    }
}
