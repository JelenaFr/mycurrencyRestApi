package boost.model;



import lombok.*;


import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "currency")
public class CurrencyRate {

    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    @NonNull
    private Currency currency;
    @NonNull
    private String currencyBase;
    @NonNull
    private Date date;

    public CurrencyRate(Currency currency, String currencyBase, Date date) {
        this.currency = currency;
        this.currencyBase = currencyBase;
        this.date = date;
    }
}

