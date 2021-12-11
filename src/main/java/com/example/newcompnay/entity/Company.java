package com.example.newcompnay.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("company")
public class Company {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;

    public Company()
    {
        this.id = null;
        this.name = null;
    }

    public Company(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean sameId = ((Company)obj).getId().equals(this.id);
        boolean sameName = ((Company)obj).getName().equals(this.name);

        return sameId && sameName;
    }
}
