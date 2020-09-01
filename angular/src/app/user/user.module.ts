import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class UserModule {
  username:string;
  email:string;
  name:string;
  password:string;
  role:string;
  date:Date;
  constructor(username:string, email:string, name:string, password:string, role:string, date:Date)
  {
    this.username=username;
    this.email=email;
    this.name=name;
    this.password=password;
    this.role=role;
    this.date=date;

  }
}
