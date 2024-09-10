package stock_microservices.adapters.driving.http.dto.request;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationRequest {
    private int page;
    private String column;
    private boolean ascending;

    public PaginationRequest(Map<String, String> query) {
        String sortBy = null;
        int pageNum = 0;
        boolean asc = true;
        if(query.containsKey("sortBy")) sortBy = query.get("sortBy");
        if (query.containsKey("page")) pageNum = Integer.parseInt(query.get("page"));
        if (query.containsKey("asc")) asc = Boolean.parseBoolean(query.get("asc"));
        this.page = pageNum;
        this.column = sortBy;
        this.ascending = asc;
    }
}
