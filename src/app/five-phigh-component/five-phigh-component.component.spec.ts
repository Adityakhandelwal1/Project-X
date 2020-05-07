import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FivePHighComponentComponent } from './five-phigh-component.component';

describe('FivePHighComponentComponent', () => {
  let component: FivePHighComponentComponent;
  let fixture: ComponentFixture<FivePHighComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FivePHighComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FivePHighComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
