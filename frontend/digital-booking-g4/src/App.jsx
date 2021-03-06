import Layout from "./components/Layout/Layout";
import Home from "./components/Home/Home";
import Login from "./components/Forms/Login";
import Register from "./components/Forms/Register";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import loggedContext from "./contexts/loggedContext";
import { useState } from "react";

function App() {
  const [isLogged, setIsLogged] = useState(false);

  return (
    <loggedContext.Provider value={{ isLogged, setIsLogged }}>
      <div className="App">
        <BrowserRouter>
          <Layout>
            <Switch>
              <Route exact path="/" component={Home} />
              <Route path="/login" component={Login} />
              <Route path="/register" component={Register} />
            </Switch>
          </Layout>
        </BrowserRouter>
      </div>
    </loggedContext.Provider>
  );
}

export default App;
