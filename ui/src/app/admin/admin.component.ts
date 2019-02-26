import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  greetingMessage = '';

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {
    this.greetingMessage = '';
    this.http.get('http://localhost:8080/userInfo', {
        withCredentials: true
    }).subscribe(
        data => {
          if (data['role'] == 'ADMIN') {
              this.greetingMessage = 'Добро пожаловать, администратор ' + data['name'] + '!';
          } else {
              this.router.navigate(['home']);
          }
        },
        err => {
          this.router.navigate(['login']);
        }
    );
  }

  ngOnInit() {
  }

  logout(event) {
    event.preventDefault();
    this.http.get('http://localhost:8080/logout', {
        observe: "response" as "response",
        withCredentials: true
    }).subscribe(
        (data: HttpResponse<any>) => {
            this.cookieService.set("JSESSIONID", data.headers.get("Set-Cookie"));

        }
    );
    this.router.navigate['login'];
  }

}
