import axios, { post, get } from 'axios';
import {handleChange} from './App';


const React = require('react');



export default class ListMapping extends React.Component{

    constructor(props) {
        super(props);
        this.state ={
            listLines: props.listLines,
        }
        this.deleteRow = this.deleteRow.bind(this);
        this.updateRows = this.updateRows.bind(this);
        this.sendData = this.sendData.bind(this);
    }

    // componentDidMount() {
    //     console.log(this);
    // }

    updateRows(){

        axios.get('http://localhost:8080/listLinesRequest')
            .then(function (response) {
                console.log(response);
                this.setState({listLines: response.data});
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    sendData() {
        this.props.buttonListLines();
    }

deleteRow(e){
        axios.get(`http://localhost:8080/delete-${e}-line`).then( function (response) {
            console.log(response.data);
        }).then(this.updateRows).then(this.sendData)
            .catch(function (error) {
            console.log(error);
        });
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
                </td>
            </tr>
        )
    }
}