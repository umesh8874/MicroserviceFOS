package com.AN1D.an1d.DTO;

import javax.persistence.*;

@Entity
@Table(name="category_list")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
}
