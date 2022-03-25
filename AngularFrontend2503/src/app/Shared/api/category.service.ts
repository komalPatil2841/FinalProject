import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import M_Category from '../models/Category';
import { Observable, Subject } from 'rxjs';



@Injectable()
export default class CategoryService {
    public API = 'http://localhost:8080/api/v1';
    // public API = 'http://10.22.28.5:1993/api';
    public CATEGORY_API = `${this.API}/category`;
    public CATEGORY_SAVEAPI = `${this.API}/category`;
  

    constructor(private http: HttpClient) {}

    


   

    getCategory() {
        return this.http.get(`${this.CATEGORY_API}`);
    }

    save(Request : any){
        return this.http.post<object>(`${this.CATEGORY_SAVEAPI}`, Request);
    }
  

   
   
}