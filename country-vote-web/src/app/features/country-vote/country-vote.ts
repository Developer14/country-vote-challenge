import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { UserForm } from "./components/user-form/user-form";
import { MostVotedView } from "./components/most-voted-view/most-voted-view";
import { CountriesClient } from './services/countries-client';
import { VoteEventModel } from './models/vote.event.model';
import { CountryVoteClient } from './services/country-vote-client';
import { catchError, forkJoin, Observable, of, switchMap } from 'rxjs';
import { CountryVotesModel } from './models/country-vote.model';

@Component({
  selector: 'cv-country-vote',
  imports: [UserForm, MostVotedView],
  templateUrl: './country-vote.html',
  styleUrl: './country-vote.scss',
})
export class CountryVote implements OnInit {

  changeDetector = inject(ChangeDetectorRef);

  private countriesClient = inject(CountriesClient);
  private countryVoteClient = inject(CountryVoteClient);

  protected countryVotesArray: CountryVotesModel[] = [];

  constructor() { }

  ngOnInit(): void {
  
    forkJoin({
      countries: this.countriesClient.getCountries(),
      countryVotes: this.countryVoteClient.getCountryVotes() 
    }).subscribe({
      next: ({ countries, countryVotes }) => {
        this.updateMostVotedView(countryVotes);
      },
      error: (err) => console.error('Error fetching data:', err)
    });
  }

  protected saveUser(voteEvent: VoteEventModel) {
    console.log('User data submitted:', voteEvent.request);
    this.countryVoteClient.voteForCountry(voteEvent.request).pipe(
      switchMap(() => this.countryVoteClient.getCountryVotes())
    ).subscribe({
      next: (votes) => this.updateMostVotedView(votes),
      error: (err) => this.notifyError(err, () => voteEvent.callback(err.error?.details || 'An error occurred')),
      complete: () => voteEvent.callback('success')
    });
  }

  private updateMostVotedView(countryVotes: CountryVotesModel[]) {
    this.countryVotesArray = countryVotes;
    this.changeDetector.detectChanges();
  }

  private notifyError(error: any, callback?: () => void) {
    console.error('Error:', error);
    if (callback) {
      callback();
    }
  }
}
