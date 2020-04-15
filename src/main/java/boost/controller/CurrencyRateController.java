package boost.controller;


import boost.model.CurrencyRate;
import boost.service.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currencyrates")
public class CurrencyRateController {

    private CurrencyService currencyService;

    public CurrencyRateController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/list")
    public Iterable<CurrencyRate> list(){
        return currencyService.list();
    }
}
