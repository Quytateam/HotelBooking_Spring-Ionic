import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  isSuccessful = false;
  isSignUpFailed = false;
  isLoggedIn = false;
  isLoginFailed = false;
  roles: string[] = [];
  login = {
    username: '',
    password: '',
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
    // console.log(this.login);
    const { username, password } = this.login;
    // console.log(this.login);
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

  goToForget() {
    console.log('Forget');
    this.router.navigate(['/forget-password']);
  }

  facebookLogin() {
    console.log('Facebook');
    this.router.navigate(['setting']);
  }

  twitterLogin() {
    console.log('Twitter');
    this.router.navigate(['setting']);
  }

  gmailLogin() {
    console.log('Gmail');
    this.router.navigate(['setting']);
  }

  touchLogin() {
    console.log('Touch Login');
    this.router.navigate(['/touch-id']);
  }

  faceLogin() {
    console.log('Face Login');
    this.router.navigate(['/face-id']);
  }

  goToRegister() {
    this.router.navigate(['register']);
  }
}
