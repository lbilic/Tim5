import { TestBed, inject } from '@angular/core/testing';

import { AddPerformanceService } from './add-performance.service';

describe('AddPerformanceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AddPerformanceService]
    });
  });

  it('should be created', inject([AddPerformanceService], (service: AddPerformanceService) => {
    expect(service).toBeTruthy();
  }));
});
