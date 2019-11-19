abstract class PageReplacementManager {
    private int numberOfPages;
    private int numberOfFrames;
    private int[] pages;


    abstract int getPageFaults();

    void setNumberOfPages(int n) { numberOfPages = n; };
    void setNumberOfFrames(int n) { numberOfFrames = n; };
    void setPages(int[] newArray) { pages = newArray; };


    int getNumberOfPages() { return numberOfPages; }
    int getNumberOfFrames() { return numberOfFrames; }
    int[] getPages() { return pages; }

}
