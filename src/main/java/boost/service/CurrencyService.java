package boost.service;


import boost.model.CurrencyRate;
import boost.repo.CurrencyRateRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {
    private CurrencyRateRepo currencyRateRepo;

    public CurrencyService(CurrencyRateRepo currencyRateRepo) {
        this.currencyRateRepo = currencyRateRepo;
    }


    public  Iterable<CurrencyRate>list(){
        return currencyRateRepo.findAll();
    }
    public CurrencyRate save (CurrencyRate currencyRate){
        return currencyRateRepo.save(currencyRate);
    }
    public Iterable<CurrencyRate> save(List<CurrencyRate> allRates) {
        return currencyRateRepo.save(allRates);
    }



}
