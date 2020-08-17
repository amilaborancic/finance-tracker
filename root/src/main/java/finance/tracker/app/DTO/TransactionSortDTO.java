package finance.tracker.app.DTO;

public class TransactionSortDTO {
    Long accountId;
    Boolean orderAsc;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Boolean getOrderAsc() {
        return orderAsc;
    }

    public void setOrderAsc(Boolean orderAsc) {
        this.orderAsc = orderAsc;
    }

    public TransactionSortDTO(Long accountId, Boolean orderAsc) {
        this.accountId = accountId;
        this.orderAsc = orderAsc;
    }
    public TransactionSortDTO(){}
}
