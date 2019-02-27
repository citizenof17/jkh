import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import { Router } from '@angular/router';
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  greetingMessage = '';

  constructor(private http: HttpClient, private router: Router, private cookieService: CookieService) {
    this.greetingMessage = '';
    this.http.get('http://localhost:8080/userInfo', {
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

  sendIndication(event){
    event.preventDefault();
    const target = event.target;

    this.http.post('http://localhost:8080/sendIndications',
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
            let temp = err.error.message;
            if (temp instanceof Array) {
                let msg: String = '';
                for (let i in temp) {
                    if (!temp[i]['isOk']) {
                        msg = msg + temp[i]['message'] + ' ' + (+i + 1) + '.\n';
                    }
                }
            window.alert(msg);
            } else {
                window.alert(temp);
            }
        }

    );
  }

}
