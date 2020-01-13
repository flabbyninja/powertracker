import React, { Component } from 'react';

class ListItem extends Component {
    constructor(props) {
        super(props);
        console.log("Loading ListItem component...");
    }

    render() {
        return (
            <ul>
    <li key={this.props.item.id}>{this.props.item.brand}, {this.props.item.capacity}, {this.props.item.location}</li>
            </ul>
        );
    }
}

export default ListItem;
