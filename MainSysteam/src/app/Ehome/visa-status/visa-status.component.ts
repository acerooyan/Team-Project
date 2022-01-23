import { Component, OnInit } from '@angular/core';
import { HttpClient ,  HttpRequest, HttpEvent} from '@angular/common/http';
import { Observable, Observer } from 'rxjs';

export class StatusInfo{
  currentStep?: String;
  nextStep?: String;
  comment?: String;
  documents?: string[];
}
@Component({
  selector: 'app-visa-status',
  templateUrl: './visa-status.component.html',
  styleUrls: ['./visa-status.component.css']
})

export class VisaStatusComponent implements OnInit {
  public statusInfo: StatusInfo = {};

  selectedFiles?: FileList;
  private statusInfoUrl = "";
  constructor(private httpClient: HttpClient) { }
  ngOnInit(): void {
    this.getStatusInfo().subscribe(
      (data:any) =>{
        console.log(data);
        this.statusInfo = {
          currentStep:data.statusInfoDomain.currentStep,
          nextStep:data.statusInfoDomain.nextStep,
          comment: data.statusInfoDomain.comment,
          documents:data.statusInfoDomain.documents
        };
      }
    )
  }



  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  getStatusInfo():Observable<any>{
    return this.httpClient.get(this.statusInfoUrl,
    );
  }
  reset() {
    this.ngOnInit();
  }



  submitFile():Observable<HttpEvent<any>> {

   const formData: FormData = new FormData();

    if (this.selectedFiles) {

      var file: File | null = this.selectedFiles.item(0);

      if (file) {

        console.log(file);
        formData.append('file', file);


      }
    }
    const req = new HttpRequest('POST', this.statusInfoUrl, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.httpClient.request(req);

  }


}
