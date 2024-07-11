package com.org.address.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String state;
    private String city;
    @Column(name = "zip_code")
    private String zipCode;
}
