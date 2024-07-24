import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent implements OnInit{
public data :any;
public dataSource: any;
public  users : any
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  public displayedColumns =['id', 'code','validation','email','lastName','firstName'];
  constructor(private http : HttpClient) {
  }
  ngOnInit(): void {
    this.http.get('http://localhost:8080/users').subscribe(
      {
        next : data => {
          this.users=data;
          this.dataSource=new MatTableDataSource(this.users)
          this.dataSource.paginator=this.paginator;
          this.dataSource.sort=this.sort;
      },
        error : err => { console.log(err)
        }
      }
    )

    }
  }


