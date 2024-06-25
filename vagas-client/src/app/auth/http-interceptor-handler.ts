import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

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
          alert('HTTP error: ' + err.status + ' Verifique novamente.');

          console.log('error : token não encontrado');
          // router.navigate(['/login']);
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
