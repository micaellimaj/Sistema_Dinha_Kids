package com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate;

import com.example.dinhakids.sistemaweb.Models.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class CategoryUpdateDTO {

    private int id;

    @Length(max = 45, message = "Nome da categoria não pode exceder 45 caracteres")
    private String name;

    public void updateCategory(Category category) {
        if (id != 0 ) {
            category.setId(id);
        }
        if (name != null) {
            category.setName(name);
        }
    }
}
