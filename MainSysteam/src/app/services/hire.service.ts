import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HireService {
  endPoint = 'http://localhost:9999/auth/';

  constructor(private http: HttpClient) {
  }

  addToken(email: string, duration: number): Observable<any> {
    return this.http.post(this.endPoint + 'createToken', {
      headers: {
        'Allow-Cross-Origin-Origin0': '*'
      },
      responseType: 'text',
      email: email,
      duration: duration
    });
  }
}
