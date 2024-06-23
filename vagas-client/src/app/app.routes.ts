import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { AdminComponent } from './dashboard/admin/admin.component';
import { RegistrarComponent } from './auth/registrar/registrar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CandidatosComponent } from './dashboard/candidatos/candidatos.component';
import { VagasComponent } from './dashboard/vagas/vagas.component';
import { DashboardContentComponent } from './dashboard/dashboard-content/dashboard-content.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registrar', component: RegistrarComponent },

  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      { path: '', redirectTo: 'dashboard-content', pathMatch: 'full' },
      { path: 'dashboard-content', component: DashboardContentComponent },
      { path: 'candidatos', component: CandidatosComponent },
      { path: 'admin', component: AdminComponent },
      { path: 'vagas', component: VagasComponent },
    ],
  },
];
