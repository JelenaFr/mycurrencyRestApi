package boost.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Currency {
    @Column(name = "currency")
    private String code;
    private BigDecimal currencyRate;
}
