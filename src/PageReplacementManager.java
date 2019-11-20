abstract class PageReplacementManager {
    private int numberOfPages;
    private int numberOfFrames;
    private int[] pages;


    abstract int getPageFaults();
    /**
     * @param n
     */
    void setNumberOfPages(int n) { numberOfPages = n; };
    /**
     * @param n
     */
    void setNumberOfFrames(int n) { numberOfFrames = n; };
    /**
     * @param newArray
     */
    void setPages(int[] newArray) { pages = newArray; };
    /**
     * @return numberOfPages
     */
    int getNumberOfPages() { return numberOfPages; }
    /**
     * @return numberOfFrames
     */
    int getNumberOfFrames() { return numberOfFrames; }
    /**
     * @return pages
     */
    int[] getPages() { return pages; }

}
