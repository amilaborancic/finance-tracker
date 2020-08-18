package finance.tracker.app.repositories;

import finance.tracker.app.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByIdAccountOrderByAmountAsc(Long idAccount);
    List<Transaction> findByIdAccountOrderByAmountDesc(Long idAccount);
    List<Transaction> findAllByType(Long type);
    List<Transaction> findAllByIdAccount(Long accountId);
}
