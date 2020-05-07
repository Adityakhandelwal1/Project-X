import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FivetyTwoWkHighComponentComponent } from './fivety-two-wk-high-component.component';

describe('FivetyTwoWkHighComponentComponent', () => {
  let component: FivetyTwoWkHighComponentComponent;
  let fixture: ComponentFixture<FivetyTwoWkHighComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FivetyTwoWkHighComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FivetyTwoWkHighComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
