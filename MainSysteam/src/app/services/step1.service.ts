import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {RegUser} from "../entity/reg-user";

@Injectable({
  providedIn: 'root'
})
export class Step1Service {
  endPoint = 'auth/';

  constructor(private http: HttpClient) {
  }

  validateToken(token: string): Observable<any> {
    return this.http.get(this.endPoint + 'new?registrationToken=' + token, {
      headers: {
        'Allow-Cross-Origin-Origin0': '*'
      },
      responseType: 'text',
    });
  }

  addUser(user: RegUser): Observable<any> {
    return this.http.post(this.endPoint + 'registration', {
      headers: {
        'Allow-Cross-Origin-Origin0': '*'
      },
      responseType: 'text',
      userName: user.userName,
      password: user.password,
      email: user.email
    });
  }
}
