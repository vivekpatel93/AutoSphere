package com.vivek.service.impl;

import com.vivek.dto.AccountResponseDTO;
import com.vivek.dto.AddMoneyDTO;
import com.vivek.entity.Account;
import com.vivek.entity.CarUser;
import com.vivek.exception.ResourceNotFoundException;
import com.vivek.repository.AccountRepository;
import com.vivek.repository.CarUserRepository;
import com.vivek.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final CarUserRepository carUserRepository;

    private final  AccountRepository accountRepository;



    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,CarUserRepository carUserRepository){

        this.accountRepository=accountRepository;
        this.carUserRepository=carUserRepository;
    }

    // Entity to DTO mapping
    public AccountResponseDTO entityToDTO(Account account){
        AccountResponseDTO dto=new AccountResponseDTO();
        dto.setAccountNo(account.getAccountNo());
        dto.setBalance(account.getBalance());
        return dto;
    }

    @Override
    public Account createAccount(CarUser carUser){
           accountRepository.findByCarUser_UserId(carUser.getUserId()).
                   ifPresent( acc -> {
                       throw new IllegalArgumentException("Account already exist for this user.");
                   });



        Account account=new Account();
        account.setCarUser(carUser);
        account.setBalance(0.0);
        account.setAccountNo(generateAccountNumber());

        return accountRepository.save(account);
    }


    @Override
    @Transactional
    public AccountResponseDTO addMoney(AddMoneyDTO dto){
        if(dto.getAmount() == null || dto.getAmount() <= 0.0){
            throw new IllegalArgumentException("Amount must be greater than 0.0");
        }
        String email=SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        CarUser carUser=carUserRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User Not Found."));

        Account account=accountRepository.findByCarUser_UserId(carUser.getUserId()).orElseThrow(()-> new ResourceNotFoundException("Account does not exist."));


        account.setBalance(account.getBalance()+dto.getAmount());
        return entityToDTO(accountRepository.save(account));

    }

    @Override
    public AccountResponseDTO getAccountById(){
        String email=SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        CarUser carUser=carUserRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User Not Login"));
        Account account=accountRepository.findByCarUser_UserId(carUser.getUserId()).orElseThrow(()-> new ResourceNotFoundException("Account does not exist."));

        return entityToDTO(account);
    }



    public String generateAccountNumber(){
        return "Ac"+ System.currentTimeMillis();
    }
}
