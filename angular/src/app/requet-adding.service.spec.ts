import { TestBed } from '@angular/core/testing';

import { RequetAddingService } from './requet-adding.service';

describe('RequetAddingService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RequetAddingService = TestBed.get(RequetAddingService);
    expect(service).toBeTruthy();
  });
});
