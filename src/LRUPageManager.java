import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class LRUPageManager extends PageReplacementManager {

    /**
     * @param numberOfPages
     * @param numberOfFrames
     * @param pages
     */
    LRUPageManager(int numberOfPages, int numberOfFrames, int[] pages) {
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
        HashMap<Integer, Integer> referenceMap = new HashMap<>();

        int page_faults = 0;
        for (int i=0; i < this.getNumberOfPages(); i++) {
            if (frames.size() < this.getNumberOfFrames()) {

                if (!frames.contains(this.getPages()[i])) {
                    frames.add(this.getPages()[i]);
                    page_faults++;
                }
                referenceMap.put(this.getPages()[i], i);
            }

            else
            {

                if (!frames.contains(this.getPages()[i])) {
                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;

                    Iterator<Integer> itr = frames.iterator();

                    while (itr.hasNext()) {
                        int temp = itr.next();
                        if (referenceMap.get(temp) < lru) {
                            lru = referenceMap.get(temp);
                            val = temp;
                        }
                    }
                    frames.remove(val);
                    referenceMap.remove(val);
                    frames.add(this.getPages()[i]);
                    page_faults++;
                }
                referenceMap.put(this.getPages()[i], i);
            }
        }
        return page_faults;
    }
}
