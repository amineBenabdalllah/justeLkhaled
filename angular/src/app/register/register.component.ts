import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { Router } from '@angular/router';
import { UserModule } from '../user/user.module';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  constructor(private autheservice:AuthenticationService,
              private router:Router) { }

  user:UserModule= new UserModule("", "", "", "", "",new Date());
  password2:string;
  date:Date;
  ngOnInit(): void {
  }
  onRegister(){
    console.log(this.user);
    console.log(this.password2);

    if(this.user.password!=""&&this.password2!=""&&this.user.username!=""){

      if(this.user.password!=this.password2){
        alert("Use the same password");
      }else{
        console.log(this.user.date);
        console.log(this.user);
        this.autheservice.register(this.user)
          .subscribe(resp=>{

            if(resp==400){
              alert("user exist !!");
            }
            if(resp==200){
              alert("WELCOME");
              this.router.navigateByUrl('/login');
            }

          },err=>{
          })

      }

    }

  }

}
