package com.example.bankmanagement.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Customer {
    private String  ID;
    private String username;
    private double balance;

}
