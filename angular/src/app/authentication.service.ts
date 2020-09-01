import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { JwtHelperService } from "@auth0/angular-jwt";
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  host:string="http://localhost:8084";
  jwt:string;
  username:string;
  roles:string;

  saveToken(jwt: string) {
    localStorage.setItem('token',jwt);
    this.jwt=jwt;
    this.parseJWT(this.jwt);

  }
  parseJWT(jwt: string){
    let jwtHelper=new JwtHelperService();
    let objJWT=jwtHelper.decodeToken(this.jwt);
    this.username=objJWT.sub;
    localStorage.setItem("username",this.username);
    this.roles=objJWT.role[0]['authority'];
    console.log(this.username);
    console.log(localStorage.getItem("username"));
  }

  login(data){
    return this.http.post(this.host+"/login",data,{observe:'response'});
  }
  register(data){
    let newdata={"username":data["username"],"password":data["password"],"role":"USER","email":data["email"], "name":data["name"], "date":data["date"]};
    console.log(newdata)
    return this.http.post<any>(this.host+"/register",newdata);
  }

  public doUpdate(user)
  {
    console.log("yapdaity");
    console.log(localStorage.getItem("username"))
    let data={"email":user["email"], "name":user["name"], "role":"USER","date":user["date"]};
    return this.http.post(this.host+"/userinfo",data,this.httpOptions);
  }

  public getUser()
  {
    
    return this.http.get(this.host+"/user",this.httpOptions);
  }
public changepassword(data){
  return this.http.post(this.host+"/changepassword",data,this.httpOptions);
}
   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization':localStorage.getItem('token')
    })
  };

  public logout(){
    localStorage.setItem('token',null);
    window.location.reload();
  }

public userRequest(){
  return this.http.get(this.host+"/jwt",this.httpOptions);

}


}
