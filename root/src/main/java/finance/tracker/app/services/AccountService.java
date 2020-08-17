package finance.tracker.app.services;

import finance.tracker.app.models.Account;
import finance.tracker.app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account findAccountById(Long id){
        return accountRepository.getOne(id);
    }
    public boolean isUnderBudget(double amount, Long id){
        Account acc = findAccountById(id);
        if(amount < 0) amount*=-1;
        return acc.getBudget() >= amount;
    }

    public void updateAccountBudget(Long id, Double newBudget){
        Account acc = findAccountById(id);
        acc.setBudget(newBudget);
    }

}
