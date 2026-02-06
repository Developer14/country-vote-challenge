import { TestBed } from '@angular/core/testing';

import { CountriesClient } from './countries-client';

describe('CountriesClient', () => {
  let service: CountriesClient;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CountriesClient);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
