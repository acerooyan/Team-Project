import { ComponentFixture, TestBed } from '@angular/core/testing';

import { step2Component } from './step2.component';

describe('Step2Component', () => {
  let component: step2Component;
  let fixture: ComponentFixture<step2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ step2Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(step2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
