package boost.model;



import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@ToString(of = {"id", "code","rate","base","date"})
@EqualsAndHashCode(of = {"id"})
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

    @Column(updatable =  false)
    private Date date;

    public CurrencyRate(Currency currency, String base, Date date) {
        this.currency = currency;
        this.currencyBase = base;
        this.date = date;
    }


}

