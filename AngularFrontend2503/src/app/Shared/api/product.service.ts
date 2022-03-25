import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import SearchProduct from '../models/SearchProduct';
import ProductDetails from '../models/ProductDetails';

import { Subject } from 'rxjs';

@Injectable()
export default class ProductService {
  public API = 'http://localhost:8080';
  
  public CATEGORY_API = `${this.API}/api/v1/get-all-categorys`;
  public PRODUCT_API = `${this.API}/user/get-products`;
  private cartList$ : Subject<any> = new Subject()
  public userDetails$ : Subject<any> = new Subject();

 
  constructor(private http: HttpClient) {}

 
  
//my code

geAllCategory() {
  return this.http.get(`${this.CATEGORY_API}`);
}

getProductByCatId(categoryId: String) {
  return this.http.get(`${this.PRODUCT_API}/${categoryId.toString()}`,{headers: {Authorization : this.getAuthToken()}});
}

addProductByCatId(categoryId:String, newProduct : any){
return this.http.post(`${this.API}/admin/add-product/${categoryId.toString()}/category`,newProduct,{headers: {Authorization : this.getAuthToken()}});
}

addProductImage(ProductId:String, prodImage){
return this.http.post(`${this.API}/admin/add-image/${ProductId.toString()}/prodImage`,prodImage,{headers: {Authorization : this.getAuthToken()}});
}

GetAllProductList() {
  // let result: Observable<ProductDetails>;
  return this.http.get<ProductDetails>(`${this.API}/api/v1/get-all-products`);
}

getImageByProductId(productId : String){
  return this.http.get(`${this.API}/api/v1/get-image/${productId.toString()}/prodImages`)
}


getAllImages(){
  return this.http.get(`${this.API}/api/v1/get-all-product-details`);
}
GetImageById(imageId :any){
  return this.http.get(`${this.API}/api/v1/images/${imageId}/`);
}
GetProductById(prodId : String){
  return this.http.get(`${this.API}/product/${prodId.toString()}`);
}

sendCartList(cartListData) {
  this.cartList$.next(cartListData);
}

receiveCartList() {
  return this.cartList$.asObservable();
}

receiveUserDetails() {
  return this.userDetails$.asObservable();
}

sendUserDetails(userDetails) {
  this.userDetails$.next(userDetails);
}

getAuthToken(){
  const userDetails = JSON.parse(sessionStorage.getItem('LoginDetails')!)
  if(userDetails){
    return "Basic" + " " + userDetails.authToken;
  }
  
}

}
