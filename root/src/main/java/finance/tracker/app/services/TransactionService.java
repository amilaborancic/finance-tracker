package finance.tracker.app.services;

import finance.tracker.app.models.Transaction;
import finance.tracker.app.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public void save(Transaction transaction){
        transactionRepository.save(transaction);
    }

}
