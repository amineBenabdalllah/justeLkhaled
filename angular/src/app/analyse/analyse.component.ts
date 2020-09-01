import { Component, OnInit } from '@angular/core';
import { TranslateService } from '../translate.service';

@Component({
  selector: 'app-analyse',
  templateUrl: './analyse.component.html',
  styleUrls: ['./analyse.component.scss']
})
export class AnalyseComponent implements OnInit {

  constructor(private service:TranslateService) { }
  text:string;
  response:any;
  ngOnInit() {
  }

  onAnalyse()
  { this.response="it's working fine";
    let response = this.service.analyse(this.text);
    response.subscribe((data)=>this.response=data);
    this.response=response;
    console.log(response);
    console.log(this.text);
  }

}
