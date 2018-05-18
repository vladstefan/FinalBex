import React, { Component } from 'react';
import FlatButton from 'material-ui/FlatButton';
import Search from 'material-ui/svg-icons/action/search';
import TextField from 'material-ui/TextField';
import { Modal, Button } from 'react-bootstrap';
import CathegoryBookParser from './CathegoryBookParser';


class SearchButton extends Component {

    constructor(props) {
        super(props);
        this.state = {
            open: false,
            show: false
        };

        this.handleCloseModal = this.handleCloseModal.bind(this);
        this.handleShow = this.handleShow.bind(this);
        this.handleToggle = this.handleToggle.bind(this);
        this.functionse = this.functionse.bind(this);
    }

    handleToggle = () => this.setState({ open: !this.state.open });

    handleCloseModal() {
        this.setState({ show: false });
    }
    handleShow() {
        this.setState({ show: true });
    }


    returnTextfield() {
        if (this.state.open) {
            return (
                <TextField
                    hintText="Search for..."
                    style={{
                        width: 500
                    }}
                    onKeyPress={event => this.onKeyDown(event)}
                />
            );
        }
    }

    functionse(value){
        fetch(`http://localhost:8090/search/${value}`, {
            method: 'get'

        }).then(response => response.json())
            .then(books => {
                this.setState({ books })
                if(this.state.books && this.state.books.length > 0) {
                    this.handleShow();
                }

            });
    }

    onKeyDown(event) {

        if (event.key === 'Enter') {
            this.enterKeyPressed = true;
            const value = event.target.value;
            console.log(value);

            this.functionse(value);
        }
    }

    render() {
        return (
            <div>
                {this.returnTextfield()}
                <FlatButton className="bar__btn"
                            eventKey={1}
                            icon={<Search color="#fff" />}
                            onClick={this.handleToggle}
                />
                <div>
                    <Modal bsSize="large"
                           className="main__modal"
                           show={this.state.show}
                           onHide={this.handleCloseModal}
                    >

                        <Modal.Body>
                            <CathegoryBookParser booklist={this.state.books}/>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button bsStyle="primary" bsSize="small" onClick={this.handleCloseModal}>Close</Button>
                        </Modal.Footer>
                    </Modal>
                </div>
            </div>



        );
    }
}

export default SearchButton;