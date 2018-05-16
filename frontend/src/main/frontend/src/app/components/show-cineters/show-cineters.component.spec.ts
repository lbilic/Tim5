import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowCinetersComponent } from './show-cineters.component';

describe('ShowCinetersComponent', () => {
  let component: ShowCinetersComponent;
  let fixture: ComponentFixture<ShowCinetersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowCinetersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowCinetersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
