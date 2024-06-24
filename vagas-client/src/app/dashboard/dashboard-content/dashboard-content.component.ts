import { Component, inject } from '@angular/core';
import { LoginService } from '../../auth/login/login.service';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-dashboard-content',
  standalone: true,
  imports: [MatCardModule],
  templateUrl: './dashboard-content.component.html',
  styleUrl: './dashboard-content.component.css',
})
export class DashboardContentComponent {
  loginService = inject(LoginService);
}
