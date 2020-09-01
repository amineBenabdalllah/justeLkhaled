import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TranslateService {

  constructor(private http:HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization':localStorage.getItem('token')
    })
  };

  analyse(text:string)
  {
    return this.http.get("http://localhost:5000/sentiment/"+text, this.httpOptions)
  }

  translating(text:string)
  {
    return this.http.get("http://localhost:3000/traduction/"+text, this.httpOptions);
  }
}