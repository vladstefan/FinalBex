import React, { Component } from 'react';
import { Modal, Button } from 'react-bootstrap';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import darkBaseTheme from 'material-ui/styles/baseThemes/darkBaseTheme';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';

class RentButton extends Component {
    constructor(props, context) {
        super(props, context);

        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);

        this.state = {
            show: false,
            value: 1
        };
    }

    handleChange = (event, index, value) => this.setState({ value });

    handleClose() {
        this.setState({ show: false });
    }

    handleShow() {
        this.setState({ show: true });
    }

    render() {
        return (
            <div >

                <Button bsStyle="success" bsSize="large" onClick={this.handleShow}>
                    Rent Book
                </Button>
                <MuiThemeProvider muiTheme={getMuiTheme(darkBaseTheme)}>
                    <Modal className="modal__rent" show={this.state.show} onHide={this.handleClose}>
                        <Modal.Header closeButton>
                            <Modal.Title className="text__color">Rent <span className="book__titleModal">{this.props.title}</span></Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <SelectField
                                
                                floatingLabelText="Choose period to rent"
                                value={this.state.value}
                                onChange={this.handleChange}
                            >
                                <MenuItem value={1} primaryText="1 week" />
                                <MenuItem value={2} primaryText="2 weeks" />
                                <MenuItem value={3} primaryText="3 weeks" />
                            </SelectField>


                        </Modal.Body>
                        <Modal.Footer>
                            <Button bsStyle="danger" bsSize="small" onClick={this.handleClose}>Submit</Button>
                            <Button bsStyle="primary" bsSize="small" onClick={this.handleClose}>Close</Button>
                        </Modal.Footer>
                    </Modal>
                </MuiThemeProvider>
            </div>
        );
    }
}



export default RentButton;