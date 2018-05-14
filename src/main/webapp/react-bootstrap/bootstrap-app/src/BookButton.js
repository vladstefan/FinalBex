import React, { Component } from 'react';
import Modal from 'react-modal';


const customStyles = {
    content: {
        top: '50%',
        left: '50%',
        right: 'auto',
        bottom: 'auto',
        marginRight: '-50%',
        transform: 'translate(-50%, -50%)'
    }
};

Modal.setAppElement(document.getElementById('body'));

class BookButton extends Component {

    constructor() {

        super();
        this.state = {
            modalIsOpen: false,
            bookPeriod: 0,
            email: ''
        };
        this.verifyLogin();
        this.openModal = this.openModal.bind(this);
        // this.afterOpenModal = this.afterOpenModal.bind(this);
        this.closeModal = this.closeModal.bind(this);

    }


    verifyLogin() {
        const email = document.getElementById('loggedIn');
        if (email && email.content) {
            this.setState({ email: email.content });
        }
    }

    openModal() {
        this.setState({ modalIsOpen: true });
    }

    closeModal() {
        // fetch de post
        this.setState({ modalIsOpen: false });
    }


    postLoan(loan) {
        fetch('http://localhost:3000/loans', {
            method: 'post',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loan)
        });
    }

    loanSubmit(event, id, title) {
        event.preventDefault();
        const period = this.state.bookPeriod;
        const loan = {
            bookId: id,
            bookTitle: title,
            bookPeriod: period
        };
        this.postLoan(loan);
        this.closeModal();

    }

    setPeriod(event, id, title) {
        // console.log(event.target.value);
        this.setState({
            bookPeriod: event.target.value
        });
        // console.log(this.state);
    }

    render() {
        return (
            <div>
                {/* {this.showSubmit()} */}
                <button onClick={this.openModal}>Open Modal</button>
                <Modal
                    isOpen={this.state.modalIsOpen}
                    onRequestClose={this.state.closeModal}
                    style={customStyles}
                    contentLabel="Example Modal"
                >
                    <div className="modal-container">
                        <div className="modalll">
                            <div className="modal__content">
                                <div className="modal__message">
                                    <h1>Rent the book for: </h1>
                                </div>
                                <div className="form__container">
                                    <form action="/" onChange={event => this.setPeriod(event)}>
                                        <input type="radio" className="form__input" name="period" value="1" /> 1 Week
                                        <br />
                                        <input type="radio" className="form__input" name="period" value="2" /> 2 Weeks
                                        <br />
                                        <input type="radio" className="form__input" name="period" value="3" /> 3 Weeks
                                        <div className="submit__button">
                                            <button className="button" type="button" onClick={event => this.loanSubmit(event, this.props.id, this.props.title)}>Submit</button>
                                        </div>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>

                    {/* <h2>{this.props.id}</h2> */}
                    <button onClick={this.closeModal}>Close</button>
                </Modal>
            </div>
        );
    }

}

export default BookButton;