import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import CustomerService from '../Shared/api/customer.service';
import M_Customer from '../Shared/models/Customer';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import Swal from 'sweetalert2';
import { FormGroup, Validators, FormBuilder, FormControl } from '@angular/forms';

@Component({
  selector: 'app-customer-add',
  templateUrl: './customer-add.component.html',
  styleUrls: ['./customer-add.component.css'],
  providers: [CustomerService]
})
export class CustomerAddComponent implements OnInit, OnDestroy {
  @BlockUI()
    
  blockUI: NgBlockUI;
  sub: Subscription;
  addCustomerForm : FormGroup;
  customer: M_Customer = new  M_Customer();
  errorMsg: boolean = false;
  // customer = {CustomerId: 0, CustomerName: '', MobileNo: '', EmailId: '', Password: '', ConfirmPassword: ''};
  constructor(private route: ActivatedRoute,
              private router: Router,
              private customerService: CustomerService,
              private formBuilder : FormBuilder) { }

  ngOnInit() {

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
    this.addCustomerForm = this.formBuilder.group({
      userName: new FormControl('', Validators.required),
      userPhn: new FormControl('', Validators.required),
      userEmail : new FormControl('',Validators.required),
      userAddress:new FormControl(),
      password: new FormControl('', Validators.required),
      confirmPassword : new FormControl('',Validators.required )

    });

  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/login']);
  }

  save(form: any) {
    if(this.addCustomerForm.value.password !== this.addCustomerForm.value.confirmPassword){
      this.errorMsg = true;
    }else{
    this.blockUI.start();
    const customerDetailsbuild = this.buildCustomerDetails();
    this.sub = this.customerService.save(customerDetailsbuild).subscribe(
      result => {
        setTimeout(() => {
          this.blockUI.stop();
          Swal.fire({
            title: 'Thank you',
            text: 'Registration Done Successfully!',
            icon: 'success',
            showCancelButton: false,
            allowOutsideClick: false
          }).then((result) => {
            this.gotoList();
          });
        
        }, 5000);
       
       
      },
      error => console.error(error)
    );
  }
}
  buildCustomerDetails(){
    const customerDetails = {
                userName : this.addCustomerForm.value.userName,
                userEmail : this.addCustomerForm.value.userEmail,
                userPhn : this.addCustomerForm.value.userPhn,
                userAddress : this.addCustomerForm.value.userAddress,
                password : this.addCustomerForm.value.password

    }
    return customerDetails

  }
}
