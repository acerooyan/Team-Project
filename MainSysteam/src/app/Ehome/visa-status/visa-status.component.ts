import { Component, OnInit } from '@angular/core';
import { HttpClient ,  HttpRequest, HttpEvent} from '@angular/common/http';
import { Observable, Observer } from 'rxjs';

export class StatusInfo{
  status?: String;
  currentStep?: String;
  nextStep?: String;
  comment?: String;
  documents?: string[];
  i983?: String;
  i983Sample?: String;
}

  //   "currentStep": "onBoarding",
  //   "nextStep": "OPT Receipt",
  //   "comment": null,
  //   "status": "complete",
  //   "i983": "i983.pdf",
  //   "i983Sample": "i983Sample.pdf",
  //   "documents": [
  //   "xxxxxxxx"
  // ]
  // }
@Component({
  selector: 'app-visa-status',
  templateUrl: './visa-status.component.html',
  styleUrls: ['./visa-status.component.css']
})

export class VisaStatusComponent implements OnInit {
  public statusInfo: StatusInfo = {};
  localData:any;
  selectedFiles?: FileList;
  private statusInfoUrl = "api/jwt/em/visaStatus";
  constructor(private httpClient: HttpClient) { }
  ngOnInit(): void {
    this.getStatusInfo().subscribe(
      (data:any) =>{
        console.log(data);
        this.localData = data;
          //   "currentStep": "onBoarding",
          //   "nextStep": "OPT Receipt",
          //   "comment": null,
          //   "status": "complete",
          //   "i983": "i983.pdf",
          //   "i983Sample": "i983Sample.pdf",
          //   "documents": [
          //   "xxxxxxxx"
          // ]
          // }
        this.statusInfo = {
          currentStep:data.currentStep,
          nextStep:data.nextStep,
          comment: data.comment,
          documents:data.documents,
          status: data.status,
          i983: data.i983,
          i983Sample: data.i983Sample,

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


  callSubmitFile() {
    this.submitFile().subscribe(
      (data:any) =>{
        console.log(data);
      },
      (error: any)=>{
        if (error.status === 401) {

        }
        else{
          console.log(error);
        }

      }
    )

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
    console.log(this.localData);
    formData.append("model",JSON.stringify(this.localData));

    const req = new HttpRequest('POST', this.statusInfoUrl, formData, {
      reportProgress: true,
      responseType: 'json'
    });
  console.log('abc');
    return this.httpClient.request(req);

  }
  haveI983() {
    if(this.statusInfo.i983!=null) {
      return true;
    } else {
      return false;
    }
  }

}
