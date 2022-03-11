
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
  customer: M_Customer = new  M_Customer();
  // customer = {CustomerId: 0, CustomerName: '', MobileNo: '', EmailId: '', Password: '', ConfirmPassword: ''};
  constructor(private route: ActivatedRoute,
              private router: Router,
              private customerService: CustomerService) { }

  ngOnInit() {
    this.blockUI.start();
    this.sub = this.route.params.subscribe(params => {
      const id = params.id;
      if (id) {
        this.customerService.getCustomer().subscribe((cust: any) => {
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
    this.blockUI.stop();
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/login']);
  }

  save(form: any) {
    const customerDetailsbuild = this.buildCustomerDetails();
    this.sub = this.customerService.save(customerDetailsbuild).subscribe(
      result => {
        alert("You have registered successfully")
        this.gotoList();
      },
      error => console.error(error)
    );
  }
  buildCustomerDetails(){
    const customerDetails = {
                custName : this.customer.custName,
                custEmail : this.customer.custEmail,
                custPhn : this.customer.custPhNo,
                custAddress : this.customer.custAddress,
                password : this.customer.password

    }
    return customerDetails

  }
}

