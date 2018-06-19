import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyPropsComponent } from './my-props.component';

describe('MyPropsComponent', () => {
  let component: MyPropsComponent;
  let fixture: ComponentFixture<MyPropsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyPropsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyPropsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
