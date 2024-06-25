import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login, Token, Usuario } from '../auth/model';
import { JwtPayload, jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  $api = 'http://localhost:8081/auth/login';

  constructor(public http: HttpClient) {}

  logar(login: Login): Observable<string> {
    return this.http.post<string>(this.$api, login, {
      responseType: 'text' as 'json',
    });
  }

  obterUsuario() {
    return this.jwtDecodeToken() as Usuario;
  }

  setToken(token: string) {
    const data = JSON.parse(token);
    localStorage.setItem('token', data.token);
  }

  setTokenString(token: any) {
    localStorage.setItem('token', token);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  removeToken() {
    localStorage.removeItem('token');
  }

  hasPermission(role: string) {
    let usuario = this.jwtDecodeToken() as Usuario;
    return usuario.role === role ? true : false;
  }

  isLoggedIn(): boolean {
    return this.getToken() !== null;
  }

  jwtDecodeToken(): any {
    let token = this.getToken();
    if (token) {
      return jwtDecode<JwtPayload>(token);
    }
    return '';
  }
}
