package finance.tracker.app.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="transaction_types")
public class TransactionType {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String naziv;

    @OneToMany
    @JoinColumn(name="type_id", referencedColumnName = "id")
    private List<Transaction> transactions;

    public TransactionType() {
    }

    public TransactionType(String naziv){
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getId() {
        return id;
    }
}
