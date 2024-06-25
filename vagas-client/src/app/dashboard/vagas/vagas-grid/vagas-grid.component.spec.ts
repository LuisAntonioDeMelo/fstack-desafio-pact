import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VagasGridComponent } from './vagas-grid.component';

describe('VagasGridComponent', () => {
  let component: VagasGridComponent;
  let fixture: ComponentFixture<VagasGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VagasGridComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VagasGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
