package twino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LoanService {
    @Autowired
    LoanRepository loanRepository;

    private static final int EXTENSION_PERIOD = 14;

    public long addLoan(String ip, long amount, LocalDate endDate) {
        Loan loan = new Loan(ip, amount, LocalDate.now(), endDate);
        loanRepository.save(loan);
        long loanId = loan.getId();
        return loanId;
    }

    public boolean canExtend(String ip, long loanId) {
        Optional<Loan> loanOptional = loanRepository.findById(loanId);
        if (loanOptional.isPresent()
                && !loanOptional.get().isExtended()
                && !LocalDate.now().isAfter(loanOptional.get().getEndDate())) {
            return true;
        } else
            return false;
    }

    public boolean extendLoan(String ip, long loanId) {
        Optional<Loan> loanOptional = loanRepository.findById(loanId);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            LocalDate endDate = loan.getEndDate();
            endDate = endDate.plusDays(14);
            loan.setEndDate(endDate);
            loan.setExtended(true);
            loanRepository.save(loan);
            return true;
        } else
            return false;
    }
}
