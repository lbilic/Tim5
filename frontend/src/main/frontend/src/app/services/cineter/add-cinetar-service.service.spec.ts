import { TestBed, inject } from '@angular/core/testing';

import { AddCinetarServiceService } from './add-cinetar-service.service';

describe('AddCinetarServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AddCinetarServiceService]
    });
  });

  it('should be created', inject([AddCinetarServiceService], (service: AddCinetarServiceService) => {
    expect(service).toBeTruthy();
  }));
});
