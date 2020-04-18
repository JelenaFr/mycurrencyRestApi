package boost.model;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Currency  {
    @NonNull
    @Size(min=3, max=3, message="Code must have 3 characters")

    private String code;
    @NonNull
    @DecimalMin(value = "0.0", inclusive = false, message="Rate must have positive number")
    private BigDecimal rate;

    @PrePersist
    @PreUpdate
    public void codeToUpper() {
        code = code.toUpperCase();
    }


}
