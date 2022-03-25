
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import CustomerService from '../Shared/api/customer.service';
import M_Customer from '../Shared/models/Customer';
import { BlockUI,NgBlockUI } from 'ng-block-ui';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css'],
  providers: [CustomerService]
})
export class EditCustomerComponent implements OnInit {

  @BlockUI() blockUI : NgBlockUI;
  sub: Subscription;
  customer: any = {};
  userDetails: any = {};
  passworddata: any;
  password: any;
  errorMsg: boolean =false;
  // customer = {CustomerId: 0, CustomerName: '', MobileNo: '', EmailId: '', Password: '', ConfirmPassword: ''};
  constructor(private route: ActivatedRoute,
              private router: Router,
              private customerService: CustomerService) { }

  ngOnInit() {
    this.blockUI.start();
    this.sub = this.route.params.subscribe(params => {
      const id = params.id;
      if (id) {
        this.customerService.getCustomer(id).subscribe((cust: any) => {
          if (cust) {
            this.customer = cust[0];
            this.customer.ConfirmPassword = cust[0].Password;
          } else {
            console.log(
              `Product with id '${id}' not found, returning to list`
            );
          }
        });
      }
    });
     
    this.userDetails = JSON.parse(sessionStorage.getItem('UserData'));
    this.passworddata = JSON.parse(sessionStorage.getItem('LoginDetails'));
    this.password = this.passworddata.password
    console.log(this.userDetails);
  if(this.userDetails){
 this.customer.userName = this.userDetails.userName;
 this.customer.userEmail = this.userDetails.userEmail;
 this.customer.userPhn = this.userDetails.userPhn;
 this.customer.userAddress = this.userDetails.userAddress;
 this.customer.password = this.password ;
  }
    this.blockUI.stop();
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/login']);
  }

  save(form: any) {
    if(this.customer.password !== this.customer.ConfirmPassword){
      this.errorMsg = true;
    }else{
    this.errorMsg = false;
    const customerDetailsbuild = this.buildCustomerDetails();
    this.sub = this.customerService.update(customerDetailsbuild,this.userDetails.userId).subscribe(
      result => {
        alert("Your details updated successfully")
        this.gotoList();
      },
      error => console.error(error)
    );
    }
  }
  buildCustomerDetails(){
    const customerDetails = {
                userName : this.customer.userName,
                userEmail : this.customer.userEmail,
                userPhn : this.customer.userPhn,
                userAddress : this.customer.userAddress,
                password : this.customer.password

    }
    return customerDetails

  }
}

