import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { $ } from 'protractor';
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {environment} from "../../environments/environment";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;
  pmatch: String;
  msg: any;

  constructor(private router: Router, private http: HttpClient, private cookieService : CookieService,
              private formBuilder: FormBuilder) {}

  ngOnInit(){
      this.form = new FormGroup({
          username: new FormControl('', [Validators.required,
              Validators.pattern("[a-zA-ZА-Яа-я0-9]{3,}")]),
          fullname: new FormControl('', [Validators.required,
              Validators.pattern("[a-zA-ZА-Яа-я ]{3,}")]),
          flatnumber: new FormControl('', [Validators.required,
              Validators.min(1)]),
          email: new FormControl('', [Validators.required,
              Validators.pattern('[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}')]),
          phonenumber: new FormControl('', [Validators.required,
              Validators.pattern("[\+][0-9]{11}")]),
          password: new FormControl('', [Validators.required,
              Validators.pattern("(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[_!-])[0-9a-zA-Z_!-]{8,}")]),
          confirmpassword: new FormControl('', [Validators.required])
      });

      console.log(this.msg && this.msg['login'] != 'ok');
  }

  registerUser(event) {
    event.preventDefault();
    const target = event.target;

    const password = target.querySelector('#password').value;
    const passwordConfirm = target.querySelector('#confirmpassword').value;

    if (passwordConfirm === password) {
        this.pmatch = '';
        this.http.post(environment.backend + 'register', {
            login: target.querySelector('#username').value,
            name: target.querySelector('#fullname').value,
            flat :
                {
                    number: target.querySelector('#flatnumber').value
                },
            email: target.querySelector('#email').value,
            phone: target.querySelector('#phonenumber').value,
            password: password
        }, {
            observe: 'response' as 'response',
            withCredentials: true
        }).subscribe(
            (data : HttpResponse<any>) => {
                this.cookieService.set("JSESSIONID", data.headers.get("Set-Cookie"));
                this.router.navigate(['home']);
        },
        err => {
          this.msg = err.error;
        }
       );
    } else {
        this.pmatch = 'Введенные пароли не совпадают.';
    }
  }

}


