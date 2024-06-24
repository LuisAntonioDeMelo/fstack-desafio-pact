import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VagasDetalheComponent } from './vagas-detalhe.component';

describe('VagasDetalheComponent', () => {
  let component: VagasDetalheComponent;
  let fixture: ComponentFixture<VagasDetalheComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VagasDetalheComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VagasDetalheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
