import { Component, effect, input } from '@angular/core';
import { CountryVotesModel } from '../../models/country-vote.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'cv-most-voted-view',
  imports: [CommonModule, FormsModule],
  templateUrl: './most-voted-view.html',
  styleUrl: './most-voted-view.scss'
})
export class MostVotedView {

  public countryVotesArray = input<CountryVotesModel[]>([]);

  protected filteredCountryVotesArray: CountryVotesModel[] = [];
  protected searchText: string = '';

  constructor() {
    effect(() => {
      this.filteredCountryVotesArray = this.countryVotesArray();
    });
  }

  filterCountries() {
    if (!this.searchText) {
      this.filteredCountryVotesArray = this.countryVotesArray();
      return;
    }
    this.filteredCountryVotesArray = this.countryVotesArray().filter(countryVote =>
      this.checkCoincidence(countryVote.country, this.searchText) ||
      this.checkCoincidence(countryVote.capital, this.searchText) ||
      this.checkCoincidence(countryVote.region, this.searchText) ||
      this.checkCoincidence(countryVote.subregion, this.searchText)
    );
  }

  private checkCoincidence(value: string, searchText: string): boolean {
    return value.toLowerCase().includes(searchText.toLowerCase());
  }
}
