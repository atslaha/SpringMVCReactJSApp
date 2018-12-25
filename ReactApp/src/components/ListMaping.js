import axios, { post } from 'axios';
import {Fragment} from "react";

const React = require('react');


export default class ListMapping extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            line_id: 0,
        }

        this.deleteRow = this.deleteRow.bind(this);
    }

    deleteRow(e){
        //this.setState({line_id: e});

        axios.get(`http://localhost:8080/delete-${e}-line`).then(response =>{
            console.log(response);
            console.log(response.data);
            console.log(this.state.line_id);
        })
            .catch(function (error) {
            console.log(error);
        });
    //console.log(e);
    //     const url = 'http://localhost:8080/delete-{line_id}-line';
    //     const formData = new FormData();
        //formData.append('line_id',e);

        // this.Axios.post('/delete-{line_id}-line', {
        //     line_id: e,
        //
        // })
        //     .then(function (response) {
        //         console.log(response);
        //     })
        //     .catch(function (error) {
        //         console.log(error);
        //     });

   // (e) => this.deleteRow(this.props.listLines.line_id, e)
    }

    render() {
        return (
            <tr>
                <td>{this.props.listLines.line}</td>
                <td>{this.props.listLines.longest_word}</td>
                <td>{this.props.listLines.shortest_word}</td>
                <td>{this.props.listLines.line_length}</td>
                <td>{this.props.listLines.average_w_length}</td>
                <td>
                    <button className="btn delete" onClick={(e) => this.deleteRow(this.props.listLines.line_id, e)}>Delete</button>
                    {/*<a href={`http://localhost:8080/delete-${this.props.listLines.line_id}-line`} >DELETE</a>*/}
                </td>
            </tr>
        )
    }
}