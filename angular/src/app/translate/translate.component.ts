import { Component, OnInit } from '@angular/core';
import { TranslateService } from '../translate.service';

@Component({
  selector: 'app-translate',
  templateUrl: './translate.component.html',
  styleUrls: ['./translate.component.scss']
})
export class TranslateComponent implements OnInit {

  constructor(private service:TranslateService) { }
  text:string;
  response:any;
  ngOnInit() {
  }

  onTranslate()
  { this.response="it's working fine";
    let response = this.service.translating(this.text);
    response.subscribe((data)=>this.response=data);
    this.response=response;
    console.log(response);
    console.log(this.text);
  }
}
