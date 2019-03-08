import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { $ } from 'protractor';
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import { environment} from "../../environments/environment";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient, private cookieService : CookieService) {
  }

  ngOnInit() {
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
            } else {
              this.router.navigate(['admin']);
            }
      },
      err => {
        window.alert(err.error.message);
      }
     );
  }


}
