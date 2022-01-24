import { Component, OnInit , Input} from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {


  @Input('exData1') exData1: any;
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  hide(){
    this.router.navigate(['nav/hire']);
  }

}
