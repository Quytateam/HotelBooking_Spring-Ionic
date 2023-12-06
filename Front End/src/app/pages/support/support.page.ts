import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-support',
  templateUrl: './support.page.html',
  styleUrls: ['./support.page.scss'],
})
export class SupportPage implements OnInit {
  totalItemsCart: number = 0;
  constructor(private cartService: CartService) {}

  ngOnInit() {
    this.totalItemsCart = this.cartService.getTotalItemsCount();
  }
}
