package com.doziem.pos.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private String categoryId;
    private String name;
    private String description;
}
