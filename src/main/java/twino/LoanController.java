package twino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Component
public class LoanController {
    public static final int MAX_AMOUNT = 5000;
    public static final LocalTime RISKY_MIN_TIME = LocalTime.of(0,0);
    public static final LocalTime RISKY_MAX_TIME = LocalTime.of(6,0);
    public static final int MAX_REQUESTS = 3;

    @Autowired
    CountService countService;

    @Autowired
    LoanService loanService;

    public LoanController() { }


    public long getLoan(String ip, long amount, LocalDate endDate) {
        int count = countService.increaseRequestCount(ip);
        if (count > MAX_REQUESTS)
            return -1;
        LocalTime now = LocalTime.now();
        // check if end time is proper and after current date
        if (endDate == null || !endDate.isAfter(LocalDate.now()))
            return -1;

        if (amount <= 0 || amount > MAX_AMOUNT)
            return -1;

        // check if time is between 6 and 0
        if (now.isBefore(RISKY_MIN_TIME) || now.isAfter(RISKY_MAX_TIME)) {
            return loanService.addLoan(ip, amount, endDate);
        } else {
            if (amount == MAX_AMOUNT)
                return -1;
            return loanService.addLoan(ip, amount, endDate);
        }
    }

    public boolean extendLoan(String ip, long loanId) {
        if (loanService.canExtend(ip, loanId)) {
            loanService.extendLoan(ip, loanId);
            return true;
        } else
            return false;
    }
}
