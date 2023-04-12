package com.example.repository;

import com.example.entity.Account;
import com.example.model.enums.Currency;
import com.example.model.enums.Status;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("select a from Account  a where  a.status =:status ")
    List<Account> findAllWhereStatusIs(Status status);


    @Query("select a from Account a " +
            " join a.user u " +
            " where a.currency =:currency and u.id =:userId")
    List<Account> findAllAccountsByCurrencyAndUserId(Currency currency, Integer userId);
}