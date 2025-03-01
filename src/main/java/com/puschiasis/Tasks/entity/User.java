package com.puschiasis.Tasks.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class User {
    private String id;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
}