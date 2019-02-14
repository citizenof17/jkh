import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface MyData {
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  getUserDetails(username, password2) {
    return this.http.post<MyData>('http://localhost:8080/login', {
            login: username,
            password: password2
      });
  }
}
