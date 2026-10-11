package com.doziem.pos.mapper;

import com.doziem.pos.model.Category;
import com.doziem.pos.request.CategoryRequest;
import com.doziem.pos.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public static CategoryResponse toResponse(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public static Category mapToCategory(CategoryRequest request) {
        if (request == null) {
            return null;
        }
        return Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }
}
