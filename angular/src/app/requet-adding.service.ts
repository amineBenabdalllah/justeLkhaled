import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RequetAddingService {

  constructor(private http:HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization':localStorage.getItem('token')
    })
  };


  public doLaunch(id, name, cle)
  {
    return this.http.get("http://localhost:8087/project/launch/"+id, this.httpOptions);
  }

  public doAdding(requet)
  {
    return this.http.post("http://localhost:8087/addProject", requet, this.httpOptions);
  }

  public getAllRequest()
  {
    return this.http.get("http://localhost:8087/projects/"+localStorage.getItem("username"), this.httpOptions);
  }

  public deleteRequest(id)
  {
    return this.http.delete("http://localhost:8087/deleteProject/"+id, this.httpOptions);
  }

  public getRequestByName(name)
  {
    return this.http.get("http://localhost:8087/projectSearch/"+name, this.httpOptions);
  }

  public getUser()
  {
    return this.http.get("http://localhost:8087/test", this.httpOptions);
  }

}
