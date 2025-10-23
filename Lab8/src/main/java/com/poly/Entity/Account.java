package com.poly.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "account") // ánh xạ với bảng "account" trong SQL Server
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @Column(length = 50)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String fullname;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(name = "is_admin", nullable = false) // ánh xạ sang cột BIT
    private boolean isAdmin;
}