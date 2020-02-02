package twino;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountRepository extends CrudRepository<Count, String> {
}
