package finance.tracker.app.models;

import javax.persistence.*;

@Entity
@Table(name="transaction_types")
public class TransactionType {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String naziv;

    @OneToOne(mappedBy = "type")
    private Transaction transaction;

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
