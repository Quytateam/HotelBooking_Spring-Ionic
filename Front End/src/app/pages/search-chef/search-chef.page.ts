import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {
  LoadingController,
  ModalController,
  PopoverController,
} from '@ionic/angular';
import { AppServiceService } from 'src/app/services/app-service.service';
import { NotificationsComponent } from '../components/notifications/notifications.component';
import { ProfileComponent } from '../components/profile/profile.component';
import { ProfileMenuPage } from '../popup/profile-menu/profile-menu.page';
import { ChefService } from 'src/app/services/chef.service';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-search-chef',
  templateUrl: './search-chef.page.html',
  styleUrls: ['./search-chef.page.scss'],
})
export class SearchChefPage implements OnInit {
  dish = [];
  chefList = [];
  returnDataFromModal;
  totalItemsCart: number = 0;
  constructor(
    private modalCtrl: ModalController,
    private service: AppServiceService,
    public loadingController: LoadingController,
    private router: Router,
    public popoverController: PopoverController,
    private chefService: ChefService,
    private cartService: CartService
  ) {}

  ngOnInit() {
    this.totalItemsCart = this.cartService.getTotalItemsCount();
    this.presentLoading().then(() => {
      this.service.getAllDishes().subscribe((res) => {
        this.dish = res.document.records;
        // console.log(this.dish);
        this.loadingController.dismiss();
      });
    });
    this.getList();
  }

  getList() {
    this.chefService.getListChef().subscribe({
      next: (res) => {
        this.chefList = res;
        console.log(this.chefList);
        // this.loadingController.dismiss();
      },
    });
  }

  async presentLoading() {
    const loading = await this.loadingController.create({
      message: 'Please Wait .. ',
    });
    await loading.present();
  }

  async presentPopover(ev: any, id: number) {
    const popover = await this.popoverController.create({
      component: ProfileMenuPage,
      componentProps: { id: id },
      event: ev,
      mode: 'ios',
      translucent: true,
    });
    await popover.present();
    const { role } = await popover.onDidDismiss();
  }

  async presentModal(ev) {
    const modal = await this.modalCtrl.create({
      component: ProfileComponent,
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

  async notificationModal(ev) {
    const modal = await this.modalCtrl.create({
      component: NotificationsComponent,
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
      cssClass: 'my-modal-notification',
    });

    modal.onDidDismiss().then((data: any) => {
      this.returnDataFromModal = data;
      console.log(this.returnDataFromModal);
    });
    return await modal.present();
  }
  goToEvent(id: number) {
    console.log(id);
    this.router.navigate(['/events', id]);
  }
}
