import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpVisaStatusManagementComponent } from './emp-visa-status-management.component';

describe('EmpVisaStatusManagementComponent', () => {
  let component: EmpVisaStatusManagementComponent;
  let fixture: ComponentFixture<EmpVisaStatusManagementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmpVisaStatusManagementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmpVisaStatusManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
