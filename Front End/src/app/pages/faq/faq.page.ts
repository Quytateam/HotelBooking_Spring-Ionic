import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.page.html',
  styleUrls: ['./faq.page.scss'],
})
export class FaqPage implements OnInit {
  totalItemsCart: number = 0;
  constructor(private cartService: CartService) {}

  ngOnInit() {
    this.totalItemsCart = this.cartService.getTotalItemsCount();
  }
}
