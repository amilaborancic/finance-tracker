package finance.tracker.app.DTO;

public class TransactionDTO {
    Double amount;
    String title;
    Long idAccount;
    Long type;
    String itemDescription;
    Integer transactionInterval;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Integer getTransactionInterval() {
        return transactionInterval;
    }

    public void setTransactionInterval(Integer transactionInterval) {
        this.transactionInterval = transactionInterval;
    }

    public TransactionDTO(Double amount, String title, Long idAccount, Long type, String itemDescription, Integer transactionInterval) {
        this.amount = amount;
        this.title = title;
        this.idAccount = idAccount;
        this.type = type;
        this.itemDescription = itemDescription;
        this.transactionInterval = transactionInterval;
    }
    public TransactionDTO(){}
}
