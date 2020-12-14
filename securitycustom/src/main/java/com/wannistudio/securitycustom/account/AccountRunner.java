package com.wannistudio.securitycustom.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AccountRunner implements ApplicationRunner {
    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account wanni = accountService.createAccount("wanni", "12345");
        System.out.println(wanni.getId());
        System.out.println(wanni.getUsername());
        System.out.println(wanni.getPassword());
    }
}
