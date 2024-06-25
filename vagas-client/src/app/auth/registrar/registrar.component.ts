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
import { LoginService } from '../../services/login.service';
import { RegistrarService } from '../../services/registrar.service';
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
  tokenUser: any;

  constructor(
    private _formBuilder: FormBuilder,
    private loginService: LoginService,
    private registrarService: RegistrarService,
    private router: Router
  ) {}

  async registrar() {
    if (this.firstFormGroup.valid || this.secondFormGroup.valid) {
      const { email, senha, role } = this.firstFormGroup.value;
      const usuario = new Usuario(null, email, senha, role);

      try {
        const token = await this.registrarAuth(usuario);
        this.loginService.setToken(token);
        this.tokenUser = this.loginService.obterUsuario();

        const { id, role } = this.tokenUser;
        const { nome, endereco, telefone, cpf } = this.secondFormGroup.value;
        const dadosUsuario = {
          id,
          nome,
          email,
          endereco,
          telefone,
          cpf,
          role,
        } as DadosUsuario;

        this.registrarDadosUsuario(dadosUsuario);
      } catch (erro) {
        alert('erro ao registrar!' + erro);
        throw erro;
      }
    } else {
      alert('Preencha os campos antes de se registrar!');
    }
  }

  registrarAuth(usuario: Usuario): Promise<string> {
    return new Promise((resolve, reject) => {
      this.registrarService.registrarAuth(usuario).subscribe({
        next: (token) => resolve(token),
        error: (error) => reject(error),
      });
    });
  }

  registrarDadosUsuario(dadosUsuario: DadosUsuario) {
    console.log(dadosUsuario);
    this.registrarService.registrarDadosUsuario(dadosUsuario).subscribe({
      next: (res) => {
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        alert('error ao salvar:: ' + error.error);
      },
    });
    this.router.navigate(['/dashboard']);
  }
}
