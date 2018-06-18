import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMovieScreeningComponent } from './add-movie-screening.component';

describe('AddMovieScreeningComponent', () => {
  let component: AddMovieScreeningComponent;
  let fixture: ComponentFixture<AddMovieScreeningComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddMovieScreeningComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMovieScreeningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
