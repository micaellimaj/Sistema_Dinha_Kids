package com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate;

import com.example.dinhakids.sistemaweb.Domain.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class CategoryCreateDTO {

    @NotBlank(message = "Id da categoria não informado")
    private String id;

    @NotBlank(message = "Nome da categoria não informado")
    @Length(max = 45, message = "Nome da categoria não pode exceder 45 caracteres")
    private String name;

    public Category createCategory() {
        Category category = new Category();

        category.setId(category.getId());
        category.setName(category.getName());

        return category;
    }
}
