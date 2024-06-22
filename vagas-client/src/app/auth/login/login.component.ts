import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
import { Login } from './Login';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  login: Login;

  constructor(public loginService: LoginService, public router: Router) {
    this.login = new Login();
    this.loginService.removeToken();
  }

  logar() {
    this.loginService.logar(this.login).subscribe({
      next: (token) => {
        if (token) {
          this.loginService.setToken(token);
          this.router.navigate(['/admin']);
          console.log(token);
        } else {
          alert('usuario e senha incorretos');
        }
      },
      error: (erro) => {
        alert('deu erro');
      },
    });
  }
}
