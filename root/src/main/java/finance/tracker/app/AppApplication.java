package finance.tracker.app;

import finance.tracker.app.models.Account;
import finance.tracker.app.models.Transaction;
import finance.tracker.app.models.TransactionType;
import finance.tracker.app.repositories.AccountRepository;
import finance.tracker.app.repositories.TransactionRepository;
import finance.tracker.app.repositories.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

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
	@Autowired
	private TransactionRepository transactionRepository;

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

		Long purchaseId = transactionTypeRepository.findByNazivContaining("PURCHASE").getId();
		Long regIncId = transactionTypeRepository.findByNazivContaining("REGULARINCOME").getId();
		Long regPayId = transactionTypeRepository.findByNazivContaining("REGULARPAYMENT").getId();
		Long indIncId = transactionTypeRepository.findByNazivContaining("INDIVIDUALINCOME").getId();
		Long indPayId = transactionTypeRepository.findByNazivContaining("INDIVIDUALPAYMENT").getId();

		//kreiramo nekoliko transakcija za svaki od racuna
		Transaction t1 = new Transaction(new Date(), -5.55, "100 Sticker Pack", purchaseId, "A set of 100 Naruto stickers", null, 1L);
		Transaction t2 = new Transaction(new Date(), -40.35, "Racun za struju", regPayId, "Racun za struju za tekuci mjesec", 30, 2L);
		Transaction t3 = new Transaction(new Date(), (double) -100, "Slanje novca ka inostranstvu", indPayId, "Slanje novca", null, 2L);
		Transaction t4 = new Transaction(new Date(), (double) 200, "Prihod od narudzbe iz online storea", indIncId, "Neko je kupio Vas artikal.", null, 1L);
		Transaction t5 = new Transaction(new Date(), (double) 1500, "Plata", regIncId, "Plata za tekuci mjesec", 30, 2L);

		transactionRepository.save(t1);
		transactionRepository.save(t2);
		transactionRepository.save(t3);
		transactionRepository.save(t4);
		transactionRepository.save(t5);

	}
}