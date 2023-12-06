import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoadingController, PopoverController } from '@ionic/angular';
import { AppServiceService } from 'src/app/services/app-service.service';
import { OrderPage } from '../popup/order/order.page';
import { CartService } from 'src/app/services/cart.service';
import { StorageService } from 'src/app/services/storage.service';
import { OrderService } from 'src/app/services/order.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.page.html',
  styleUrls: ['./my-orders.page.scss'],
})
export class MyOrdersPage implements OnInit {
  segId = 'upcoming';
  orders = [];
  totalItemsCart: number = 0;
  userId: any;
  ordersDESC: any;
  ordersASC: any;
  currentDate: Date;
  constructor(
    private service: AppServiceService,
    public loadingController: LoadingController,
    private router: Router,
    public popoverController: PopoverController,
    private cartService: CartService,
    private storageService: StorageService,
    private orderService: OrderService
  ) {}

  ngOnInit() {
    this.getUserId();
    this.today();
    this.totalItemsCart = this.cartService.getTotalItemsCount();
    this.presentLoading().then(() => {
      this.cartService.loadCart();
      this.getOrdersDESC();
      this.getOrdersASC();
      this.loadingController.dismiss();
    });
  }

  today() {
    this.currentDate = new Date();
  }

  getUserId() {
    this.userId = this.storageService.getUser().id;
  }

  getOrdersDESC() {
    this.orderService.getOrdersByUserDESC(Number(this.userId)).subscribe({
      next: (res) => {
        this.ordersDESC = res;
        // Sử dụng forkJoin để gộp nhiều yêu cầu thành một
        forkJoin(
          this.ordersDESC.map((obj) => this.orderService.getDetails(obj.id))
        ).subscribe({
          next: (details) => {
            // Gán chi tiết cho từng dịch vụ tương ứng
            this.ordersDESC.forEach((order, index) => {
              order.details = details[index];
            });
            console.log(this.ordersDESC);
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

  getOrdersASC() {
    this.orderService.getOrdersByUserASC(Number(this.userId)).subscribe({
      next: (res) => {
        this.ordersASC = res;
        // Sử dụng forkJoin để gộp nhiều yêu cầu thành một
        forkJoin(
          this.ordersASC.map((obj) => this.orderService.getDetails(obj.id))
        ).subscribe({
          next: (details) => {
            // Gán chi tiết cho từng dịch vụ tương ứng
            this.ordersASC.forEach((order, index) => {
              order.details = details[index];
            });
            console.log(this.ordersASC);
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

  reOrder(item: any) {
    this.cartService.addToCart(item, 1);
  }

  async presentLoading() {
    const loading = await this.loadingController.create({
      message: 'Please Wait .. ',
    });
    await loading.present();
  }

  segmentChanged(ev) {
    this.segId = ev.detail.value;
  }
  async presentPopover(ev: any) {
    const popover = await this.popoverController.create({
      component: OrderPage,
      event: ev,
      mode: 'ios',
      translucent: true,
    });
    await popover.present();
    const { role } = await popover.onDidDismiss();
  }
}
