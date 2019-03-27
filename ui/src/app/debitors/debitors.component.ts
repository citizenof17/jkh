import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-debitors',
  templateUrl: './debitors.component.html',
  styleUrls: ['./debitors.component.css']
})
export class DebitorsComponent implements OnInit {

  greetingMessage: String;
  form: FormGroup;
  notificationPeriod: number;
  report: any;
  errMessage: String = '';
  successMessage = '';

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {
    this.http.get(environment.backend + 'userInfo', {
      withCredentials: true
    }).subscribe(
        data => {
          if (data['role'] == 'ADMIN') {
            this.greetingMessage = 'Администратор ' + data['name'] + '!';
            this.notificationPeriod = data['defaultPeriodBetweenSendings'];

            this.http.get(environment.backend + 'admin/getWhoDidNotSend', {
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

  ngOnInit() {
    this.form = new FormGroup({
          notPeriod: new FormControl(this.notificationPeriod, [Validators.required, Validators.min(1)])
        }
    );
  }

  changeNotPeriod(event){
    event.preventDefault();
    const target = event.target;
    let val = target.querySelector("#notPeriod").value;

    this.http.post(environment.backend + 'admin/setNotificationsPeriod',{}, {
      withCredentials: true,
      params : {
          notificationsPeriod: val
      }
    }).subscribe(
        _ => {
          this.successMessage = "Максимальный срок подачи показаний успешно изменен.";

          this.http.get(environment.backend + 'admin/getWhoDidNotSend', {
            withCredentials: true
          }).subscribe(
              data => {
                this.report = data;
              }
          );

        },
        err => {
          this.errMessage = err.error.message;
          this.successMessage = '';
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

}
