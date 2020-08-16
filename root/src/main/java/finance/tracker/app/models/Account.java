package finance.tracker.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="accounts")
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Double budget;

    @OneToMany
    @JoinColumn(name="id_account", referencedColumnName = "id")
    private List<Transaction> transactions;

    public Account() {
    }
    public Account(Double budget){
        this.budget = budget;
    }

    public Long getId() {
        return id;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
