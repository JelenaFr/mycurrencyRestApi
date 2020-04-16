package boost.controller;

import boost.model.CurrencyRate;
import boost.repo.CurrencyRateRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("currencyRate")
public class MainController {

    @Autowired
    CurrencyRateRepo currencyRateRepo;


    @GetMapping
    public List<CurrencyRate> list() {
        return currencyRateRepo.findAll();
    }


    //    @GetMapping("{id}")
//    Optional<CurrencyRate> findById(@PathVariable  Long id) {
//        return currencyRateRepo.findById(id);
//    }
    @GetMapping("{id}")
    public CurrencyRate getOne(@PathVariable("id") CurrencyRate currencyRate) {
        return currencyRate;
    }
//    @PostMapping
//    public Map<String, String> create(@ResponseBody CurrencyRate currencyRate){
//        return currencyRateRepo.save(currencyRate);
//    }

    @PostMapping
    public CurrencyRate create(@RequestBody CurrencyRate currencyRate) {
        currencyRate.setDate(Date.from(Instant.now()));
        return currencyRateRepo.save(currencyRate);

    }

    @PutMapping("{id}")
    public CurrencyRate update(@PathVariable("id") CurrencyRate rateFromDB,
                               @RequestBody CurrencyRate currencyRate) {
        BeanUtils.copyProperties(currencyRate, rateFromDB, "id");
        return currencyRateRepo.save(rateFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") CurrencyRate currencyRate) {
        currencyRateRepo.delete(currencyRate);
    }


}
