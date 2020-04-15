package boost.controller;


import boost.model.CurrencyRate;
import boost.repo.CurrencyRateRepo;
import boost.repo.CurrencyRepo;
import boost.service.CurrencyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("message")
public class MainController {

    @Autowired
    CurrencyRateRepo currencyRateRepo;
//    @Autowired
//    CurrencyRepo currencyRepo;
//    @Autowired
//    CurrencyService currencyService;


    @GetMapping
    public Iterable<CurrencyRate> list(){
        return currencyRateRepo.findAll();
    }


    @GetMapping("{id}")
    Optional<CurrencyRate> findById(@PathVariable  Long id) {
        return currencyRateRepo.findById(id);
    }

//    @GetMapping("{id}")
////    Iterable<CurrencyRate> findById(@PathVariable("id")  CurrencyRate currencyRate) {
////        return (Iterable<CurrencyRate>) currencyRate;
////    } ne rabotajet

    @PostMapping
    public CurrencyRate create (@RequestBody CurrencyRate newRate){
        return currencyRateRepo.save(newRate);

    }
    @PutMapping("{id}")
    public CurrencyRate update (@PathVariable("id")  CurrencyRate rateFromDB, @RequestBody CurrencyRate currencyRate){
        BeanUtils.copyProperties(currencyRate, rateFromDB, "id");
        return currencyRateRepo.save(rateFromDB);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") CurrencyRate currencyRate){
        currencyRateRepo.delete(currencyRate);
    }




}
