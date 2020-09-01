import {Component, OnDestroy, OnInit} from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {


  constructor(private autheservice:AuthenticationService,
    private router:Router) { }

  ngOnInit(): void {
  }
  onLogin(data){
    console.log(data);
    this.autheservice.login(data)
    .subscribe(resp=>{
        let jwt=resp.headers.get('Authorization');
        //console.log(jwt);
        this.autheservice.saveToken(jwt);
        window.location.href="/";

    },err=>{
      alert("user does not exist !!");
    })

  }}

