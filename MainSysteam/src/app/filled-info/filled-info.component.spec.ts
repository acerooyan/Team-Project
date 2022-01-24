import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilledInfoComponent } from './filled-info.component';

describe('FilledInfoComponent', () => {
  let component: FilledInfoComponent;
  let fixture: ComponentFixture<FilledInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FilledInfoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FilledInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
