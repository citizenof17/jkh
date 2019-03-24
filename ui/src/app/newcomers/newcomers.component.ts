import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-newcomers',
  templateUrl: './newcomers.component.html',
  styleUrls: ['./newcomers.component.css']
})
export class NewcomersComponent implements OnInit {

  greetingMessage: String;
  errorMessage: any;
  report: any;

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {
    this.http.get(environment.backend + 'userInfo', {
        withCredentials: true
      }).subscribe(
          data => {
            if (data['role'] == 'ADMIN') {
              this.greetingMessage = 'Администратор ' + data['name'];

              this.http.get(environment.backend + 'admin/getNewcomers', {
                withCredentials: true
              }).subscribe(
                  data => {
                    this.report = data;
                  }
              );
            } else {
              this.router.navigate(['home']);
            }
          },
          err => {
            this.router.navigate(['login']);
          }
      );
  }

  ngOnInit() {}

  logout(event) {
      event.preventDefault();
      this.http.get(environment.backend + 'logout', {
        observe: "response" as "response",
        withCredentials: true
      }).subscribe();
      this.router.navigate(['login']);
  }


  setNewcomers(event) {
      event.preventDefault();
      const target = event.target;

      let report2: any = this.report;

      for (let i in report2) {
        for (let j in report2[i]) {
          let statuses = target.getElementsByClassName(i.toString() + j.toString());

          let status: String = "";
          for (let k of statuses) {
            if (k.checked) { status = k.value; }
          }

          report2[i][j]['status'] = status;
        }
      }

      this.http.post(environment.backend + 'admin/setNewcomers', report2,
          {
            withCredentials: true
          }
      ).subscribe(
          _ => {
            this.errorMessage = '';
            window.alert('Изменения успешно сохранены.');
            this.http.get(environment.backend + 'admin/getNewcomers', {
                  withCredentials: true
              }).subscribe(
                  data => {
                      this.report = data;
                  }
              );
          }, err => {
            this.errorMessage = err.error;
          }
      )
  }

}
