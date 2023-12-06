import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.page.html',
  styleUrls: ['./profile-page.page.scss'],
})
export class ProfilePagePage implements OnInit {
  totalItemsCart: number = 0;
  constructor(private cartService: CartService) {}

  ngOnInit() {
    this.totalItemsCart = this.cartService.getTotalItemsCount();
  }
}
