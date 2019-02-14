﻿import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { $ } from 'protractor';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit() {
  }

  loginUser(event) {
    event.preventDefault();
    const target = event.target;
    const username = target.querySelector('#username').value;
    const password = target.querySelector('#password').value;
    this.auth.getUserDetails(username, password).subscribe(
      _ => {
        this.router.navigate(['main']);
      },
      err => {
        window.alert(err.error.message);
      }
     );
     
  }

}
