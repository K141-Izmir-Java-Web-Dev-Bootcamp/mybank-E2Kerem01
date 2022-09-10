package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankCardRepository extends PagingAndSortingRepository<BankCard, Long> {
        BankCard findByBankCardId(Long cardId);

}
