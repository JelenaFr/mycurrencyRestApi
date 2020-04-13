package boost.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private Currency currency;
    private String currencyBase;
    private Date date;

    public CurrencyRate(Currency currency, String currencyBase, Date date) {
        this.currency = currency;
        this.currencyBase = currencyBase;
        this.date = date;
    }
}

