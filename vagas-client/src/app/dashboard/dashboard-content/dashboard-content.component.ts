import { Component, OnInit, inject } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { MatCardModule } from '@angular/material/card';
import { VagaService } from '../../services/vagas.service';

@Component({
  selector: 'app-dashboard-content',
  standalone: true,
  imports: [MatCardModule],
  templateUrl: './dashboard-content.component.html',
  styleUrl: './dashboard-content.component.css',
})
export class DashboardContentComponent implements OnInit {
  loginService = inject(LoginService);
  vagaService = inject(VagaService);

  status: StatusVaga = new StatusVaga();

  constructor() {}

  ngOnInit(): void {
    const id = localStorage.getItem('id_user_role') as string;
    if (id) {
      this.vagaService
        .obterStatusPorAnalista(id)
        .subscribe((res: StatusVaga) => (this.status = res));
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
