import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnavbarComponent } from './enavbar.component';

describe('EnavbarComponent', () => {
  let component: EnavbarComponent;
  let fixture: ComponentFixture<EnavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnavbarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
