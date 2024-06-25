import { Component, OnInit, inject } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { MatCardModule } from '@angular/material/card';
import { VagaService } from '../../services/vagas.service';
import { CommonModule } from '@angular/common';
import { CandidatoService } from '../../services/candidato.service';
import { CandidatoInteressado } from '../candidatos/candidatos.component';

@Component({
  selector: 'app-dashboard-content',
  standalone: true,
  imports: [MatCardModule, CommonModule],
  templateUrl: './dashboard-content.component.html',
  styleUrl: './dashboard-content.component.css',
})
export class DashboardContentComponent implements OnInit {
  loginService = inject(LoginService);
  vagaService = inject(VagaService);
  candidatoService = inject(CandidatoService);
  candidatos: CandidatoInteressado[] = [];

  status: StatusVaga = new StatusVaga();

  constructor() {}

  ngOnInit(): void {
    const id = localStorage.getItem('id_user_role') as string;
    if (id && this.loginService.hasPermission('analista')) {
      this.vagaService
        .obterStatusPorAnalista(id)
        .subscribe((res: StatusVaga) => (this.status = res));
    }

    if (id && this.loginService.hasPermission('candidato')) {
      this.candidatoService.obterVagasIngressadas(id).subscribe({
        next: (res: CandidatoInteressado[]) => {
          this.candidatos = res;
        },
        error: (error) => {
          alert(error.message);
        },
      });
    }
  }
}

class StatusVaga {
  qtdCriada!: number;
  qtdAberta!: number;
  qtdEmProcesso!: number;
  qtdFechada!: number;
  qtdCancelada!: number;
  constructor() {}
}
