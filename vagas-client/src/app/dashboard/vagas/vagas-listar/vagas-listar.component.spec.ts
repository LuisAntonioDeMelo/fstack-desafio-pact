import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VagasListarComponent } from './vagas-listar.component';

describe('VagasListarComponent', () => {
  let component: VagasListarComponent;
  let fixture: ComponentFixture<VagasListarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VagasListarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VagasListarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
