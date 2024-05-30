package com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate;

import com.example.dinhakids.sistemaweb.Domain.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class CategoryUpdateDTO {

    private String id;

    @Length(max = 45, message = "Nome da categoria não pode exceder 45 caracteres")
    private String name;

    public void updateCategory(Category category) {
        if (id != null && id.isEmpty()) {
            category.setId(id);
        }
        if (name != null) {
            category.setName(name);
        }
    }
}
