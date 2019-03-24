import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {environment} from "../../environments/environment";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})

export class AdminComponent implements OnInit {

  greetingMessage: String;
  countNewcomers: Number;
  countWhoDidNotSend: Number;
  flatForm: FormGroup;
  errorMessage: String = '';
  type = 0;
  report: any = {};

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {
    this.http.get(environment.backend + 'userInfo', {
        withCredentials: true
    }).subscribe(
        data => {
          if (data['role'] == 'ADMIN') {
              this.greetingMessage = 'Администратор ' + data['name'];
              this.countNewcomers = data['countNewcomers'];
              this.countWhoDidNotSend = data['countWhoDidNotSend'];
              this.type = 0;
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
      this.flatForm = new FormGroup({
              flatNum: new FormControl('', [Validators.min(1)])
          }
      );
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
      let flatNum = target.querySelector("#flatNum").value;

      let b = target.getElementsByClassName("rrange");
      let c = target.getElementsByClassName("utype");

      let rRange: String = "";
      for (let i of b) {
          if (i.checked) { rRange = i.value; }
      }

      let uType: String = "";
      for (let i of c) {
          if (i.checked) {
              uType = i.value;
          }
      }


      let q = {};
      if (rRange == "MANUAL") {
        q = {
          status: uType == 'null' ? null : uType,
          leftDate: target.querySelector("#left-b").value,
          rightDate: target.querySelector("#right-b").value,
          flat: { number: flatNum }
        };
      } else {
          q = {
              status: uType == 'null' ? null : uType,
              standardPeriod: rRange,
              flat: { number: flatNum }
          }
      }

      this.http.post(environment.backend + 'report', q, {
          withCredentials: true
      }).subscribe(
          data => {
              this.errorMessage = '';
              this.type = 1;
              this.report = data;

          },
          err => {
              this.errorMessage = err.error.message;
              this.type = 0;
          }
      );
  }

}
