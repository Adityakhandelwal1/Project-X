import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FivePLowComponentComponent } from './five-plow-component.component';

describe('FivePLowComponentComponent', () => {
  let component: FivePLowComponentComponent;
  let fixture: ComponentFixture<FivePLowComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FivePLowComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FivePLowComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
