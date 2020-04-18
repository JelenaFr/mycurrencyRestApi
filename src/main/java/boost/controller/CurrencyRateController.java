package boost.controller;

import boost.model.Currency;
import boost.model.CurrencyRate;
import boost.repo.CurrencyRateRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
 import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("currencyRate")
public class CurrencyRateController {

    @Autowired
    CurrencyRateRepo currencyRateRepo;

    @GetMapping
    public List<CurrencyRate> list() {
        return currencyRateRepo.findAll();
    }

    @GetMapping("{id}")
    public CurrencyRate getOne(@PathVariable("id") CurrencyRate currencyRate) {
        return currencyRate;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CurrencyRate create(@Valid @RequestBody CurrencyRate currencyRate) {
        currencyRate.setDate(Date.from(Instant.now()));
        currencyRate.setBase("EUR");
        return currencyRateRepo.save(currencyRate);

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CurrencyRate update(@PathVariable("id") CurrencyRate rateFromDB, @Valid
    @RequestBody CurrencyRate currencyRate) {
        if (rateFromDB != null) {
            BeanUtils.copyProperties(currencyRate, rateFromDB, "id");
            return currencyRateRepo.save(rateFromDB);
        }
        return null;

    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") CurrencyRate currencyRate) {
        currencyRateRepo.delete(currencyRate);
    }

}
