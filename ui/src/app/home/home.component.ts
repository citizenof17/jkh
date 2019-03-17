import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import { Router } from '@angular/router';
import {CookieService} from "ngx-cookie-service";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  greetingMessage = '';
  report: any = {
      message: "Здесь будут отображаться отчеты о Ваших показаниях.",
  };

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {
    this.greetingMessage = '';
    this.http.get(environment.backend + 'userInfo', {
      withCredentials: true
    }).subscribe(
        data => {
            if (data['role'] == 'USER') {
              this.greetingMessage = 'Добро пожаловать, ' + data['name'] + '!';
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
            window.alert('Данные успешно отправлены.');
        } ,
        err => {
            let temp = err.error.messages;
            if (temp.length > 1) {
                let msg: String = '';
                for (let i in temp) {
                    if (temp[i] !== 'ok') {
                        msg = msg + temp[i] + ' ' + (+i + 1) + '.\n';
                    }
                }
                window.alert(msg);
            } else {
                window.alert(temp[0]);
            }
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
            },
            err => {
                window.alert(err.error.message);
            }
        );
  }

}
