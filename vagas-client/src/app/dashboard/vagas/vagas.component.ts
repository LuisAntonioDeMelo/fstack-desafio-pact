import { Component, OnInit, inject } from '@angular/core';
import { VagasListarComponent } from './vagas-listar/vagas-listar.component';
import { LoginService } from '../../services/login.service';
import { MatCardModule } from '@angular/material/card';
import { VagaService } from '../../services/vagas.service';
import { Vaga } from './vaga.model';
import { VagasGridComponent } from './vagas-grid/vagas-grid.component';

@Component({
  selector: 'app-vagas',
  standalone: true,
  templateUrl: './vagas.component.html',
  styleUrl: './vagas.component.css',
  imports: [VagasListarComponent, MatCardModule, VagasGridComponent],
})
export class VagasComponent implements OnInit {
  vagas: Vaga[] = [];
  constructor(
    public loginService: LoginService,
    private vagasService: VagaService
  ) {}

  ngOnInit(): void {
    const idUser = localStorage.getItem('id_user_role') as string;

    if (this.loginService.hasPermission('analista')) {
      this.vagasService.obterVagasPorAnalista(idUser).subscribe({
        next: (res) => {
          this.vagas = [...res];
        },
        error: (error) => {
          console.log(error);
        },
      });
    } else {
      this.vagasService.obterVagasHome().subscribe({
        next: (res) => {
          this.vagas = [...res];
        },
      });
    }
  }
}
