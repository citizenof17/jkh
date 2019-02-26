import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { $ } from 'protractor';
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient, private cookieService : CookieService) { }

  ngOnInit() {
  }

  registerUser(event) {
    event.preventDefault();
    const target = event.target;

    const password = target.querySelector('#password').value;
    const passwordConfirm = target.querySelector('#confirm_password').value;

    if (passwordConfirm === password) {
        this.http.post('http://localhost:8080/register', {
            login: target.querySelector('#username').value,
            name: target.querySelector('#fullname').value,
            flat :
                {
                    number: target.querySelector('#flatnumber').value
                },
            email: target.querySelector('#email_address').value,
            phone: target.querySelector('#phone_number').value,
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
          window.alert(err.error.message);
        }
       );
    } else {
        window.alert('Введенные пароли не совпадают.');
    }
  }

}
