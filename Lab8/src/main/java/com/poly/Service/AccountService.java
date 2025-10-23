package com.poly.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.poly.Entity.Account;
import com.poly.dao.AccountDAO;


@Service
public class AccountService {
    @Autowired
    AccountDAO dao;
    public Account findById(String username) {
        return dao.findById(username).orElse(null);
    }
}
