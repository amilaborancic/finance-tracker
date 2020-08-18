package finance.tracker.app.services;

import finance.tracker.app.DTO.TransactionSortDTO;
import finance.tracker.app.models.Transaction;
import finance.tracker.app.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public void save(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsSortedByAmount(TransactionSortDTO dto){
        if(dto.getOrderAsc()) return transactionRepository.findByIdAccountOrderByAmountAsc(dto.getAccountId());
        return transactionRepository.findByIdAccountOrderByAmountDesc(dto.getAccountId());
    }
    public List<Transaction> getTransactionsByType(Long typeId){
        return transactionRepository.findAllByType(typeId);
    }

    public long countTransactions(){
        return transactionRepository.count();
    }

    public List<Transaction> getTransactionsForAccount(Long accountId){
        return transactionRepository.findAllByIdAccount(accountId);
    }

}
