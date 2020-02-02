package twino;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    LoanRepository loanRepository;

    @InjectMocks
    LoanService loanService;

    Loan loanStub1;
    Loan loanStub2;

    @BeforeEach
    void prepare() {
        loanStub1 = new Loan("1.1.1.1", 100, LocalDate.parse("2020-01-01"), LocalDate.parse("2100-01-01"));
        loanStub1.setId(1);
        loanStub1.setExtended(false);

        loanStub2 = new Loan("2.2.2.2", 500, LocalDate.parse("1900-01-01"), LocalDate.parse("2000-01-01"));
        loanStub1.setId(2);
        loanStub1.setExtended(false);
    }

    @Test
    void canExtend() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loanStub1));
        when(loanRepository.findById(2L)).thenReturn(Optional.of(loanStub2));
        assertEquals(loanService.canExtend("1.1.1.1", 1), true);
        loanStub1.setExtended(true);
        assertEquals(loanService.canExtend("1.1.1.1", 1), false);
        assertEquals(loanService.canExtend("2.2.2.2", 2), false);
    }

    @Test
    void extendLoan() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loanStub1));
        assertEquals(loanService.extendLoan("1.1.1.1", 1), true);
        assertEquals(loanService.extendLoan("1.1.1.1", 3), false);
    }
}