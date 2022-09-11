package org.kodluyoruz.mybank.repository;

import org.kodluyoruz.mybank.model.CreditCard;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends PagingAndSortingRepository<CreditCard, Long> {
    CreditCard findByCreditCardId(Long cardId);
    CreditCard findByAccount_AccountId(Long accountId);
}
