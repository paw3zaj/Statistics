import React from "react"
import Table from './Table'
import axios from 'axios'
import {SERVER_URL} from './constant'

const Month = ({
                   data,
                   setData,
                   year,
                   setYear,
                   month,
                   setMonth,
                   all,
                   setAll,
                   error,
                   setError
               }) => {

    const url = SERVER_URL;

    async function fetchDataInMonth() {
        await axios.get(`${url}/countAllScans?year=${year}&month=${month}`)
            .then(response => {setAll(response.data)});
        await axios.get(`${url}/countBadScans?year=${year}&month=${month}`)
            .then(response => {setError(response.data)});
        await axios.get(`${url}/getAllInMonth?year=${year}&month=${month}`)
            .then(response => {setData(response.data)});
    }

    async function fetchDataInYear() {
        await axios.get(`${url}/countAllScans?year=${year}&month=1`)
            .then(response => {setAll(response.data)});
        await axios.get(`${url}/countBadScans?year=${year}&month=1`)
            .then(response => {setError(response.data)});
        await axios.get(`${url}/getAllInYear?year=${year}`)
            .then(response => {setData(response.data)});
    }

    const handleDownload = (e) => {
        if(month === '0') {
             return fetchDataInYear()
        } else {
             return fetchDataInMonth()
        }
    }

    const columns = React.useMemo(
        () => [
            {
                Header: 'Autor',
                accessor: 'author',
            },
            {
                Header: 'Tytół',
                accessor: 'title'
            },
            {
                Header: 'Ilość wyporzyczeń',
                accessor: 'amount'
            }
        ],
        []
    )

    return (
        <div>

            {/* <form onSubmit={handleSubmit}> */}
            <label>
                Wybierz miesiąc\rok:
            </label>

            <select value={month} onChange={e => setMonth(e.currentTarget.value)}>
                <option value='0'>Cały</option>
                <option value='1'>Styczeń</option>
                <option value='2'>Luty</option>
                <option value='3'>Marzec</option>
                <option value='4'>Kwiecień</option>
                <option value='5'>Maj</option>
                <option value='6'>Czerwiec</option>
                <option value='7'>Lipiec</option>
                <option value='8'>Sierpień</option>
                <option value='9'>Wrzesień</option>
                <option value='10'>Październik</option>
                <option value='11'>Listopad</option>
                <option value='12'>Grudzień</option>
            </select>
            <select value={year} onChange={e => setYear(e.currentTarget.value)}>
                <option value='2019'>2019</option>
                <option value='2020'>2020</option>
            </select>
            {/* </label> */}
            <button onClick={handleDownload}>pobierz</button>

            {/* <input type="submit" value="Wyślij" /> */}
            {/* </form> */}

            <br/>
            <label>Całkowita ilość skanów: {all}, w tym błędnych skanów: {error}</label>
            {/* <Styles> */}
            <Table
                columns={columns}
                data={data}
            />
            {/* </Styles> */}
        </div>

    );
}

export default Month