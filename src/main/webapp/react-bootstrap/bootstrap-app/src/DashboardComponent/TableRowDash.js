import React, { Component } from 'react';
import FlatButton from 'material-ui/FlatButton';

import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn,
} from 'material-ui/Table';
export default class TableRowDash extends Component {

  returnBook(bookId, loanId) {
    fetch('http://localhost:8090/return',{
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      method: 'post',
      body: JSON.stringify({bookId: bookId,
                            loanId: loanId
      })
    }).then(response => this.props.onReturnClick());

  }

  render(){

    return(
        <div>
          <TableRow >
            <TableRowColumn>{<FlatButton onClick={() => this.returnBook(this.props.bookId, this.props.id)} label="RETURN"/>}</TableRowColumn>
            <TableRowColumn>{this.props.id}</TableRowColumn>
            <TableRowColumn>OTHER_VALUE</TableRowColumn>
            <TableRowColumn>{this.props.returnDate}</TableRowColumn>
            {/*<TableRowColumn>Employed</TableRowColumn>*/}
            {/*<FlatButton label="RETURN"/>*/}
          </TableRow>
        </div>
    );



  }
}