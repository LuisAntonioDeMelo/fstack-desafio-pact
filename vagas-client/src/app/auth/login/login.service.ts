import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from './Login';

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

  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  removeToken() {
    localStorage.removeItem('token');
  }
}
