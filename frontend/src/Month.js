import React, { useState } from "react"
import Table from './Table'
import axios from 'axios'
import { SERVER_URL } from './constant'


const Month = ({
    data,
    setData,
    year,
    setYear,
    month,
    setMonth,
    
  }) => {
     
      // const [data, setData] = useState([])
      // const [year, setYear] = useState('2019')
      // const [month, setMonth] = useState('3')
  
      const url = SERVER_URL;

      const allScans = "34"
      const errors = "3"
  
      const handleDownload = (e) => {
        (async function fetchData() {
          const response = await axios.get(url + '/scannerLogsBy?year='+year+'&month='+month);
          setData(response.data);
        })();
      };
  
  const columns = React.useMemo(
    () => [
      {
        Header: 'Index',
        accessor: 'createdDate',
      },
      {
        Header: 'Sygnatura',
        accessor: 'virtua.signature'
      },
      {
        Header: 'Kod kreskowy',
        accessor: 'virtua.barcode'
      },
      {
        Header: 'Autor',
        accessor: 'virtua.author',
      },
      {
        Header: 'Tytół',
        accessor: 'virtua.title'
      },
      {
        Header: 'Ilość wyporzyczeń',
        accessor: 'barcode'
      }
    ],
    []
  )

  return (
    <div>
     
      {/* <form onSubmit={handleSubmit}> */}
          <label>
            Wybierz rok\miesiąc:
            </label>
            <select value = {year} onChange={e => setYear(e.currentTarget.value)}>
              <option value='2019' selected>2019</option>
              <option value='2020'>2020</option>
            </select>
            <select value = {month} onChange={e => setMonth(e.currentTarget.value)}>
              <option value='0'></option>
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
          {/* </label> */}
          <button  onClick={handleDownload}>pobierz</button>
          
          {/* <input type="submit" value="Wyślij" /> */}
        {/* </form> */}
      
      <br />
  <label>Całkowita ilość skanów: {allScans}, w tym błędnych skanów: {errors}</label>
      {/* <Styles> */}
      <Table
        columns={columns}
        data={data}
        // setData={setData}
        
      />
      {/* </Styles> */}
    </div>
  
  );
  }
  
  export default Month