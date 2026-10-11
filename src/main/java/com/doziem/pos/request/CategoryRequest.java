package com.doziem.pos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {
    @NotBlank(message = "Category name is required")
    private String name;
    @NotBlank(message = "Category description is required")
    private String description;
}
