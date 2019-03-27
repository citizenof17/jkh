import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { $ } from 'protractor';
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import { environment} from "../../environments/environment";
import {FormGroup, Validators, FormBuilder, FormControl} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  form: FormGroup;
  msg: String;

  constructor(private router: Router, private http: HttpClient,
                private cookieService : CookieService, private formBuilder: FormBuilder) {}

  ngOnInit() {
      this.form = new FormGroup({
          username: new FormControl('', [Validators.required,
                                    Validators.pattern("[a-zA-ZА-Яа-я0-9]{3,}")]),
          password: new FormControl('', [Validators.required])
      });
  }

  loginUser(event) {
    event.preventDefault();
    const target = event.target;

    const username = target.querySelector('#username').value;
    const password = target.querySelector('#password').value;

    this.http.post(environment.backend + 'login', {
          login: username,
          password: password
      }, {
          observe: 'response' as 'response',
          withCredentials: true
      }).subscribe(
        (data : HttpResponse<any>) => {
            this.cookieService.set("JSESSIONID", data.headers.get("Set-Cookie"));
            if (data.body['role'] == 'USER') {
              this.router.navigate(['home']);
            } else if (data.body['role'] == 'ADMIN') {
              this.router.navigate(['admin']);
            }
      },
      err => {
        this.msg = err.error.message;
      }
     );
  }


}
