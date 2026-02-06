import { Routes } from '@angular/router';
import { CountryVote } from './features/country-vote/country-vote';

export const routes: Routes = [
  {
    path: 'country-vote',
    component: CountryVote
  },
  {
    path: '',
    redirectTo: 'country-vote',
    pathMatch: 'full'
  }
];
