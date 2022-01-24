import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HrEmployeeProfileService {
  private endPoint = "api/jwt"

  constructor(private httpClient: HttpClient) {
  }

  getEmployeeProfiles(curPage: number, totalNum: number, maxResult: number, email: string): Observable<any> {
    
    return this.httpClient.post(this.endPoint + '/hr/employee/profiles', {
      headers: {
        'Allow-Cross-Origin-Origin0': '*'
      },
      responseType: 'text',
      curPage: curPage,
      totalNum: totalNum,
      email: email,
      maxResult: maxResult
    });
  }
}