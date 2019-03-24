import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";
import {environment} from "../../environments/environment";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-inhabitants',
  templateUrl: './inhabitants.component.html',
  styleUrls: ['./inhabitants.component.css']
})
export class InhabitantsComponent implements OnInit {

  greetingMessage : String;
  form: FormGroup;
  errorMessage: String = '';
  report: any;
  type = 0;

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {
    this.http.get(environment.backend + 'userInfo', {
      withCredentials: true
    }).subscribe(
        data => {
          if (data['role'] == 'ADMIN') {
            this.greetingMessage = 'Администратор ' + data['name'];
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
          flat: new FormControl('', [Validators.required, Validators.min(1)])
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

  getInhabitants(event) {
      event.preventDefault();
      const target = event.target;

      let val = target.querySelector("#flat").value;

      this.http.get(environment.backend + 'admin/getFlatInhabitants', {
          withCredentials: true,
          params: {
            flatNumber: val
          }
        }).subscribe(
          data => {
            this.type = 1;
            this.report = data;
            this.errorMessage = '';
          }, err => {
            this.errorMessage = err.error['message'];
            this.type = 0;
          }
      );
  }

  setInhabitants(event) {
      event.preventDefault();
      const target = event.target;

      let report2: any = this.report;

      for (let j in report2) {
        let statuses = target.getElementsByClassName(j.toString());

        let status: String = "";
        for (let k of statuses) {
          if (k.checked) { status = k.value; }
        }

        report2[j]['status'] = status;
      }

      this.http.post(environment.backend + 'admin/setFlatInhabitants', report2,
          {
            withCredentials: true
          }
      ).subscribe(
          _ => {
            this.errorMessage = '';
            window.alert('Изменения успешно сохранены.');

            let val = this.form.get('flat').value;

            this.http.get(environment.backend + 'admin/getFlatInhabitants', {
                  withCredentials: true,
                  params: {
                      flatNumber: val
                  }
              }).subscribe(
                  data => {
                      this.type = 1;
                      this.report = data;
                      this.errorMessage = '';
                  }, err => {
                      this.errorMessage = err.error['message'];
                      this.type = 0;
                  }
              );
          }, err => {
            this.errorMessage = err.error;
          }
      );
  }

}
