package boost.service;


import boost.model.CurrencyRate;
import boost.repo.CurrencyRateRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Iterable<CurrencyRate> save(ArrayList<CurrencyRate> allRates) {
        return currencyRateRepo.saveAll(allRates);
    }



}
