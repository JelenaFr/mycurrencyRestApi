package boost.jsonobject;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Data
public class JsonCurrencyResponse {
    Map<String, BigDecimal> rates;
    String base;
    Date date;
}
