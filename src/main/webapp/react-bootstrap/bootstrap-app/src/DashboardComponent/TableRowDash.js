import React, { Component } from 'react';

import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow,
    TableRowColumn,
} from 'material-ui/Table';
export default class TableRowDash extends Component {

    render(){
        console.log('received id: ',this.props.id);
        return(
            <div>
                <TableRow >
                <TableRowColumn>{this.props.id}</TableRowColumn>
                <TableRowColumn>{this.props.returnDate}</TableRowColumn>
                    <TableRowColumn>OTHER_VALUE</TableRowColumn>
                    {/*<TableRowColumn>Employed</TableRowColumn>*/}
                {/*<FlatButton label="RETURN"/>*/}
                </TableRow>
            </div>
        );



    }
}