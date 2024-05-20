package com.example.dinhakids.sistemaweb.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardErrorDTO {

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}