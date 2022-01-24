import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowDeatilComponent } from './show-deatil.component';

describe('ShowDeatilComponent', () => {
  let component: ShowDeatilComponent;
  let fixture: ComponentFixture<ShowDeatilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowDeatilComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowDeatilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
