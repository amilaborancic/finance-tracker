package finance.tracker.app.repositories;

import finance.tracker.app.models.Transaction;
import finance.tracker.app.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
    TransactionType findByNazivContaining(String naziv);
}
