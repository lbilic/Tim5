import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoughtPropsComponent } from './bought-props.component';

describe('BoughtPropsComponent', () => {
  let component: BoughtPropsComponent;
  let fixture: ComponentFixture<BoughtPropsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BoughtPropsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoughtPropsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
