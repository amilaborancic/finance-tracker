package finance.tracker.app.services;

import finance.tracker.app.DTO.TransactionSortDTO;
import finance.tracker.app.models.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class TransactionServiceTest {
    @Autowired
    TransactionService transactionService;

    @Test
    void save() {
        //metoda koja spasava novu transakciju u bazu
        assertEquals(transactionService.countTransactions(), 5L);
        transactionService.save(new Transaction());
        assertEquals(transactionService.countTransactions(), 6L);

    }

    @Test
    void getTransactionsSortedByAmount() {
        List<Transaction> transakcijeRacuna2 = transactionService.getTransactionsForAccount(2L);
        List<Transaction> transakcijeSortiraneRastuce = transactionService.getTransactionsSortedByAmount(new TransactionSortDTO(2L, true));
        List<Transaction> transakcijeSortiraneOpadajuce = transactionService.getTransactionsSortedByAmount(new TransactionSortDTO(2L, false));

        //loop kroz ascending listu
        for (int i = 0; i < transakcijeSortiraneRastuce.size()-1; i++) {
            int j = i + 1;
            assertTrue(transakcijeSortiraneRastuce.get(i).getAmount() <= transakcijeSortiraneRastuce.get(j).getAmount());
        }
        //loop kroz descending listu
        for (int i = 0; i < transakcijeSortiraneOpadajuce.size()-1; i++) {
            int j = i + 1;
            assertTrue(transakcijeSortiraneOpadajuce.get(i).getAmount() >= transakcijeSortiraneOpadajuce.get(j).getAmount());
        }
        //poredjenje izvorne liste i sortiranih
        assertNotEquals(transakcijeRacuna2, transakcijeSortiraneRastuce);
        assertNotEquals(transakcijeRacuna2, transakcijeSortiraneOpadajuce);

        //sortirane liste za racun koji ne postoji - ocekujemo da se vrati prazna lista
        assertEquals(0, transactionService.getTransactionsSortedByAmount(new TransactionSortDTO(50L, true)).size());
    }

    @Test
    void getTransactionsByType() {

        //tip REGULARINCOME 5
        List<Transaction> regIncList = transactionService.getTransactionsByType(5L);
        assertEquals(regIncList.size(), 1);
        assertEquals(regIncList.get(0).getType(), 5L);

        //tip REGULARPAYMENT 2
        List<Transaction> regPayList = transactionService.getTransactionsByType(2L);
        assertEquals(regPayList.size(), 1);
        assertEquals(regPayList.get(0).getType(), 2L);

        //tip INDIVIDUALINCOME 4
        List<Transaction> indIncList = transactionService.getTransactionsByType(4L);
        assertEquals(indIncList.size(), 1);
        assertEquals(indIncList.get(0).getType(), 4L);

        //tip INDIVIDUALPAYMENT 1
        List<Transaction> indPayList = transactionService.getTransactionsByType(1L);
        assertEquals(indPayList.size(), 1);
        assertEquals(indPayList.get(0).getType(), 1L);

        //tip PURCHASE 3
        List<Transaction> purchaseList = transactionService.getTransactionsByType(3L);
        assertEquals(purchaseList.size(), 1);
        assertEquals(purchaseList.get(0).getType(), 3L);
    }

    @Test
    void countTransactions() {
        assertEquals(transactionService.countTransactions(), 5);
    }

    @Test
    void getTransactionsForAccount() {
        //racun 1
        List<Transaction> t1 = transactionService.getTransactionsForAccount(1L);
        assertEquals(t1.size(), 2);
        for (Transaction transaction : t1) {
            assertEquals(1L, (long) transaction.getIdAccount());
        }
        //racun 2
        List<Transaction> t2 = transactionService.getTransactionsForAccount(2L);
        assertEquals(t2.size(), 3);
        for (Transaction transaction : t2) {
            assertEquals(2L, (long) transaction.getIdAccount());
        }
    }
}