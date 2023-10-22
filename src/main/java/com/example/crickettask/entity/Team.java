package com.example.crickettask.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "teams")
public class Team {
    @Id
    private String id;

    @Indexed(unique = true)
    private String teamname;

    //private Integer teamsize;

}
