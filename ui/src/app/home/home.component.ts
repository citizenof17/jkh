import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import { Router } from '@angular/router';
import {CookieService} from "ngx-cookie-service";
import {environment} from "../../environments/environment";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [DatePipe]
})
export class HomeComponent implements OnInit {

  form: FormGroup;
  greetingMessage = '';
  errorMessage: [];
  successMessage = '';
  dateError: String;
  status = '';
  notification = 0;
  myDate = '';
  infoMessage: any = {
      msg: '',
      contacts: {}
  };
  report: any = {
      message: "Здесь будут отображаться отчеты о Ваших показаниях.",
  };


  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService,
              private datePipe: DatePipe) {
    this.greetingMessage = '';
    this.http.get(environment.backend + 'userInfo', {
      withCredentials: true
    }).subscribe(
        data => {
            if (data['role'] == 'USER') {
              this.status = data['status'];
              if (this.status == 'ACTIVE' || this.status == 'INACTIVE') {
                  this.greetingMessage = data['name'];
                  this.notification = data['daysOverDefaultPeriodOfCountersSending'];
              } else {
                  if (this.status == 'REMOVED') {
                      this.infoMessage['msg'] = 'Ваш аккаунт был удален. Для информации свяжитесь с администратором.';
                  }
                  if (this.status == 'UNVERIFIED') {
                      this.infoMessage['msg'] = 'Ваш аккаунт не был подтвержден. Для подтверждения свяжитесь с администратором.';
                  }

                  this.http.get(environment.backend + 'getAdminContacts', {
                      withCredentials: true
                  }).subscribe(
                      data => {
                          this.infoMessage['contacts'] = data;
                      }
                  );
              }
            } else {
              this.router.navigate(['admin']);
            }
        },
        err => {
            this.router.navigate(['login']);
        }
    );
  }

  ngOnInit() {
      this.form = new FormGroup({
          electr: new FormControl('', [Validators.required,
              Validators.min(0), Validators.pattern("^[0-9]*$")]),
          hot: new FormControl('', [Validators.required,
              Validators.min(0), Validators.pattern("^[0-9]*$")]),
          cold: new FormControl('', [Validators.required,
              Validators.min(0), Validators.pattern("^[0-9]*$")])
      });
  }

  logout(event) {
      event.preventDefault();
      this.http.get(environment.backend + 'logout', {
          observe: "response" as "response",
          withCredentials: true
      }).subscribe();
      this.router.navigate(['login']);
  }

  sendIndication(event){
      event.preventDefault();
      const target = event.target;

      this.http.post(environment.backend + 'sendIndications',
            [
                {
                    counter: {type: 'ELECTRICITY'},
                    value: target.querySelector('#electr').value
                },
                {
                    counter: {type: 'HOT_WATER'},
                    value: target.querySelector('#hot').value
                },
                {
                    counter: {type: 'COLD_WATER'},
                    value: target.querySelector('#cold').value
                }
            ], {
        withCredentials: true
      }).subscribe(
        _ => {
            this.errorMessage = [];
            this.myDate = this.datePipe.transform(new Date(), "dd.MM.yyyy", "en");
            this.successMessage = 'Данные успешно отправлены за ' + this.myDate + '.';
        } ,
        err => {
            this.successMessage = '';
            this.errorMessage = err.error.messages;
        }
      );
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
                rightDate: target.querySelector("#right-b").value
            };
        } else {
            q = {
                standardPeriod: repType
            }
        }

        this.http.post(environment.backend + 'report', q, {
            withCredentials: true
        }).subscribe(
            data => {
                this.report = data;
                this.dateError = '';
            },
            err => {
                this.dateError = err.error.message;
            }
        );
  }

}
