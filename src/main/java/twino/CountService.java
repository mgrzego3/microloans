package twino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountService {
    @Autowired
    CountRepository countRepository;

    public int increaseRequestCount(String ip) {
        Optional<Count> countOptional = countRepository.findById(ip);
        if (countOptional.isPresent())
        {
            Count count = countOptional.get();
            int c = count.getCount();
            count.setCount(++c);
            countRepository.save(count);
            return c;
        } else {
            countRepository.save(new Count(ip, 1));
            return 1;
        }
    }
}
