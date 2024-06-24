import { Component, inject } from '@angular/core';
import { LoginService } from '../../auth/login/login.service';

@Component({
  selector: 'app-candidatos',
  standalone: true,
  imports: [],
  templateUrl: './candidatos.component.html',
  styleUrl: './candidatos.component.css',
})
export class CandidatosComponent {
  loginService: LoginService = inject(LoginService);
}
