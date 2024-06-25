import { Component, Input, inject } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Vaga } from '../vaga.model';
import { CommonModule } from '@angular/common';
import { VButtonModule } from '../../../components/custom.module';
import { MatButtonModule } from '@angular/material/button';
import { UUID } from 'angular2-uuid';
import { Router } from '@angular/router';
import { VagaService } from '../vagas.service';

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
  vagaService = inject(VagaService);

  constructor() {}

  candidatar(idVaga: UUID) {
    const id_user = localStorage.getItem('id_user_role') as string;
    const data = {
      idCanditado: id_user,
      idVaga: idVaga,
    };
    this.vagaService.cadidatarParaVaga(data).subscribe({
      next: (res) => {
        alert(res);
      },
      error: (error) => {
        alert(error);
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
