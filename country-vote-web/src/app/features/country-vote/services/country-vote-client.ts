import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VoteEventModel } from '../models/vote.event.model';
import { CountryVotesModel } from '../models/country-vote.model';
import { VoteRequestModel } from '../models/vote-request.model';

@Injectable({
  providedIn: 'root',
})
export class CountryVoteClient {

  private httpClient: HttpClient = inject(HttpClient);

  public voteForCountry(request: VoteRequestModel): Observable<void> {
    return this.httpClient.post<void>('/api/v1/votes', request);
  }

  public getCountryVotes(): Observable<CountryVotesModel[]> {
    return this.httpClient.get<CountryVotesModel[]>('/api/v1/countries/most-voted');
  }
}
