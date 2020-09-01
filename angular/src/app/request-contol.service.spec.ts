import { TestBed } from '@angular/core/testing';

import { RequestContolService } from './request-contol.service';

describe('RequestContolService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RequestContolService = TestBed.get(RequestContolService);
    expect(service).toBeTruthy();
  });
});
