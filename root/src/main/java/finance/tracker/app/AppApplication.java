package finance.tracker.app;

import finance.tracker.app.models.Account;
import finance.tracker.app.models.Transaction;
import finance.tracker.app.models.TransactionType;
import finance.tracker.app.repositories.AccountRepository;
import finance.tracker.app.repositories.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}

@Component
class DemoCommandLineRunner implements CommandLineRunner {
	@Autowired
	private TransactionTypeRepository transactionTypeRepository;
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void run(String... args) throws Exception {
		//dodamo sve tipove transakcija koji su zadani postavkom zadatka
		TransactionType INDIVIDUALPAYMENT = new TransactionType("INDIVIDUALPAYMENT");
		TransactionType REGULARPAYMENT = new TransactionType("REGULARPAYMENT");
		TransactionType PURCHASE = new TransactionType("PURCHASE");
		TransactionType INDIVIDUALINCOME = new TransactionType("INDIVIDUALINCOME");
		TransactionType REGULARINCOME = new TransactionType("REGULARINCOME");

		transactionTypeRepository.save(INDIVIDUALPAYMENT);
		transactionTypeRepository.save(REGULARPAYMENT);
		transactionTypeRepository.save(PURCHASE);
		transactionTypeRepository.save(INDIVIDUALINCOME);
		transactionTypeRepository.save(REGULARINCOME);

		//kreiramo nekoliko korisnickih racuna
		Account acc1 = new Account((double) 500);
		Account acc2 = new Account((double) 1000);

		accountRepository.save(acc1);
		accountRepository.save(acc2);

		//kreiramo nekoliko transakcija za svaki od racuna
		//Transaction t1 = new Transaction(ne)

	}
}