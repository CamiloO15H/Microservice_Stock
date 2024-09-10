package stock_microservices.domain.utils.pagination;


import java.util.List;

public class DomainPage<T> {
    // Attributes

    Integer page;
    Integer pageSize;
    Integer totalPages;
    Integer count;
    Long totalCount;
    List<T> content;

    // Constructors


    public DomainPage(Integer page, Integer pageSize, Integer totalPages, Integer count, Long totalCount, List<T> content) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.count = count;
        this.totalCount = totalCount;
        this.content = content;
    }

    public DomainPage() {
    }

    // Getters and Setters

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
