import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-setting',
  templateUrl: './setting.page.html',
  styleUrls: ['./setting.page.scss'],
})
export class SettingPage implements OnInit {
  setting: any = {
    appSetting: true,
    sms: true,
    offer: true,
    reminder: '',
    touchId: true,
    faceId: true,
  };
  totalItemsCart: number = 0;
  constructor(private cartService: CartService) {}

  ngOnInit() {
    this.totalItemsCart = this.cartService.getTotalItemsCount();
  }

  changeApp(ev) {
    this.setting.appSetting = ev.detail.checked;
    console.log(this.setting);
  }

  changeSms(ev) {
    this.setting.sms = ev.detail.checked;
    console.log(this.setting);
  }

  changeOffer(ev) {
    this.setting.offer = ev.detail.checked;
    console.log(this.setting);
  }

  changeReminder(ev) {
    this.setting.reminder = ev.detail.value;
    console.log(this.setting);
  }

  changetouchId(ev) {
    this.setting.touchId = ev.detail.checked;
    console.log(this.setting);
  }

  changefaceId(ev) {
    this.setting.faceId = ev.detail.checked;
    console.log(this.setting);
  }
}
