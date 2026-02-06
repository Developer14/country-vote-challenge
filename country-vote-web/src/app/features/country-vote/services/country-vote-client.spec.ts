import { TestBed } from '@angular/core/testing';

import { CountryVoteClient } from './country-vote-client';

describe('CountryVoteClient', () => {
  let service: CountryVoteClient;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CountryVoteClient);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
