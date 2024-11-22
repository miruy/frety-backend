package me.frety.frety_back.domain.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRs<T> {
    private List<T> data;
    private PageRs__Meta meta;

    public static <T> PageRs<T> fromPage(Page<T> page) {
        PageRs__Meta meta = PageRs__Meta.builder()
                .page(page.getNumber())
                .pageSize(page.getSize())
                .totalCount(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .hasPreviousPage(!page.isFirst())
                .hasNextPage(!page.isLast())
                .build();

        return PageRs.<T>builder()
                .data(page.getContent())
                .meta(meta)
                .build();
    }
}
