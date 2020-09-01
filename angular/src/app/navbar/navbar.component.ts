import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  constructor(private autheservice:AuthenticationService,
              private router:Router) { }

  ngOnInit() {
  }

  onlogout(){
    this.autheservice.logout();
    this.router.navigateByUrl('/');

  }
}
