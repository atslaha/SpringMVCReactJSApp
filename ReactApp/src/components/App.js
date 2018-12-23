import React, { Component } from 'react';
import axios from 'axios';
import ListMapping from './ListMaping';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {listLines: []};
        this.Axios = axios.create({
            baseURL: "/",
            //headers: {'content-type': 'application/json', 'creds':'lines'}
        });
    }

    componentDidMount() {
        let _this = this;
        this.Axios.get('/api/listLinesRest')
            .then(function (response) {
                console.log(response);
                _this.setState({listLines: response.data});
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    getMassege(){
        var str,str1 ;
        str = axios.get('/api/getTestTransaction');
        str1 = JSON.stringify(str);
        //document.write(Object.values(str) + "DATA!!!");
       // alert(str.data);
        axios.get('/api/getTestTransaction').then(response => console.log(response));

      // console.log(response)

    }






    render() {

        var listLines = this.state.listLines.map((listLines) =>
            <ListMapping key={this.state.listLine.id} listLines={listLines}/>
        );

        return(
            <div>

                <h1>My  React App</h1>
                <h1>I'm Happy</h1>
                <h1>I'm Happy</h1>


                <form method="POST" action="uploadFile" encType="multipart/form-data">
                    File to upload: <input type="file" name="file" accept="text/plain" /><br/>
                    Name: <input type="text" name="name" /><br/>
                        <input type="submit" value="Upload" /> Press here to upload the file!
                </form>

                <h3>
                    {/*${message} ${name}*/}

                </h3>

                <button className="parse-button"  onClick={ this.button() }>Parse File</button>



                <table>
                    <tbody>
                    <tr>
                        <td width="700px">Lines</td>
                        <td>Longest word</td>
                        <td>Shortest word</td>
                        <td>Line Length</td>
                        <td>Average Word Length</td>
                        <td width="40px"></td>
                    </tr>
                    {listLines}
                    </tbody>
                </table>


            </div>
        );
    }

    button(){
        axios.get('/parse');

    }


}

export default App;