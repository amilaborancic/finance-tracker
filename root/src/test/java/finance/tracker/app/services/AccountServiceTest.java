package finance.tracker.app.services;

import finance.tracker.app.models.Account;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//unit testovi za account servis
@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Test
    void findAccountById() {
        Account acc = accountService.findAccountById(1L);
        //ocekujemo da ovaj account postoji i da ima id jednak 1
        assertNotNull(acc);
        assertEquals(1L, acc.getId());

        //racun sa id-jem 50 ne postoji pa ocekujemo da metoda vrati null kao rezultat pretrage
        assertThat(accountService.findAccountById(50L) == null);
    }

    @Test
    void isUnderBudget() {
        Account acc = accountService.findAccountById(1L);
        //stvarni budzet racuna 1 iznosi 500
        assertTrue(-50 < acc.getBudget());
        assertEquals(500, (double) acc.getBudget());
        assertTrue(4000 > acc.getBudget());
    }

    @Test
    void updateAccountBudget() {
        //trenutni budzet racuna 2 iznosi 1000
        accountService.updateAccountBudget(2L, (double) 200);
        assertEquals(accountService.findAccountById(2L).getBudget(), 200);
        //izmjena na visu vrijednost
        accountService.updateAccountBudget(2L, (double) 2000);
        assertEquals(accountService.findAccountById(2L).getBudget(), 2000);
    }
}