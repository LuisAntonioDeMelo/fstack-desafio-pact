import { Component } from '@angular/core';
import {
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatStepperModule } from '@angular/material/stepper';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { LoginService } from '../login/login.service';
import { registrarService } from './registrar.service';
import { Router } from '@angular/router';
import { Login, DadosUsuario, Token, Usuario } from '../model';

@Component({
  selector: 'app-registrar',
  standalone: true,
  imports: [
    MatButtonModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonToggleModule,
  ],
  templateUrl: './registrar.component.html',
  styleUrl: './registrar.component.css',
})
export class RegistrarComponent {
  firstFormGroup = this._formBuilder.group({
    email: ['', Validators.required],
    senha: ['', Validators.required],
    confirmaSenha: ['', Validators.required],
    role: ['', ''],
  });
  secondFormGroup = this._formBuilder.group({
    nome: ['', Validators.required],
    cpf: ['', Validators.required],
    telefone: ['', Validators.required],
    endereco: ['', Validators.required],
  });
  isLinear = false;
  showField = false;

  constructor(
    private _formBuilder: FormBuilder,
    private loginService: LoginService,
    private registrarService: registrarService,
    private router: Router
  ) {}

  async registrar() {
    if (this.firstFormGroup.valid) {
      const { email, senha, role } = this.firstFormGroup.value;
      const usuario = new Usuario(null, email, senha, role);
      const dadosUsuario = new DadosUsuario();
      await this.save(usuario);
      await this.registrarPessoa(dadosUsuario);
    }
  }

  private async save(usuario: Usuario) {
    this.registrarService.registrar(usuario).subscribe({
      next: (response) => {
        const headers = response.headers;
        console.log(response);
        console.log(headers);
        this.loginService.setToken(response.body);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  private async registrarPessoa(dadosUsuario: DadosUsuario) {
    this.registrarService.registrarDadosUsuario(dadosUsuario).subscribe({
      next: (res) => {},
      error: (error) => {
        alert('erro ao salvar');
      },
    });
    this.router.navigate(['/dashboard']);
  }
}
