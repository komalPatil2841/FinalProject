import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import CategoryService from '../Shared/api/category.service';
import M_Category from '../Shared/models/Category';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import Swal from 'sweetalert2';
import { FormGroup, Validators, FormBuilder, FormControl } from '@angular/forms';

@Component({
  selector: 'app-category-add',
  templateUrl: './category-add.component.html',
  styleUrls: ['./category-add.component.css'],
  providers: [CategoryService]
})
export class CategoryAddComponent implements OnInit, OnDestroy {
  @BlockUI()
    
  blockUI: NgBlockUI;
  sub: Subscription;
  addCategoryForm : FormGroup;
  category: M_Category = new  M_Category();
  // category = {CategoryId: 0, CategoryName: '', MobileNo: '', EmailId: '', Password: '', ConfirmPassword: ''};
  constructor(private route: ActivatedRoute,
              private router: Router,
              private categoryService: CategoryService,
              private formBuilder : FormBuilder) { }

              ngOnInit() {

                this.sub = this.route.params.subscribe(params => {
                  const id = params.id;
                  if (id) {
                    this. categoryService.getCategory().subscribe((cust: any) => {
                      if (cust) {
                        this.category = cust[0];
                     
                      } else {
                        console.log(
                          `Category with id '${id}' not found, returning to list`
                        );
                      }
                    });
                  }
                });
                this.addCategoryForm = this.formBuilder.group({
                  CategoryName: new FormControl('', Validators.required),
                  
            
                });
            
              }
            
              ngOnDestroy(): void {
                this.sub.unsubscribe();
              }
            
              gotoList() {
                this.router.navigate(['/product-add']);
              }
            
              save(form: any) {
                this.blockUI.start();
                const categoryDetailsbuild = this.buildCategoryDetails();
                this.sub = this. categoryService.save(categoryDetailsbuild).subscribe(
                  result => {
                    setTimeout(() => {
                      this.blockUI.stop();
                      Swal.fire({
                        title: 'success',
                        text: 'Category Added Successfully!',
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
              buildCategoryDetails(){
                const categoryDetails = {
                            catName : this.addCategoryForm.value.CategoryName,
                           
            
                }
                return categoryDetails
            
              }
            }