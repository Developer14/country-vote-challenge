import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CountryModel } from '../models/country.model';

@Injectable({
  providedIn: 'root',
})
export class CountriesClient {
  
  private httpClient: HttpClient = inject(HttpClient);

  public getCountries(): Observable<CountryModel[]> {
    return this.httpClient.get<CountryModel[]>('/api/v1/countries');
  }
}
