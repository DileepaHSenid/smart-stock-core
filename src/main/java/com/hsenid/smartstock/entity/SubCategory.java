package com.hsenid.smartstock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "subcategories")
public class SubCategory {
    @Id
    private String id;
    private String name;
    private List<SubCategory> subCategories; // Allows for further nesting
}
