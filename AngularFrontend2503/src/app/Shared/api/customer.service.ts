import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import M_Customer from '../models/Customer';
import { Observable, Subject } from 'rxjs';
import SearchAddress from '../models/SearchAddress';


@Injectable()
export default class CustomerService {
    public API = 'http://localhost:8080/api/v1';
    // public API = 'http://10.22.28.5:1993/api';
    public CUSTOMER_API = `${this.API}/customerslist`;
    public CUSTOMER_SAVEAPI = `${this.API}/registerNewUser`;
    public userDetails$ : Subject<any> = new Subject();
   
    public CUSTOMER_LOGINAPI = `${this.API}/login`;

    constructor(private http: HttpClient) {}

    

    getCustomer(id:string) {
        return this.http.get(`${this.API}/user/get-user/${id}`);
    }

    update(Request :any, userId:string){
        return this.http.put(`${this.API}/user/update-user/${userId}`,Request)
    }
    save(Request : any){
        return this.http.post<object>(`${this.CUSTOMER_SAVEAPI}`, Request);
    }

    checkCustomerLogin(customer :any){
        return this.http.post(`${this.CUSTOMER_LOGINAPI}`, customer);
    }

    receiveUserDetails() {
        return this.userDetails$.asObservable();
    }
    
    sendUserDetails(userDetails) {
        this.userDetails$.next(userDetails);
    }
   
}
