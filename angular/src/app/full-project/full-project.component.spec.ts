import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FullProjectComponent } from './full-project.component';

describe('FullProjectComponent', () => {
  let component: FullProjectComponent;
  let fixture: ComponentFixture<FullProjectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FullProjectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FullProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
