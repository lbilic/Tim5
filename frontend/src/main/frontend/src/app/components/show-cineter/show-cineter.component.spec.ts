import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowCineterComponent } from './show-cineter.component';

describe('ShowCineterComponent', () => {
  let component: ShowCineterComponent;
  let fixture: ComponentFixture<ShowCineterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowCineterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowCineterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
