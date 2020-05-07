import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { StockData } from '../Model/StockData';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

stockdata: StockData[] = [];
  clickCounter: number = 0;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getAllStocks();
  }

  columnDefs = [
    {headerName: 'Name', field: 'name', sortable: true, filter: true },
    {headerName: 'Prices', field: 'prices', sortable: true, filter: true},
    {headerName: 'Change', field: 'change', sortable: true, filter: true},
    {headerName: 'Percentage Change', field: 'percentageChange', sortable: true, filter: true},
    {headerName: 'Market Cap', field: 'marketCap', sortable: true, filter: true},
    {headerName: 'Average Volume', field: 'averageVolume', sortable: true, filter: true},
    {headerName: 'Volume', field: 'volume', sortable: true, filter: true},
    {headerName: 'Week Range', field: 'weekRange', sortable: true, filter: true}
];

  public getAllStocks(){
    let url = "http://localhost:8080/getStocksHitting52Low";
    this.http.get<StockData[]>(url).subscribe(
      res => {
        this.stockdata = res;
      }
    );
  }
  rowData: any = this.stockdata;

}

