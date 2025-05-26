package com.prueba.devsu.repository;

import com.prueba.devsu.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByAccountIdAndTransactionDateBetween(Long accountId, LocalDate start, LocalDate end);
}
