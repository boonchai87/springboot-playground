import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import React, { Component } from "react"

class App extends Component {
  state = {
    clients: []
  };

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/api/member');
    const body = await response.json();
    console.log(body.content);
    this.setState({clients: body.content});
  }

  render() {
    const {clients} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
              <h2>Clients</h2>
              {clients.map(client =>
                  <div key={client.id}>
                    {client.firstName} ({client.email})
                  </div>
              )}
            </div>
          </header>
        </div>
    );
  }
}
export default App;
