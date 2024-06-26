import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import Swal from 'sweetalert2';

export const httpInteceptorHandler: HttpInterceptorFn = (request, next) => {
  let router = inject(Router);

  let token = localStorage.getItem('token');

  if (
    token &&
    (!router.url.includes('/login') ||
      !router.url.includes('/registrar') ||
      !router.url.includes('/'))
  ) {
    request = request.clone({
      setHeaders: { Authorization: 'Bearer ' + token },
    });
  }

  return next(request).pipe(
    catchError((err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 403 || err.status === 401) {
          Swal.fire({
            title: 'Error!',
            text: 'Insira todos os dados antes de se registrar!',
            icon: 'error',
            confirmButtonText: 'ok',
          });

          Swal.fire({
            title: 'HTTP error',
            text: 'HTTP error: ' + err.status + ' Verifique novamente.',
            //showDenyButton: true,
            showCancelButton: true,
            confirmButtonText: 'Logout',
            denyButtonText: `Continuar`,
          }).then((result) => {
            if (result.isConfirmed) {
              router.navigate(['/login']);
              Swal.fire('Logout', 'Você irá deslogar', 'warning');
            } else if (result.isDenied) {
              Swal.fire('ok', '', 'info');
            }
          });
        } else {
          console.error('HTTP error:', err);
        }
      } else {
        console.error('An error occurred:', err);
      }

      return throwError(() => err);
    })
  );
};
