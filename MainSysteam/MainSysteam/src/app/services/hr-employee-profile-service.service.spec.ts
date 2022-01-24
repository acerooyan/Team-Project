import { TestBed } from '@angular/core/testing';

import { HrEmployeeProfileServiceService } from './hr-employee-profile-service.service';

describe('HrEmployeeProfileServiceService', () => {
  let service: HrEmployeeProfileServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HrEmployeeProfileServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
