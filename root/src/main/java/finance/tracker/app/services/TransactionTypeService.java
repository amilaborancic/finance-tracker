package finance.tracker.app.services;

import finance.tracker.app.models.Transaction;
import finance.tracker.app.models.TransactionType;
import finance.tracker.app.repositories.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionTypeService {
    @Autowired
    TransactionTypeRepository transactionTypeRepository;

    public TransactionType findTypeById(Long typeId){
        return transactionTypeRepository.getOne(typeId);
    }
    public void save(TransactionType t){
        transactionTypeRepository.save(t);
    }
    public TransactionType getByTitle(String title){
        return transactionTypeRepository.findByNazivContaining(title);
    }
}
