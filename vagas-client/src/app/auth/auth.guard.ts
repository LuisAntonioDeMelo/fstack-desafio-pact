import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { LoginService } from './login/login.service';

export const authGuard: CanActivateFn = (route, state) => {
  let loginService: LoginService = inject(LoginService);
  let router: Router = inject(Router);

  //   if (state.url !== '/registrar' && !loginService.isLoggedIn()) {
  //     alert('Nenhum usuario logado!');
  //     router.navigate(['']);
  //   }

  if (
    loginService.hasPermission('candidato') &&
    state.url == '/dashboard/admin/'
  ) {
    alert('Você não tem permissão de acesso à essa rota!');
    return false;
  }

  return true;
};
