package finance.tracker.app.controllers;

import finance.tracker.app.DTO.TransactionDTO;
import finance.tracker.app.DTO.TransactionSortDTO;
import finance.tracker.app.models.Account;
import finance.tracker.app.models.Transaction;
import finance.tracker.app.models.TransactionType;
import finance.tracker.app.services.AccountService;
import finance.tracker.app.services.TransactionService;
import finance.tracker.app.services.TransactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    TransactionTypeService transactionTypeService;
    @Autowired
    AccountService accountService;

    @PostMapping("/new")
    public Long createNewTransaction(@RequestBody TransactionDTO newTransaction) throws Exception {
        Long typeId = newTransaction.getType();
        String typeName = transactionTypeService.findTypeById(typeId).getNaziv();
        //validacija
        if((typeName.equals("REGULARINCOME") || typeName.equals("INDIVIDUALINCOME")) && newTransaction.getItemDescription() != null) throw new Exception("Transakcije tipa INCOME nemaju opis kupljenog proizvoda.");
        if(!typeName.equals("REGULARINCOME") && !typeName.equals("REGULARPAYMENT") && newTransaction.getTransactionInterval() != null) throw new Exception("Transakcije koje nisu REGULAR tipa se ne ponavljaju periodicno.");

        //provjera da li ima dovoljno sredstava na racunu ukoliko se radi o rashodu
        if((typeName.equals("REGULARPAYMENT") || typeName.equals("INDIVIDUALPAYMENT") || typeName.equals("PURCHASE")) && !accountService.isUnderBudget(newTransaction.getAmount(), newTransaction.getIdAccount())) throw new Exception("Nemate dovoljno sredstava za ovu transakciju!");

        Transaction transaction = new Transaction(new Date(), newTransaction.getAmount(), newTransaction.getTitle(), typeId, newTransaction.getItemDescription(), newTransaction.getTransactionInterval(), newTransaction.getIdAccount());
        transactionService.save(transaction);

        //update stanja na racunu
        Account acc = accountService.findAccountById(newTransaction.getIdAccount());
        accountService.updateAccountBudget(newTransaction.getIdAccount(), acc.getBudget() + newTransaction.getAmount());

        return transaction.getId();
    }

    @PostMapping("/sort/amount")
    public List<Transaction> sortTransactionsByAmount(@RequestBody TransactionSortDTO dto){
        return transactionService.getTransactionsSortedByAmount(dto);
    }
}
