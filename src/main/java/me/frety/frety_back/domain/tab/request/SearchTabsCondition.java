package me.frety.frety_back.domain.tab.request;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.core.parameters.P;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ParameterObject
public class SearchTabsCondition {
    @Parameter(description = "검색어")
    private String keyword;

    @Parameter(description = "정렬 타입")
    private SearchTabsCondition__Sort sort;

    @Parameter(description = "투표 유저 ID")
    private Long voterId;

    @Parameter(description = "즐겨찾기 유저 ID")
    private Long favoriterId;
}
