import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RequestComponent } from './request.component';



@NgModule({
  declarations: [RequestComponent],
  imports: [
    CommonModule
  ]
})
export class RequestModule {
  testId:string;
  id:number;
  name:string;
  type:string;
  motCle:string;
  date:Date;
  language:string;
  username:string;

  constructor(testId:string, id: number, name: string, type: string, motCle: string, date: Date, language: string, username: string) {
    this.testId=testId;
    this.id = id;
    this.name = name;
    this.type = type;
    this.motCle = motCle;
    this.date = date;
    this.language = language;
    this.username = username;
  }
}
