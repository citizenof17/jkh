import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  greetingMessage = '';
  report: any = {
      message: "Здесь будут отображаться Ваши отчеты."
  };

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {
    this.greetingMessage = '';
    this.http.get(environment.backend + 'userInfo', {
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
    this.http.get(environment.backend + 'logout', {
        observe: "response" as "response",
        withCredentials: true
    }).subscribe();
    this.router.navigate(['login']);
  }

  showManual() {
      document.getElementById('div1').style.display = 'block';
  }

  hideManual() {
        document.getElementById('div1').style.display = 'none';
  }

  reportAll(event) {
      event.preventDefault();
      const target = event.target;
      let flatNum = target.querySelector("#flat").value;
      let a = target.getElementsByTagName('input');

      let repType: String = "";
      for (let i of a) {
          if (i.checked) {
              repType = i.value;
          }
      }

      let q = {};
      if (repType == "MANUAL") {
        q = {
          leftDate: target.querySelector("#left-b").value,
          rightDate: target.querySelector("#right-b").value,
          flat: { number: flatNum}
        };
      } else {
          q = {
              standardPeriod: repType,
              flat: {number: flatNum}
          }
      }

      this.http.post(environment.backend + 'report', q, {
          withCredentials: true
      }).subscribe(
          data => {
                this.report = data;
          },
          err => {
              window.alert(err.error.message);
          }
      );
  }

}
