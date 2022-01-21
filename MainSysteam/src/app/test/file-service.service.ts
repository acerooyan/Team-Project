import { Injectable } from '@angular/core';
import { HttpClient,  HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { errors } from 'src/app/entity/errors';

@Injectable({
  providedIn: 'root'
})
export class FileServiceService {


  private baseUrl = 'api';

  errorObject: errors = {code:"200", message:"caonima"}
  constructor(private http: HttpClient) { }



  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);
    
    formData.append('model', JSON.stringify(this.errorObject));
    formData.append('username', "username");
    formData.append('password', "woaini");
    

    const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/files`);
  }
}
