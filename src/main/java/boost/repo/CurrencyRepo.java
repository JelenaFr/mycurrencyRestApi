package boost.repo;

import boost.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CurrencyRepo extends JpaRepository<Currency, Long> {



}
