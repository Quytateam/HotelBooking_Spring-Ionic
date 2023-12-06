import { Component, OnInit, ViewChild } from '@angular/core';
import { CalendarComponent } from 'ionic2-calendar';
import { CalendarMode } from 'ionic2-calendar/calendar';
import { LoadingController, ModalController } from '@ionic/angular';
import { EventDetailsPage } from '../event-details/event-details.page';
import { AppServiceService } from 'src/app/services/app-service.service';
import { format, parseISO } from 'date-fns';
import { OrderService } from 'src/app/services/order.service';
import { StorageService } from 'src/app/services/storage.service';
import { ActivatedRoute } from '@angular/router';
import { BookingService } from 'src/app/services/booking.service';
import { CartService } from 'src/app/services/cart.service';
@Component({
  selector: 'app-events',
  templateUrl: './events.page.html',
  styleUrls: ['./events.page.scss'],
})
export class EventsPage implements OnInit {
  @ViewChild(CalendarComponent, null) myCal: CalendarComponent;
  allEvents = [];
  currentDate = new Date();
  currentMonth: string = 'ss';
  minDate = new Date().toISOString();
  showAddEvent: boolean;
  isToday: boolean;
  showPicker1 = false;
  showPicker2 = false;
  dateValue1 = format(new Date(), 'yyyy-MM-dd') + 'T09:00:00.000Z';
  dateValue2 = format(new Date(), 'yyyy-MM-dd') + 'T09:00:00.000Z';
  formattedString1 = '';
  formattedString2 = '';
  calendar = {
    mode: 'month' as CalendarMode,
    currentDate: new Date(),
  };
  newEvent = {
    title: '',
    description: '',
    startTime: '',
    endTime: '',
    img: '',
  };
  userId: any;
  chefId: any;
  totalItemsCart: number = 0;

  myData = [
    {
      title: 'What is Lorem Ipsum?',
      description: 'What is Lorem Ipsum?',
      startTime: new Date(2021, 10, 22, 12, 11, 11),
      endTime: new Date(2021, 10, 22, 14, 11, 11),
      img: 'https://picsum.photos/200',
    },
    {
      title: 'What is Lorem Ipsum?',
      description: 'What is Lorem Ipsum?',
      startTime: new Date(2021, 10, 22, 12, 11, 11),
      endTime: new Date(2021, 10, 22, 14, 11, 11),
      img: 'https://picsum.photos/200',
    },
    {
      title: 'What is Lorem Ipsum?',
      description: 'What is Lorem Ipsum?',
      startTime: new Date(2021, 10, 23, 8, 11, 11),
      endTime: new Date(2021, 10, 13, 9, 11, 11),
      img: 'https://picsum.photos/200',
    },
    {
      title: 'What is Lorem Ipsum?',
      description: 'What is Lorem Ipsum?',
      startTime: new Date(2021, 10, 21, 2, 11, 11),
      endTime: new Date(2021, 10, 21, 4, 11, 11),
      img: 'https://picsum.photos/200',
    },
    {
      title: 'What is Lorem Ipsum?',
      description: 'What is Lorem Ipsum?',
      startTime: new Date(2021, 10, 20, 12, 11, 11),
      endTime: new Date(2021, 10, 20, 14, 11, 11),
      img: 'https://picsum.photos/200',
    },
  ];
  constructor(
    private route: ActivatedRoute,
    public modalController: ModalController,
    public loadingController: LoadingController,
    private service: AppServiceService,
    private bookingService: BookingService,
    private storageService: StorageService,
    private cartService: CartService
  ) {
    this.formattedString1 = format(
      parseISO(format(new Date(), 'yyyy-MM-dd') + 'T09:00:00.000Z'),
      'HH:mm, MMM d, yyy'
    );

    this.formattedString2 = format(
      parseISO(format(new Date(), 'yyyy-MM-dd') + 'T09:00:00.000Z'),
      'HH:mm, MMM d, yyy'
    );
  }

  ngOnInit() {
    // this.allEvents = this.myData;
    this.totalItemsCart = this.cartService.getTotalItemsCount();
    console.log(this.allEvents);
    this.getUserId();
    this.getChefId();
    this.presentLoading().then(() => {
      this.bookingService.getListByChefId(Number(this.chefId)).subscribe({
        next: (res) => {
          res.forEach((event) => {
            this.allEvents.push({
              title: event.title,
              startTime: new Date(event.startTime),
              endTime: new Date(event.endTime),
              description: event.description,
              img: event.img,
              event_id: event.id,
            });
          });
          console.log(this.allEvents);
          console.log(this.myData);
          this.today();
          this.loadingController.dismiss();
        },
        error: (err) => {
          console.log(err);
        },
      });
    });
  }

