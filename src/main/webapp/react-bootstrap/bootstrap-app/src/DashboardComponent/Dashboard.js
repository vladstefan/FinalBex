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



    render() {

        const rows=  this.state.loans.map(post => <TableRowDash id={post.loanId} returnDate={post.returnDate} />)

        return (
            <MuiThemeProvider>
                <Table onRowSelection={this.handleRowSelection}>
                    <TableHeader adjustForCheckbox={this.state.showCheckboxes} displaySelectAll={this.state.showCheckboxes}>

                        <TableRow>
                            <TableHeaderColumn>ID</TableHeaderColumn>
                            <TableHeaderColumn>Return Date</TableHeaderColumn>
                            <TableHeaderColumn>Status</TableHeaderColumn>

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
