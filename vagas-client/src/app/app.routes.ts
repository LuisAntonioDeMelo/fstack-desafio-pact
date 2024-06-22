import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { AdminComponent } from './admin/admin.component';
import { RegistrarComponent } from './auth/registrar/registrar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CandidatosComponent } from './candidatos/candidatos.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registrar', component: RegistrarComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'candidatos', component: CandidatosComponent },
  { path: 'dashboard', component: DashboardComponent },
];
