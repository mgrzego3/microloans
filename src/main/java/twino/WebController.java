package twino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WebController {

    @Autowired
    LoanController loanController;

    @GetMapping(path = "getloan")
    public Map<String,Long> getLoan(@RequestParam(name="amount", defaultValue = "-1") String amt,
            @RequestParam(name="enddate", defaultValue = "-1")String ed,
            HttpServletRequest request)
    {
        String ip = request.getRemoteAddr();
        int amount = -1;
        LocalDate endTime = null;
        try {
            amount = Integer.parseInt(amt);
            endTime = LocalDate.parse(ed);
        } catch ( NumberFormatException |DateTimeParseException ex) {
            System.out.println(ex.getMessage());
        }

        long loanId = loanController.getLoan(ip, amount, endTime);
        return Collections.singletonMap("loanId", loanId);
    }

    @GetMapping(path = "extendloan")
    public Map<String, String> extendLoan(@RequestParam(name="loanid", defaultValue = "-1") String loId, HttpServletRequest request)
    {
        String ip = request.getRemoteAddr();
        long loanId = -1;
        try {
            loanId = Long.parseLong(loId);
        } catch ( NumberFormatException ex) {}
        boolean ret = loanController.extendLoan(ip, loanId);
        return Collections.singletonMap("status", ret ? "ok" : "false");
    }
}
