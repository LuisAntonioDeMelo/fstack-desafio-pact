import { Component, inject } from '@angular/core';
import { VagasCadastroComponent } from './vagas-cadastro/vagas-cadastro.component';
import { VagasListarComponent } from './vagas-listar/vagas-listar.component';
import { LoginService } from '../../auth/login/login.service';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-vagas',
  standalone: true,
  templateUrl: './vagas.component.html',
  styleUrl: './vagas.component.css',
  imports: [VagasCadastroComponent, VagasListarComponent, MatCardModule],
})
export class VagasComponent {
  loginService: LoginService = inject(LoginService);
}
