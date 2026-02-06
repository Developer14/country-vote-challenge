//package dev.loopstudio.countryvoteapi.client;
//
//import dev.loopstudio.countryvoteapi.client.CountryModel;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
//import org.springframework.cache.CacheManager;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.web.client.RestClient;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@RestClientTest
//public class CountriesClientTest {
//
//    @Mock
//    private RestClient restClient;
//
//    @Mock
//    private CacheManager cacheManager;
//
//    @InjectMocks
//    private CountriesRestClient countriesRestClient;
//
//    @Test
//    void test() {
//
//        CountryModel[] countryModels = mockCountryModelArray();
//
//        BDDMockito.given(
//                restClient.get()
//                        .uri("https://restcountries.com/v3.1/all?fields=name,capital,region,subregion")
//                        .retrieve().body(CountryModel[].class)
//        ).willReturn(countryModels);
//
//
//        List<CountryModel> countries = countriesRestClient.getCountries();
//
//        Assertions.assertFalse(countries.isEmpty());
//    }
//
//    private CountryModel[] mockCountryModelArray() {
//        return null;
//    }
//}
