import { Component } from '@angular/core';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  standalone: true,
  imports: [MatSlideToggleModule],
})
export class HomeComponent {
  constructor(private router: Router) {}

  irParaVaga() {
    //logica se estiver logado, ou redireciona para logar
    this.router.navigate(['/login']);
  }
}
