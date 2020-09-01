import { Component, OnInit } from '@angular/core';
import {UserModule} from '../user/user.module';
import {Router} from '@angular/router';
import {AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
userdata:UserModule;

  constructor(private service: AuthenticationService, private router: Router) {
  }

  ngOnInit() {
    let response = this.service.getUser();
    response.subscribe((data:UserModule) => this.userdata = data);
  }

}