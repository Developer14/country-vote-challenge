import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostVotedView } from './most-voted-view';

describe('MostVotedView', () => {
  let component: MostVotedView;
  let fixture: ComponentFixture<MostVotedView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MostVotedView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MostVotedView);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
