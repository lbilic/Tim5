import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HandleBiddingComponent } from './handle-bidding.component';

describe('HandleBiddingComponent', () => {
  let component: HandleBiddingComponent;
  let fixture: ComponentFixture<HandleBiddingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HandleBiddingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HandleBiddingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
