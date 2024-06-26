import { Component, Input, inject } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Vaga } from '../vaga.model';
import { CommonModule } from '@angular/common';
import { VButtonModule } from '../../../components/custom.module';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { CandidatoService } from '../../../services/candidato.service';
import Swal from 'sweetalert2';

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
  candidatoService = inject(CandidatoService);

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
        Swal.fire({
          title: 'verifique!',
          text: res.response,
          icon: 'warning',
          confirmButtonText: 'ok',
        });
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
