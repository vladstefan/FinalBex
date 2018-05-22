import React from 'react';
import Drawer from 'material-ui/Drawer';
import MenuItem from 'material-ui/MenuItem';
import FlatButton from 'material-ui/FlatButton';
import ArrowDropRight from 'material-ui/svg-icons/navigation-arrow-drop-right';
import  Menu from 'material-ui/svg-icons/navigation/menu';
import CathegoryBookParser from './CathegoryBookParser';
import { Modal, Button } from 'react-bootstrap';

export default class DrawMenu extends React.Component {

    constructor(props) {
        super(props);

        this.handleClose = this.handleClose.bind(this);
        this.handleToggle = this.handleToggle.bind(this);
        this.handleCloseModal = this.handleCloseModal.bind(this);
        this.handleShow = this.handleShow.bind(this);
        this.returnModal = this.returnModal.bind(this);
        this.fetchData = this.fetchData.bind(this);

        this.state = { 
            open: false,  
            cathegories:[],
            catheg: [],
            show: false
        };
    }

    handleToggle = () => this.setState({ open: !this.state.open });

    handleClose = () => this.setState({ open: false });

    fetchData(value) {
        console.log(value);
        fetch(`http://localhost:8090/booksbycategory/${value}`, {
            method: 'get'

        }).then(response => response.json())
            .then(catheg => {
                // console.log(books);
                this.setState({ catheg })
                // console.log(this.state.books);
            });

    }



    handleCloseModal() {
        this.setState({ show: false });
    }

    handleShow() {
        this.setState({ show: true });
    }

    returnModal(value){
        this.fetchData(value);
        this.handleShow();
        this.setState({open:false});
    }
   
    render() {
        console.log(this.state.catheg);
        return (
            <div>
                <FlatButton
                    icon={<Menu color="#fff" />}
                    onClick={this.handleToggle}
                />
                <Drawer

                    containerStyle={{
                        backgroundColor: "rgba(0,0,0,0.1)"

                    }}
                    docked={false}
                    width={200}
                    open={this.state.open}
                    onRequestChange={(open) => this.setState({ open })}
                >
                    <MenuItem onClick={this.handleClose}>Menu Item</MenuItem>
                    <MenuItem 
                        primaryText="Cathegories"
                        rightIcon={<ArrowDropRight />}
                        menuItems={[
                            this.props.items.map(post => <MenuItem primaryText={post.categoryName} onClick={event=>this.returnModal(post.categoryName)}/>)
                        ]}
                    />
                </Drawer>
                <div >
                    <Modal bsSize="large"
                        className="main__modal"
                         show={this.state.show} 
                        onHide={this.handleClose}
                    >
                        <Modal.Body>
                            <CathegoryBookParser booklist={this.state.catheg} />
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