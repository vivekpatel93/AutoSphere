package com.vivek.controller;

import com.vivek.dto.AccountResponseDTO;
import com.vivek.dto.AddMoneyDTO;
import com.vivek.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

//    @Autowired
//    public AccountController(AccountService accountService){
//        this.accountService=accountService;
//    }

    @PostMapping("/addMoney")
    public ResponseEntity<AccountResponseDTO> addMoney(@RequestBody AddMoneyDTO dto){
        return ResponseEntity.ok(accountService.addMoney(dto));
    }

    @GetMapping("/id")
    public ResponseEntity<AccountResponseDTO> getAccountById(){
        return ResponseEntity.ok(accountService.getAccountById());
    }

}
