import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SellPropsComponent } from './sell-props.component';

describe('SellPropsComponent', () => {
  let component: SellPropsComponent;
  let fixture: ComponentFixture<SellPropsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SellPropsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SellPropsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
