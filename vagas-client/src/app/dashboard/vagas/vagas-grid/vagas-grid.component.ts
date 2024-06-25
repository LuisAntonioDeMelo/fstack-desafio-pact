import { Component, Input, inject } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Vaga } from '../vaga.model';
import { CommonModule } from '@angular/common';
import { VButtonModule } from '../../../components/custom.module';
import { MatButtonModule } from '@angular/material/button';
import { UUID } from 'angular2-uuid';
import { Router } from '@angular/router';
import { VagaService } from '../../../services/vagas.service';
import { CandidatoSerive } from '../../../services/candidato.service';

@Component({
  selector: 'app-vagas-grid',
  standalone: true,
  templateUrl: './vagas-grid.component.html',
  styleUrl: './vagas-grid.component.css',
  imports: [MatCardModule, CommonModule, VButtonModule, MatButtonModule],
})
export class VagasGridComponent {
  @Input() vagas: Vaga[] = [];

  router = inject(Router);
  candidatoService = inject(CandidatoSerive);

  constructor() {}

  candidatar(vaga: Vaga) {
    const id_user = localStorage.getItem('id_user_role') as string;
    const data = {
      idCandidato: id_user,
      idVaga: vaga.id,
      codVaga: vaga.codigoVaga,
    };

    this.candidatoService.cadidatarParaVaga(data).subscribe({
      next: (res) => {
        alert(JSON.stringify(res));
      },
      error: (error) => {
        alert(error.message);
      },
    });
  }

  vagaDetalhe(vaga: Vaga) {
    const queryParams = JSON.stringify(vaga);
    this.router.navigate([
      '/dashboard/vagas/detalhe',
      { vagaDetalhe: queryParams },
    ]);
  }
}
