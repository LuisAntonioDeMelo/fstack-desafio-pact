import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VagasCadastroComponent } from './vagas-cadastro.component';

describe('VagasCadastroComponent', () => {
  let component: VagasCadastroComponent;
  let fixture: ComponentFixture<VagasCadastroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VagasCadastroComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VagasCadastroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
