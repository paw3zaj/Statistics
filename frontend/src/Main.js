import React, { useEffect, useState } from "react";
import {
    Route,
    NavLink,
    HashRouter,
    Switch
} from "react-router-dom";
import Item from "./Item";
import Month from "./Month";
import History from "./History";
import NotFound from "./NotFound"
import { SERVER_URL } from './constant'
import axios from 'axios'
import './Main.css';

const Main = () => {

  const [mdata, setMdata] = useState([])
    const [hdata, setHdata] = useState([])
    const [ddata, setDdata] = useState([])
    const [hddata, setHddata] = useState([])
    const [year, setYear] = useState('2020')
    const [month, setMonth] = useState('1')
    const [remove, setRemove] = useState(false)

    const url = SERVER_URL + '/allVirtuaLogs';

    useEffect(() => {
          (async function fetchData() {
            const response = await axios.get(url);
            setHdata(response.data);
          })();
        }, [url]);

  return (
    <HashRouter>
            <div>
                <ul className="header">
                    <li><NavLink exact to="/">Miesięczna/Roczna</NavLink></li>
                    <li><NavLink to="/item">Egzemplarzu</NavLink></li>
                    <li><NavLink to="/history">Historia zmian w Virtua</NavLink></li>
                </ul>
                <div className="content">
                    <Switch>
                    <Route exact path="/" component={() => <Month
                    data={mdata}
                    setData={setMdata}
                    year={year}
                    setYear={setYear}
                    month={month}
                    setMonth={setMonth}
                    remove={remove}
                    setRemove={setRemove}
                    />} />
                    <Route path="/item" component={() => <Item
                    data={ddata}
                    setData={setDdata}
                    hdata={hddata}
                    setHdata={setHddata}
                    />} />
                    <Route path="/history" component={() => <History
                    data={hdata}
                    setData={setHdata}
                    />} />
                    <Route component={NotFound} />
                    </Switch>
                </div>
            </div>
            </HashRouter>
  );
}

export default Main;