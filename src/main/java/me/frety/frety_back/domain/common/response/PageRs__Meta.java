package me.frety.frety_back.domain.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRs__Meta {
    private int page;
    private int pageSize;
    private long totalCount;
    private long totalPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
}
