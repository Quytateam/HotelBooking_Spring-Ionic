import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { StorageService } from 'src/app/services/storage.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {
  isSuccessful = false;
  isSignUpFailed = false;
  isLoggedIn = false;
  isLoginFailed = false;
  roles: string[] = [];
  login = {
    username: '',
    email: '',
    password: '',
    cpassword: '',
  };
  type: boolean = true;
  constructor(
    private authService: AuthService,
    private storageService: StorageService,
    private router: Router
  ) {}

  ngOnInit() {}

  changeType() {
    this.type = !this.type;
  }

  goToHome() {
    console.log(this.login.username);
    const username = this.login.username;
    const email = this.login.email;
    const password = this.login.password;
    this.authService.register(username, email, password).subscribe({
      next: (res) => {
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.login.username = username;
        this.login.password = password;
        this.loginAction();
      },
      error: (err) => {
        this.isSignUpFailed = true;
      },
    });
    // this.router.navigate(['/preferences']);
  }

  loginAction(): void {
    const { username, password } = this.login;
    console.log(this.login);
    this.authService.login(username, password).subscribe({
      next: (res) => {
        this.storageService.saveUser(res);
        this.isLoggedIn = true;
        this.isLoginFailed = false;
        this.roles = this.storageService.getUser().roles;
        this.router.navigate(['/preferences']);
      },
      error: (err) => {
        console.log(err);
        this.isLoggedIn = false;
        this.isLoginFailed = true;
      },
    });
  }

  register(): void {}

  goToLogin() {
    this.router.navigate(['/login']);
  }
}