  // getListByChefId() {
  //   // this.orderService.getListByChefId(Number(this.chefId)).subscribe({
  //   //   next: (res) => {
  //   //     res.forEach((event) => {
  //   //       this.allEvents.push({
  //   //         title: event.title,
  //   //         startTime: new Date(event.startTime),
  //   //         endTime: new Date(event.endTime),
  //   //         description: event.description,
  //   //         img: event.img,
  //   //         event_id: event.id,
  //   //       });
  //   //     });
  //   //     console.log(this.allEvents);
  //   //     console.log(this.myData);
  //   //     this.today();
  //   //     this.loadingController.dismiss();
  //   //   },
  //   //   error: (err) => {
  //   //     console.log(err);
  //   //   },
  //   // });
  //   // console.log(this.allEvents);
  //   // console.log(this.myData);
  //   // this.today();
  //   // this.loadingController.dismiss();
  // }

  getUserId() {
    this.userId = this.storageService.getUser().id;
  }

  getChefId() {
    this.route.params.subscribe((params) => {
      this.chefId = params['id'];
    });
  }

  dateChanged1(value1) {
    console.log(value1);
    this.newEvent.startTime = value1;
    this.dateValue1 = value1;
    this.formattedString1 = format(parseISO(value1), 'HH:mm, MMM d, yyy');
    this.showPicker1 = false;
  }

  dateChanged2(value2) {
    console.log(value2);
    this.newEvent.endTime = value2;
    this.dateValue2 = value2;
    this.formattedString2 = format(parseISO(value2), 'HH:mm, MMM d, yyy');
    this.showPicker2 = false;
  }

  async presentLoading() {
    const loading = await this.loadingController.create({
      cssClass: 'my-custom-class',
      message: 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh',
    });
    await loading.present();
  }

  onViewTitleChanged(title: string) {
    this.currentMonth = title;
  }
  async onEventSelected(ev) {
    this.newEvent = ev;
    const modal = await this.modalController.create({
      component: EventDetailsPage,
      componentProps: ev,
    });
    return await modal.present();
  }

  today() {
    this.calendar.currentDate = new Date();
  }

  changeMode(mode) {
    this.calendar.mode = mode;
  }

  next() {
    this.myCal.slideNext();
  }

  back() {
    this.myCal.slidePrev();
  }

  showHideForm() {
    this.showAddEvent = !this.showAddEvent;
    this.newEvent = {
      title: '',
      description: '',
      img: '',
      startTime: new Date().toISOString(),
      endTime: new Date().toISOString(),
    };
  }

  addEvent() {
    const { title, description, img, startTime, endTime } = this.newEvent;
    let email = this.storageService.getUser().email;
    let phone = '0123557';
    let address = '01 Công xã Paris, Bến Nghé, Q.1, Tp. HCM';
    this.bookingService
      .placeBooking(
        this.userId,
        Number(this.chefId),
        title,
        description,
        img,
        this.changeFormat(startTime),
        this.changeFormat(endTime),
        email,
        phone,
        address
      )
      .subscribe({
        next: (res) => {
          console.log(res);
        },
        error: (err) => {
          console.log(err);
        },
      });
    this.showHideForm();
  }

  changeFormat(time: string) {
    // const inputDateString = time;

    // const inputDate = new Date(inputDateString);

    // const options: Intl.DateTimeFormatOptions = {
    //   weekday: 'short' as const,
    //   day: 'numeric',
    //   month: 'short',
    //   year: 'numeric',
    //   hour: 'numeric',
    //   minute: 'numeric',
    //   second: 'numeric',
    //   timeZoneName: 'short',
    //   timeZone: 'Asia/Ho_Chi_Minh',
    // };

    // const formattedDate = new Intl.DateTimeFormat(
    //   'en-US',
    //   options
    // ).formatToParts(inputDate);

    // const formattedDateString = `${formattedDate[0].value}, ${formattedDate[4].value} ${formattedDate[2].value} ${formattedDate[6].value} ${formattedDate[8].value}:${formattedDate[10].value}:${formattedDate[12].value} ${formattedDate[14].value}`;

    // return formattedDateString;

    const inputDateString = time;

    const inputDate = new Date(inputDateString);

    const options: Intl.DateTimeFormatOptions = {
      weekday: 'short' as const,
      day: 'numeric',
      month: 'short',
      year: 'numeric',
      hour: 'numeric',
      minute: 'numeric',
      second: 'numeric',
      timeZoneName: 'short',
      hour12: false, // Đặt giá trị này để sử dụng định dạng 24 giờ
      timeZone: 'Asia/Ho_Chi_Minh',
    };

    const formatter = new Intl.DateTimeFormat('en-US', options);
    // const formattedDate = formatter.format(inputDate);
    const formattedDate = new Intl.DateTimeFormat(
      'en-US',
      options
    ).formatToParts(inputDate);

    const formattedDateString = `${formattedDate[0].value}, ${formattedDate[4].value} ${formattedDate[2].value} ${formattedDate[6].value} ${formattedDate[8].value}:${formattedDate[10].value}:${formattedDate[12].value} +0700`;

    return formattedDateString;
  }
}
