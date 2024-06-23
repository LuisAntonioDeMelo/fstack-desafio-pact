import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { Login, Token } from '../model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  login: Login = new Login();

  constructor(public loginService: LoginService, public router: Router) {
    this.loginService.removeToken();
  }

  logar() {
    this.loginService.logar(this.login).subscribe({
      next: (token) => {
        if (token) {
          this.loginService.setToken(token);
        } else {
          alert('usuario e senha incorretos');
        }

        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        alert(error.error);
      },
    });
  }
}
