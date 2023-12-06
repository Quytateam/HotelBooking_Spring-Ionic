import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoadingController } from '@ionic/angular';
import { AppServiceService } from 'src/app/services/app-service.service';
import { CartService } from 'src/app/services/cart.service';
import { ChefService } from 'src/app/services/chef.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.page.html',
  styleUrls: ['./messages.page.scss'],
})
export class MessagesPage implements OnInit {
  user = [];
  chef: any;
  totalItemsCart: number = 0;
  constructor(
    private service: AppServiceService,
    public loadingController: LoadingController,
    private router: Router,
    private cartService: CartService,
    private chefService: ChefService
  ) {}

  ngOnInit() {
    this.totalItemsCart = this.cartService.getTotalItemsCount();
    this.presentLoading().then(() => {
      this.service.getAllMsg().subscribe((res) => {
        this.user = res.document.records;
        console.log(this.user);
        this.loadingController.dismiss();
      });
    });
    this.getListChef();
  }

  getListChef() {
    this.chefService.getListChef().subscribe({
      next: (res) => {
        this.chef = res;
      },
      error: (res) => {},
    });
  }

  viewChef(id: number) {
    this.router.navigate(['/chef-details', id]);
  }

  async presentLoading() {
    const loading = await this.loadingController.create({
      message: 'Please Wait .. ',
    });
    await loading.present();
  }
}
