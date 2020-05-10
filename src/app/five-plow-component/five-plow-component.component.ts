import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { StockData } from '../Model/StockData';

@Component({
  selector: 'app-five-plow-component',
  templateUrl: './five-plow-component.component.html',
  styleUrls: ['./five-plow-component.component.scss']
})
export class FivePLowComponentComponent implements OnInit {

  stockdata: StockData[] = [];
  clickCounter: number = 0;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getAllStocks();
  }

  public getAllStocks(){
    let url = "http://localhost:8080/displayFivePercentLow";
    this.http.get<StockData[]>(url).subscribe(
      res => {
        this.stockdata = res;
      }
    );
  }
  countClick() {
    this.clickCounter +=1;
  }

}