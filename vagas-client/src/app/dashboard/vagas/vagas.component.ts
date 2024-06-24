import { Component, OnInit, inject } from '@angular/core';
import { VagasListarComponent } from './vagas-listar/vagas-listar.component';
import { LoginService } from '../../auth/login/login.service';
import { MatCardModule } from '@angular/material/card';
import { VagaService } from './vagas.service';
import { Vaga } from './vaga.model';

@Component({
  selector: 'app-vagas',
  standalone: true,
  templateUrl: './vagas.component.html',
  styleUrl: './vagas.component.css',
  imports: [VagasListarComponent, MatCardModule],
})
export class VagasComponent implements OnInit {
  vagas: Vaga[] = [];
  constructor(
    public loginService: LoginService,
    private vagasService: VagaService
  ) {}
  ngOnInit(): void {
    this.vagas = [...this.vagasService.getVagas()];
  }
}
