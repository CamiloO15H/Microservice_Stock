package stock_microservices.domain.utils.pagination;

public class PaginationData {
    private int page;
    private String column;
    private boolean ascending;

    public PaginationData(int page, String column, boolean ascending) {
        this.page = page;
        this.column = column;
        this.ascending = ascending;
    }

    public PaginationData() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
}
