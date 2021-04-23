package boost.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Currency {
    @NonNull
    @Size(min = 3, max = 3, message = "Code must have 3 characters")
    private String code;

    @NonNull
    @DecimalMin(value = "0.0", inclusive = false, message = "Rate must have positive number")
    private BigDecimal rate;

    @PrePersist
    @PreUpdate
    public void codeToUpper() {
        code = code.toUpperCase();
    }


}
