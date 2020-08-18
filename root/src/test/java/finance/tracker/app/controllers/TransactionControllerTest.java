package finance.tracker.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import finance.tracker.app.DTO.TransactionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import javax.print.attribute.standard.Media;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createNewTransaction() throws Exception {

        //sve uredu
        TransactionDTO t1 = new TransactionDTO((double) -200, "Desk", 1L, 3L, "Wooden work desk.", null);
        mockMvc.perform(post("/transaction/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(t1)))
                .andExpect(status().isOk());

        //INCOME transakcija sa opisom proizvoda - baca izuzetak
        TransactionDTO t2 = new TransactionDTO((double) 10, "Nesto", 1L, 4L, "nesto nesto", null);
        Assertions.assertThrows(Exception.class,
                ()->mockMvc.perform(post("/transaction/new").contentType(MediaType.APPLICATION_JSON).content(asJsonString(t2))));


        //PURCHASE transakcija se periodicno ponavlja - baca izuzetak
        TransactionDTO t3 = new TransactionDTO((double) -10, "blabla", 1L, 3L, "smlsm", 20);
        Assertions.assertThrows(Exception.class,
                ()->mockMvc.perform(post("/transaction/new").contentType(MediaType.APPLICATION_JSON).content(asJsonString(t3))));

        //prekoracen budzet - baca izuzetak
        TransactionDTO t4 = new TransactionDTO((double) -1000000, "blabla", 1L, 3L, "smlsm", null);
        Assertions.assertThrows(Exception.class,
                ()->mockMvc.perform(post("/transaction/new").contentType(MediaType.APPLICATION_JSON).content(asJsonString(t4))));

    }

}