package boost.model;



import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String base;

    @Column(updatable =  false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    public CurrencyRate(Currency currency, String base, Date date) {
        this.currency = currency;
        this.base = base;
        this.date = date;
    }


}

