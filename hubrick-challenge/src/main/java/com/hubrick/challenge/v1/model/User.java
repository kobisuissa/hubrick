package com.hubrick.challenge.v1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by kobi on 06/03/16.
 */
@Data
@Document( indexName = "user" , type = "user", shards = 1, replicas = 0, refreshInterval = "-1")
public class User {

    @Id
    private Long id;

    @Field(type = FieldType.String)
    private String firstName;

    @Field(type = FieldType.String)
    private String lastName;

    @Field(type = FieldType.String)
    private String email;

    @Field(type = FieldType.String)
    private String password;

}
