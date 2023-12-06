import { Component, OnInit } from '@angular/core';
import { LoadingController, ModalController } from '@ionic/angular';
import { TouchSequence } from 'selenium-webdriver';
import { AppServiceService } from 'src/app/services/app-service.service';
import { AddItemsComponent } from '../components/add-items/add-items.component';
import { CoupanComponent } from '../components/coupan/coupan.component';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';
import { OrderDetail } from 'src/app/_class/order-detail';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-your-order',
  templateUrl: './your-order.page.html',
  styleUrls: ['./your-order.page.scss'],
})
export class YourOrderPage implements OnInit {
  orders = [];
  number = 1;
  total_amt = 0;
  totalItemPrice;
  returnDataFromModal;
  totalItemsCart: number = 0;
  cart: any;
  listOrderDetail: any[] = [];
  userId: any;
  constructor(
    private service: AppServiceService,
    public loadingController: LoadingController,
    private modalCtrl: ModalController,
    private cartService: CartService,
    private orderService: OrderService,
    private storageService: StorageService
  ) {}

  ngOnInit() {
    this.getUserId();
    this.presentLoading().then(() => {
      this.cartService.loadCart();
      this.totalItemsCart = this.cartService.getTotalItemsCount();
      this.total();
      this.loadingController.dismiss();
    });
  }

  getUserId() {
    this.userId = this.storageService.getUser().id;
  }

  async presentLoading() {
    const loading = await this.loadingController.create({
      message: 'Please Wait .. ',
    });
    await loading.present();
  }
  subtractQuantity(item: any) {
    if (item.quantity > 1) {
      let quantity = Number(item.quantity);
      this.cartService.updateCart(item, (quantity -= 1));
    }
  }

  plusQuantity(item: any) {
    if (item.quantity < item.trueQuantity) {
      let quantity = Number(item.quantity);
      this.cartService.updateCart(item, (quantity += 1));
    }
  }
  total() {
    this.cartService.items.map((res) => {
      this.total_amt = this.total_amt + res.price;
    });
  }

  itemPrice(qty, amt, title) {
    console.log(qty, amt, title);
    this.totalItemPrice = (qty * amt).toFixed(2);
    console.log(this.totalItemPrice);
  }

  removeItemPrice(qty, amt, title) {
    console.log(qty, amt, title);
    this.totalItemPrice = (qty * amt).toFixed(2);
    console.log(this.totalItemPrice);
  }

  deleteItem(item: any) {
    this.cartService.remove(item);
  }

  async editCoupan() {
    console.log('edited');
    const modal = await this.modalCtrl.create({
      component: CoupanComponent,
      showBackdrop: true,
      backdropDismiss: true,
      animated: true,
      swipeToClose: true,
      mode: 'ios',
      keyboardClose: true,
      componentProps: {
        name: 'Yamini',
        city: 'Nanded',
      },
      cssClass: 'my-modal',
    });

    modal.onDidDismiss().then((data: any) => {
      this.returnDataFromModal = data;
      console.log(this.returnDataFromModal);
    });
    return await modal.present();
  }

  async moreItems() {
    console.log('more items Added');
    const modal = await this.modalCtrl.create({
      component: AddItemsComponent,
      showBackdrop: true,
      backdropDismiss: true,
      animated: true,
      swipeToClose: true,
      mode: 'ios',
      keyboardClose: true,
      componentProps: {
        name: 'Yamini',
        city: 'Nanded',
      },
      cssClass: 'bookingmodal',
    });

    modal.onDidDismiss().then((data: any) => {
      this.returnDataFromModal = data;
      console.log(this.returnDataFromModal);
    });
    return await modal.present();
  }

  orderDetails() {
    this.cartService.items.forEach((res) => {
      let orderDetail: OrderDetail = new OrderDetail();
      orderDetail.dishId = Number(res.id);
      // orderDetail.name = res.name;
      orderDetail.price = res.price;
      orderDetail.quantity = res.quantity;
      orderDetail.subTotal = res.subTotal;
      this.listOrderDetail.push(orderDetail);
    });
    this.orderService
      .placeOrder(Number(this.userId), 'abccc', this.listOrderDetail)
      .subscribe({
        next: (res) => {
          console.log('Success');
          this.cartService.clearCart();
        },
      });
  }
}
