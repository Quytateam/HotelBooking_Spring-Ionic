import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  ActionSheetController,
  LoadingController,
  PopoverController,
} from '@ionic/angular';
import { AppServiceService } from 'src/app/services/app-service.service';
import { OrderPage } from '../popup/order/order.page';
import { ChefService } from 'src/app/services/chef.service';
import { ServiceService } from 'src/app/services/services.service';
import { forkJoin } from 'rxjs';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-chef-details',
  templateUrl: './chef-details.page.html',
  styleUrls: ['./chef-details.page.scss'],
})
export class ChefDetailsPage implements OnInit {
  chef: any = {};
  order = [];
  segId = 'services';
  dish = [];
  orders = [];
  heartIcon: string = 'heart-outline';
  chefId: any;
  currentDate: Date;
  serviceChef: any;
  constructor(
    private route: ActivatedRoute,
    public loadingController: LoadingController,
    private router: Router,
    private service: AppServiceService,
    public popoverController: PopoverController,
    public actionSheetController: ActionSheetController,
    private chefService: ChefService,
    private services: ServiceService,
    private cartService: CartService
  ) {}

  ngOnInit() {
    this.getChefId();
    this.today();
    this.presentLoading().then(() => {
      this.chefService.getChef(Number(this.chefId)).subscribe({
        next: (res) => {
          this.chef = res;
          console.log(res);
          this.chef.imageChef = res.image;
          this.chef.image = res.dishs[0].image;
          this.order = res.dishs;
          this.dish = res.dishs;
        },
      });
      // this.service.getAllMenuDishes().subscribe((res) => {
      //   this.order = res.document.records;
      //   console.log(this.order);
      // });
      // this.service.getAllDishes().subscribe((res) => {
      //   this.dish = res.document.records;
      //   console.log(this.dish);
      // });
      this.loadingController.dismiss();
      // this.service.getAllOrders().subscribe((res) => {
      //   this.orders = res.document.records;
      //   console.log(this.orders);
      //   this.loadingController.dismiss();
      // });
    });
    this.getServiceChef();
  }

  getChefId() {
    this.route.params.subscribe((params) => {
      this.chefId = params['id'];
    });
  }

  // getServiceChef() {
  //   this.services.getServiceByChef(Number(this.chefId)).subscribe({
  //     next: (res) => {
  //       this.serviceChef = res;
  //       // this.services.getServiceDetail(res.id).subscribe({
  //       //   next: (detail) => {
  //       //     this.serviceChef.detail = detail;
  //       //   },
  //       // });
  //       this.serviceChef.detail = this.serviceChef.map((obj) => {
  //         this.services.getServiceDetail(obj.id).subscribe({
  //           next: (detail) => {
  //             this.serviceChef.details = detail;
  //           },
  //         });
  //       });
  //       console.log(this.serviceChef);
  //     },
  //   });
  // }

  // getServiceChef() {
  //   this.services.getServiceByChef(Number(this.chefId)).subscribe({
  //     next: (res) => {
  //       this.serviceChef = res;
  //       forkJoin(
  //         this.serviceChef.map((obj) => this.services.getServiceDetail(obj.id))
  //       ).subscribe({
  //         next: (details) => {
  //           this.serviceChef.details = details;
  //         },
  //         error: (error) => {
  //           console.error(error);
  //         },
  //       });
  //       console.log(this.serviceChef);
  //     },
  //     error: (error) => {
  //       console.error(error);
  //     },
  //   });
  // }
  getServiceChef() {
    this.services.getServiceByChef(Number(this.chefId)).subscribe({
      next: (res) => {
        this.serviceChef = res;

        // Sử dụng forkJoin để gộp nhiều yêu cầu thành một
        forkJoin(
          this.serviceChef.map((obj) => this.services.getServiceDetail(obj.id))
        ).subscribe({
          next: (details) => {
            // Gán chi tiết cho từng dịch vụ tương ứng
            this.serviceChef.forEach((service, index) => {
              service.details = details[index];
            });
            console.log(this.serviceChef);
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

  today() {
    this.currentDate = new Date();
  }

  // getChefDetail() {
  //   this.chefService.getChef(Number(this.chefId)).subscribe({
  //     next: (res) => {
  //       // this.chef = res;
  //       console.log(res);
  //     },
  //   });
  // }

  goToEvent() {
    this.router.navigate(['/events', this.chefId]);
  }

  segmentChanged(ev) {
    this.segId = ev.detail.value;
    console.log(this.segId);
  }

  async presentLoading() {
    const loading = await this.loadingController.create({
      message: 'Please Wait .. ',
    });
    await loading.present();
  }

  home() {
    this.router.navigate(['/search-chef']);
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

  async presentActionSheet(item: any) {
    const actionSheet = await this.actionSheetController.create({
      header: 'Please Select',
      cssClass: 'my-custom-class',
      mode: 'ios',
      buttons: [
        {
          text: 'Add to Cart',
          icon: 'cart-outline',
          data: 'Data value',
          handler: () => {
            this.cartService.getItems();
            this.cartService.addToCart(item, 1);
          },
        },
        {
          text: 'Add to Favourites',
          icon: 'star-outline',
          handler: () => {
            console.log('Favorite clicked');
          },
        },
        {
          text: 'Cancel',
          icon: 'close',
          role: 'cancel',
          handler: () => {
            console.log('Cancel clicked');
          },
        },
      ],
    });
    await actionSheet.present();

    const { data } = await actionSheet.onDidDismiss();
    console.log('onDidDismiss resolved with role and data', data);
  }
  // addToCart(item: any) {
  //   this.cartService.getItems();
  //   this.cartService.addToCart(item, 1);
  // }
}
