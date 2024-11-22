package me.frety.frety_back.domain.tab.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ParameterObject
public class SearchMyCreatedTabsCondition {
    @Parameter(description = "유저 아이디")
    private Long authorId;

    @Parameter(description = "정렬 타입")
    private SearchTabsCondition__Sort sort;
}
