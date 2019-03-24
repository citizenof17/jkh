import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DebitorsComponent } from './debitors.component';

describe('DebitorsComponent', () => {
  let component: DebitorsComponent;
  let fixture: ComponentFixture<DebitorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DebitorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DebitorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
