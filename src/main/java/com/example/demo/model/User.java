package com.example.demo.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
public class User {

    @Id
    public String _id;

    private String cpfCnpj;

    private String name;

}
