import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CountryVote } from './country-vote';

describe('CountryVote', () => {
  let component: CountryVote;
  let fixture: ComponentFixture<CountryVote>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CountryVote]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CountryVote);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
