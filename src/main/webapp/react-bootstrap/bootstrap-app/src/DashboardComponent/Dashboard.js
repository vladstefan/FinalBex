import React, {Component} from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import FlatButton from 'material-ui/FlatButton';
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn,
} from 'material-ui/Table';
import TableRowDash from "./TableRowDash";

export default class Dashboard extends Component {
  constructor(props){
    super(props);

    this.state = {
      showCheckboxes:false,
      loans: []

    };
  }

  handleRowSelection = (selectedRows) => {
    this.setState({
      selected: selectedRows,
    });
  };
  componentDidMount() {

    fetch('http://localhost:8090/admin')
    .then(response => response.json())
    .then(loans => {
      this.setState({ loans })

    });

  }

  returnHandler(loandId, bookId) {
    this.setState({
      loans: this.state.loans.filter((loan) => loan.loanId !== loandId)
    });
  }


  render() {

    // const rows=  this.state.loans.map(post => <TableRowDash id={post.loanId} returnDate={post.returnDate} />)
    const rows = this.state.loans.map(post => <TableRowDash id={post.title} bookId={post.bookId} onReturnClick={this.returnHandler.bind(this, post.loanId, post.bookId)} returnDate={new Intl.DateTimeFormat('en-US', {year: 'numeric', month: '2-digit',day: '2-digit'}).format(post.returnDate)} />)

    return (
        <MuiThemeProvider>
            <Table onRowSelection={this.handleRowSelection}>
                <TableHeader adjustForCheckbox={this.state.showCheckboxes} displaySelectAll={this.state.showCheckboxes}>

                    <TableRow>
                        <TableHeaderColumn></TableHeaderColumn>
                        <TableHeaderColumn>Book</TableHeaderColumn>
                        <TableHeaderColumn>Employee</TableHeaderColumn>
                        <TableHeaderColumn>Return Date</TableHeaderColumn>

                    </TableRow>
                </TableHeader>
                <TableBody displayRowCheckbox={this.state.showCheckboxes}>
                  {rows}
                </TableBody>
            </Table>
        </MuiThemeProvider>
    );
  }
}