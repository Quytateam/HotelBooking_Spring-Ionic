import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { AppServiceService } from 'src/app/services/app-service.service';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';
import { StorageService } from 'src/app/services/storage.service';
import { forkJoin } from 'rxjs';
@Component({
  selector: 'app-payment',
  templateUrl: './payment.page.html',
  styleUrls: ['./payment.page.scss'],
})
export class PaymentPage implements OnInit {
  totalItemsCart: number = 0;
  userId: any;
  payment: any;
  constructor(
    private service: AppServiceService,
    public loadingController: LoadingController,
    private router: Router,
    private cartService: CartService,
    private storageService: StorageService,
    private orderService: OrderService
  ) {}

  ngOnInit() {
    this.getUserId();
    this.totalItemsCart = this.cartService.getTotalItemsCount();
    this.presentLoading().then(() => {
      // this.service.getAllPayments().subscribe((res) => {
      //   this.payment = res.document.records;
      //   console.log(this.payment);

      // });
      this.getOrders();
      this.loadingController.dismiss();
    });
  }

  getUserId() {
    this.userId = this.storageService.getUser().id;
  }

  getOrders() {
    this.orderService.getOrdersByUserDESC(Number(this.userId)).subscribe({
      next: (res) => {
        this.payment = res;
        // Sử dụng forkJoin để gộp nhiều yêu cầu thành một
        forkJoin(
          this.payment.map((obj) => this.orderService.getDetails(obj.id))
        ).subscribe({
          next: (transactions) => {
            // Gán chi tiết cho từng dịch vụ tương ứng
            this.payment.forEach((order, index) => {
              order.transactions = transactions[index];
            });
            console.log(this.payment);
          },
          error: (error) => {
            console.error(error);
          },
        });
      },
      error: (error) => {
        console.error(error);
      },
    });
  }

  async presentLoading() {
    const loading = await this.loadingController.create({
      message: 'Please Wait .. ',
    });
    await loading.present();
  }
}
