package finance.tracker.app.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
class TransactionTypeServiceTest {

    @Autowired
    TransactionTypeService transactionTypeService;

    @Test
    void findTypeById() {
        assertEquals(transactionTypeService.findTypeById(1L).getNaziv(), "INDIVIDUALPAYMENT");
        assertEquals(transactionTypeService.findTypeById(2L).getNaziv(), "REGULARPAYMENT");
        assertEquals(transactionTypeService.findTypeById(3L).getNaziv(), "PURCHASE");
        assertEquals(transactionTypeService.findTypeById(4L).getNaziv(), "INDIVIDUALINCOME");
        assertEquals(transactionTypeService.findTypeById(5L).getNaziv(), "REGULARINCOME");

    }
}