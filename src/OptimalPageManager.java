import java.util.HashSet;

public class OptimalPageManager extends PageReplacementManager {

    private HashSet<Integer> frames;

    /**
     * @param numberOfPages
     * @param numberOfFrames
     * @param pages
     */
    OptimalPageManager(int numberOfPages, int numberOfFrames, int[] pages) {
        this.setNumberOfPages(numberOfPages);
        this.setNumberOfFrames(numberOfFrames);
        this.setPages(pages);

        this.frames = new HashSet<>(this.getNumberOfFrames());
    }

    /**
     * @param pageIndex
     * @return page
     */
    int predict(int pageIndex) {
        HashSet<Integer> checkSet = new HashSet<>();
        int page = -1;
        int farthest = pageIndex;

        for (int i = pageIndex; i < this.getNumberOfPages(); i++) {
            if (this.frames.contains(this.getPages()[i])) {
                if (checkSet.size() < this.getNumberOfFrames())
                checkSet.add(this.getPages()[i]);
                if (i > farthest) {
                    farthest = i;
                    page = i;
                }
            }
            if (i == this.getNumberOfPages() - 1 || checkSet.size() == this.getNumberOfFrames() - 1) {
                for (int framePage : this.frames) {
                    if (!checkSet.contains(framePage)) {
                        return framePage;
                    }
                }
            }
        }
        return (page == -1) ? 0: page;
    }

    /**
     * @return pageFaults
     */
    @Override
    int getPageFaults() {
        int pageFaults = 0;

        for (int i = 0; i < this.getNumberOfPages(); i++) {
            if (this.frames.size() < this.getNumberOfFrames()) {

                if (!this.frames.contains(this.getPages()[i])) {
                    this.frames.add(this.getPages()[i]);
                    pageFaults++;
                }
            } else {
                if (!this.frames.contains(this.getPages()[i])) {
                    int pageToRemove = predict(i);
                    frames.remove(pageToRemove);
                    frames.add(this.getPages()[i]);
                    pageFaults++;
                }

            }
        }
        return pageFaults;
    }
}
