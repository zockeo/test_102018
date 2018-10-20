package src.entity;

/**
 * Created by z on 2018/10/15.
 */
public class Page {
    private int pageNum;
    private int maxPage;
    private int rowsPerPage = 3 ;
    private int rowsNum ;

    public int getLastPage(){
        if(pageNum > 1)
            return pageNum-1;
        return 0;
    }
    public int getNextPage(){
        if(pageNum < maxPage)
            return pageNum + 1;
        return 0;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public int getRowsNum() {
        return rowsNum;
    }

    public void setRowsNum(int rowsNum) {
        this.rowsNum = rowsNum;
    }
}
