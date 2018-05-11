import React, { Component } from 'react';
import CathegoryBookParser from './CathegoryBookParser';
import {Modal, Button} from 'react-bootstrap';

export default class ModalBookCategory extends Component {

    constructor(props, context) {
        super(props, context);

        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);

        this.state = {
            show: true
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
        console.log("I  AMM  ALSO  HERE!1");
        return (
            <div >
                <Modal className="modal" show={this.state.show} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Rent <span className="book__titleModal">{this.props.title}</span></Modal.Title>
                    </Modal.Header>
                    <Modal.Body>

                        <CathegoryBookParser booklist={this.props.booklist} />

                    </Modal.Body>
                    <Modal.Footer>
                        <Button bsStyle="danger" bsSize="small" onClick={this.handleClose}>Submit</Button>
                        <Button bsStyle="primary" bsSize="small" onClick={this.handleClose}>Close</Button>
                    </Modal.Footer>
                </Modal>
            </div>
        );
    }
}