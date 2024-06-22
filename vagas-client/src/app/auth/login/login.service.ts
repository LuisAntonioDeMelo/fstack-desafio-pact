import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login, Token } from '../model';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  $api = 'http://localhost:8081/auth/login';

  constructor(public http: HttpClient) {}

  logar(login: Login): Observable<Token> {
    return this.http.post<Token>(this.$api, login, {
      responseType: 'json',
    });
  }

  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  removeToken() {
    localStorage.removeItem('token');
  }
}
