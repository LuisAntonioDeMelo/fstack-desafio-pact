import { Component, OnInit } from '@angular/core';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { Router } from '@angular/router';
import { LoginService } from '../auth/login/login.service';
import { VagaService } from '../dashboard/vagas/vagas.service';
import { Vaga } from '../dashboard/vagas/vaga.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  standalone: true,
  imports: [MatSlideToggleModule],
})
export class HomeComponent implements OnInit {
  vagas: Vaga[] = [];

  constructor(
    private loginService: LoginService,
    private router: Router,
    private vagaService: VagaService
  ) {}

  ngOnInit(): void {
    this.vagaService.obterVagasHome().subscribe({
      next: (vagas: Vaga[]) => {
        console.log(vagas);
        this.vagas = vagas;
      },
      error: () => {},
    });
  }

  irParaVaga() {
    if (this.loginService.isLoggedIn()) {
      //  this.vagaService.
      this.router.navigate(['/dashboard/vagas/id']);
    } else {
      alert('... ');
      this.router.navigate(['/login']);
    }
  }
}
