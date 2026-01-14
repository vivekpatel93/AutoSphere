package com.vivek.service;

import com.vivek.dto.AccountResponseDTO;
import com.vivek.dto.AddMoneyDTO;
import com.vivek.entity.Account;
import com.vivek.entity.CarUser;

public interface AccountService {

    public Account createAccount(CarUser carUser);

    AccountResponseDTO addMoney(AddMoneyDTO dto);

    AccountResponseDTO getAccountById();
}