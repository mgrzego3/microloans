package twino;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Loan {
    public Loan() {

    }

    public Loan(String ip, long amount, LocalDate startDate, LocalDate endDate) {
        this.ip = ip;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.extended = false;
    }

    @Id
    @GeneratedValue
    private long id;
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    private String ip;
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    private long amount;
    public long getAmount() { return amount; }
    public void setAmount(long amount) { this.amount = amount; }

    private LocalDate startDate;
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    private LocalDate endDate;
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    private boolean extended;
    public boolean isExtended() { return extended; }
    public void setExtended(boolean extended) { this.extended = extended; }
}
