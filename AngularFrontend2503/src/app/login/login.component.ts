import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AppComponent } from '../app.component';
import { Subscription } from 'rxjs';
import CustomerService from '../Shared/api/customer.service';
import M_Customer from '../Shared/models/Customer';
import { BlockUI, NgBlockUI} from 'ng-block-ui';

import { FormGroup, Validators, FormBuilder, FormControl } from '@angular/forms';
import ProductService from '../Shared/api/product.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [CustomerService]
})
export class LoginComponent implements OnInit, OnDestroy {
  @BlockUI()
    
  blockUI: NgBlockUI;
  sub: Subscription;
  customer: any = {};
  username: string;
  password: string;

  loginForm: FormGroup;
 
  constructor(private router: Router,
              private route: ActivatedRoute, 
              private productService: ProductService,
              private customerService: CustomerService,
              private formBuilder : FormBuilder) { }





  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {});

    this.loginForm = this.formBuilder.group({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
     
    });
  }

  login(): void {

    if (this.loginForm.value.username !== null && this.loginForm.value.password !== undefined) {
      this.customer.username = this.loginForm.value.username;
      this.customer.password = this.loginForm.value.password;
      this.customerService.checkCustomerLogin(this.customer).subscribe(result => {
        console.log(result)
          this.gotoList();
         this.customer.authToken = window.btoa(this.customer.username + ":" + this.customer.password )
          sessionStorage.setItem('LoginDetails', JSON.stringify(this.customer));
          sessionStorage.setItem('UserData', JSON.stringify(result))
          this.productService.sendUserDetails(this.customer);
         
        },
        error => console.error(error)
      );
    } else {
      alert('Invalid credentials');
    }
  }
  gotoList() {
    this.router.navigate(['product-shop']);
  }
  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
