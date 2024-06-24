import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const httpInteceptorHandler: HttpInterceptorFn = (request, next) => {
  let router = inject(Router);

  let token = localStorage.getItem('token');

  if (
    token &&
    (!router.url.includes('/login') || !router.url.includes('/registrar'))
  ) {
    request = request.clone({
      setHeaders: { Authorization: 'Bearer ' + token },
    });
  }

  return next(request).pipe(
    catchError((err: any) => {
      if (err instanceof HttpErrorResponse) {
        if (err.status === 403 || err.status === 401) {
          alert('Usuario Não está mais logado');
          router.navigate(['/login']);
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
