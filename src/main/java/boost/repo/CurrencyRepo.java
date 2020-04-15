package boost.repo;

import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface CurrencyRepo extends CrudRepository <String, BigDecimal> {
}
