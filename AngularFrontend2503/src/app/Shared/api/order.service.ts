import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Order from '../models/Order';
import OrderDetail from '../models/OrderDetail';
import { Observable } from 'rxjs';
import SearchOrder from '../models/SearchOrder';
import OrderList from '../models/OrderList';

@Injectable()
export default class OrderService {
    public API = 'http://localhost:8080';
  

    public ORDER_SAVEAPI = `${this.API}/user/save-orders`;

    public ORDER_DETAPI = `${this.API}/user/save-orderDetails`;
  
    constructor(private http: HttpClient) {}

    MakeNewOrder(order: Order , custId : String) {
        let result: Observable<Order>;
        if (order) {
            result = this.http.post<Order>(`${this.ORDER_SAVEAPI}/${custId}/customer`, order,{headers: {Authorization : this.getAuthToken()}});
        }
        return result;
    }

    InsertOrderDetails(order: OrderDetail , orderId : string) {
        let result: Observable<OrderDetail>;
        if (order) {
            result = this.http.post<OrderDetail>(`${this.ORDER_DETAPI}/${orderId}/orderdetails`, order,{headers: {Authorization : this.getAuthToken()}});
        }
        return result;
    }

    GetOrderList(custId : String) {
        return this.http.get(`${this.API}/user/get-orders/${custId}`,{headers: {Authorization : this.getAuthToken()}});
    }

    GetOrderDetails(orderId : String){
        return this.http.get(`${this.API}/user/get-orders/${orderId}/orderdetails`,{headers: {Authorization : this.getAuthToken()}});
    }

    getAuthToken(){
        const userDetails = JSON.parse(sessionStorage.getItem('LoginDetails')!)
        if(userDetails){
          return "Basic" + " " + userDetails.authToken;
        }
        
      }
}
