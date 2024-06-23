import { Component } from '@angular/core';
import { VagasCadastroComponent } from './vagas-cadastro/vagas-cadastro.component';
import { VagasListarComponent } from './vagas-listar/vagas-listar.component';

@Component({
  selector: 'app-vagas',
  standalone: true,
  templateUrl: './vagas.component.html',
  styleUrl: './vagas.component.css',
  imports: [VagasCadastroComponent, VagasListarComponent],
})
export class VagasComponent {}
